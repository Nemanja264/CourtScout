package com.example.courtscout.presentation.models

import com.google.type.LatLng

enum class CourtType {
    ASPHALT,
    RUBBER,
    HARDWOOD
}

enum class BoardType {
    GLASS,
    WOOD,
    PLASTIC
}

data class Court (
    val id: String = "",
    val name: String = "",
    val type: CourtType = CourtType.ASPHALT,
    val boardType: BoardType = BoardType.WOOD,
    val location: LatLng? = null,
    val rating: Double = 0.0,
    val city: String = "",
    val country: String = "",
    val reviewCount: Int = 0,
    val gameCount: Int = 0,
    val imageUrl: String = "",
    val coefficient: Double = 20.0,
    val createdAt: Long = System.currentTimeMillis(),
)