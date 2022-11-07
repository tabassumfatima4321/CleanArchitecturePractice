package com.example.cleanarchitecture.app.login.domain.data

data class LoginRequest(val username: String?, val password: String?)
data class Authorization(val accessToken:String
,val refreshToken:String)
