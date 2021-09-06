package com.lasys.coroutinesdbsample

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class RegistrationUtilTest{

    @Test
    fun `empty userName return false`(){

        val result = RegistrationUtil.validateUserRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `userName already exist return false`(){

        val result = RegistrationUtil.validateUserRegistrationInput(
            "brahmam",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid userName and password and confirm password correct return true`(){

        val result = RegistrationUtil.validateUserRegistrationInput(
            "venkat",
            "1234567",
            "1234567"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `password is empty return false`(){

        val result = RegistrationUtil.validateUserRegistrationInput(
            "brahm",
            "",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password and confirm password is different return false`(){

        val result = RegistrationUtil.validateUserRegistrationInput(
            "brahm",
            "123",
            "456"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password length is lessthan 6 charectors  return false`(){

        val result = RegistrationUtil.validateUserRegistrationInput(
            "brahm",
            "123",
            "456"
        )
        assertThat(result).isFalse()
    }


}