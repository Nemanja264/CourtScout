package com.example.courtscout.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.courtscout.presentation.services.auth.AuthState
import com.example.courtscout.presentation.viewmodels.AuthViewModel
import com.example.courtscout.presentation.AppViewModelProvider
import com.example.courtscout.presentation.services.auth.LoginInput
import com.example.courtscout.presentation.services.auth.LoginValidator

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onNavigateToRegister: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    val validator = remember { LoginValidator() }
    var localError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            Toast.makeText(context, "Welcome back!", Toast.LENGTH_SHORT).show()

            navController.navigate("home_screen"){
                popUpTo("login_screen"){inclusive = true}
                launchSingleTop = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to CourtScout",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            enabled = authState !is AuthState.Loading // Disable when loading
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            enabled = authState !is AuthState.Loading
        )

        Spacer(modifier = Modifier.height(24.dp))

        val remoteError = (authState as? AuthState.Error)?.message
        val errorText = localError ?: remoteError
        if (!errorText.isNullOrBlank()) {
            Text(
                text = errorText,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                if(validator.validate(LoginInput(email, password)))
                {
                    authViewModel.login(email, password)
                    localError = null
                }
                else
                    localError = "Email or password is missing."},
            modifier = Modifier.fillMaxWidth(),
            enabled = authState !is AuthState.Loading // Disable when loading
        ) {
            if (authState is AuthState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp), color =
                        MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Log In")
            }
        }

        TextButton(
            onClick = onNavigateToRegister,
            enabled = authState !is AuthState.Loading // Disable when loading
        ) {
            Text("Don't have an account? Register")
        }
    }
}

