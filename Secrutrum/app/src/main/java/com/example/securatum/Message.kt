package com.example.securatum

class Message {
    var messege:String? = null
    var senderId:String? = null

    constructor(){}

    constructor(messege: String?,senderId: String?)
    {
        this.messege = messege
        this.senderId = senderId
    }
}