package com.shiraj.searchgithubusers.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiraj.searchgithubusers.domain.error.ErrorType
import com.shiraj.searchgithubusers.domain.error.ExceptionMapper
import com.shiraj.searchgithubusers.domain.event.MessageEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val _message = MutableSharedFlow<MessageEvent>()
    val message get() = _message

    protected val exceptionHandler = ExceptionMapper(::handleError)

    private fun handleError(error: ErrorType) {
        viewModelScope.launch {
            val message = when (error) {
                ErrorType.NO_INTERNET -> MessageEvent.NO_INTERNET
                ErrorType.USER_NOT_FOUND -> MessageEvent.USER_NOT_FOUND
                else -> MessageEvent.GENERIC_MESSAGE
            }
            _message.emit(message)
        }
    }
}