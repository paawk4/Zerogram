package com.example.zerogram.models

data class User(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    var status: String = "",
    var photoUrl: String = "",
    var phone:String = ""
)