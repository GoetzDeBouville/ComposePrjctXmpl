package com.hellcorp.composeprjctxmpl

data class Contact(
    val name: String,
    val fathersName: String? = null,
    val familyName: String,
    val imageRes: Int? = null,
    val isFavorite: Boolean = false,
    val phone: String,
    val address: String,
    val email: String? = null,
)
