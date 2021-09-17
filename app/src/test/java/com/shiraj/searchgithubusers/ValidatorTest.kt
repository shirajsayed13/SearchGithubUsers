package com.shiraj.searchgithubusers

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {

    @Test
    fun whenInputIsValid() {
        val searchKeyword = "Shiraj"
        val result = Validator.validateInput(searchKeyword)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsInvalid() {
        val searchKeyword = ""
        val result = Validator.validateInput(searchKeyword)
        assertThat(result).isEqualTo(false)
    }

}