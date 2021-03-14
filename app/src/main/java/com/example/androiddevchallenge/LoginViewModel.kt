package com.example.androiddevchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel :ViewModel() {
    var email :String by mutableStateOf("")
    var password : String by mutableStateOf("")

    fun onEmailChange(newEmail :String){
        email = newEmail
    }

    fun onPasswordChange(newPassword :String){
        password = newPassword
    }
}