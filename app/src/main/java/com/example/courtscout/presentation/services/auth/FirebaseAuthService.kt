package com.example.courtscout.presentation.services.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import com.example.courtscout.presentation.models.User
import com.example.courtscout.presentation.repositories.Resource
import android.net.Uri
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException


class FirebaseAuthService (
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
): AuthService{
    override val currentUser: User?
        get() = firebaseAuth.currentUser?.let {
            User(
                id = it.uid,
                email = it.email!!
            )
        }

    override suspend fun login(email: String, password: String): AuthState{
        return try {
            val e = email.trim()
            firebaseAuth.signInWithEmailAndPassword(e, password).await()
            AuthState.Authenticated
        }
        catch (e: FirebaseAuthInvalidCredentialsException) {
            AuthState.Error("Invalid email or password.")
        }
        catch (e: FirebaseAuthInvalidUserException) {
            AuthState.Error("No account found for that email.")
        }
        catch (e: Exception){
            AuthState.Error(e.message ?: "Login failed")
        }

    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override suspend fun register(
        email: String,
        password: String,
        fullName: String,
        phoneNumber: String,
        profileImage: Uri
    ): AuthState {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            if(result.user != null){
                val userUid = result.user!!.uid

                val profileImageUrl: String? = if (profileImage != Uri.EMPTY) {
                    val storageRef = firebaseStorage.reference.child("profile_image/$userUid.jpg")
                    storageRef.putFile(profileImage).await()
                    storageRef.downloadUrl.await().toString()
                } else null

                val user = User(
                    email = email,
                    fullName = fullName,
                    phoneNumber = phoneNumber,
                    profileImage = profileImageUrl
                )
                firebaseDb.collection("users")
                    .document(userUid)
                    .set(user)
                    .await()
            }
            AuthState.Authenticated
        } catch(e: Exception){
            AuthState.Error(e.message ?: "Something went wrong")
        }
    }

    override suspend fun getUser(): Resource<User> {
        return try {
            if (currentUser != null) {
                val id = currentUser!!.id

                val userSnapshot = firebaseDb.collection("users")
                    .document(id)
                    .get()
                    .await()

                if (userSnapshot.exists()) {
                    val user = userSnapshot.toObject(User::class.java)
                    if (user != null) {
                        Resource.Success(user)
                    } else {
                        Resource.Error("Failed to map document to object")
                    }
                } else {
                    Resource.Error("User document not found")
                }
            } else {
                Resource.Error("You are not logged in")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Something went wrong")
        }
    }
}