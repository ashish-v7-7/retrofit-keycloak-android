package com.protium.protiumretrofitkeycloak.keycloak

data class UsersItem(
    val access: Access,
    val createdTimestamp: Long,
    val disableableCredentialTypes: List<Any>,
    val emailVerified: Boolean,
    val enabled: Boolean,
    val id: String,
    val notBefore: Int,
    val requiredActions: List<Any>,
    val totp: Boolean,
    val username: String
)