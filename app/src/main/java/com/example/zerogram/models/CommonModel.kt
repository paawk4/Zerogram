package com.example.zerogram.models

data class CommonModel(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    var state: String = "",
    var photoUrl: String = "empty",
    var phone: String = "",

    var text: String = "",
    var type: String = "",
    var from: String = "",
    var timeStamp: Any = ""

) {
    override fun equals(other: Any?): Boolean {
        return (other as CommonModel).id == id
    }
}