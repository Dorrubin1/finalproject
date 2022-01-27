package dor.rubin.dorproject.models

import com.google.firebase.database.IgnoreExtraProperties

// class last user
@IgnoreExtraProperties
data class AppUser(
    val email: String? = null,
    val userId: String? = null
)
