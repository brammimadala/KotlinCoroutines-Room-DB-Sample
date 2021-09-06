package com.lasys.coroutinesdbsample

object RegistrationUtil {

    private val existingUsers = listOf<String>("brahmam", "madala")

    /*
    * The input is not valid if...
    * -----------------------
    *1.the userName/Password empty
    *2.the userName already Taken
    *3.the confirm password is not same as the real password
    *4.the password length contains less than 6 digits
    *
    *
    * */
    fun validateUserRegistrationInput(
        userName: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            return false
        }
        if (userName in existingUsers){
            return false
        }
        if (password != confirmPassword){
            return false
        }
        if(password.count { it.isDigit() } <= 6){
            return false
        }
        return true
    }
}