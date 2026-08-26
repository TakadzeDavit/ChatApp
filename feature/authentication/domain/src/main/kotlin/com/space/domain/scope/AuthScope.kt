package com.space.domain.scope

sealed interface AuthScope {
    object Parent : AuthScope
    object RoutingScope : AuthScope
    object RegistrationScope : AuthScope
    object LoginScope : AuthScope
}