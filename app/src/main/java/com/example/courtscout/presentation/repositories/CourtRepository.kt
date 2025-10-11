package com.example.courtscout.presentation.repositories

import android.net.Uri
import com.example.courtscout.presentation.models.BoardType
import com.example.courtscout.presentation.models.Court
import com.example.courtscout.presentation.models.CourtType
import com.google.android.gms.maps.model.LatLng

interface CourtRepository {
    suspend fun getAllCourts(): Resource<List<Court>>
    suspend fun getCourt(id: String): Resource<Court>
    suspend fun addCourt(
        name: String,
        type: CourtType,
        boardType: BoardType,
        imageUrl: String,
        location: LatLng
    ): Resource<Unit>
}