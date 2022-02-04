package com.protium.protiumretrofitkeycloak.keycloak

data class Access(
    val impersonate: Boolean,
    val manage: Boolean,
    val manageGroupMembership: Boolean,
    val mapRoles: Boolean,
    val view: Boolean
)