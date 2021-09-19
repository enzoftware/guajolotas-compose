package com.enzoftware.guajolotas.core

sealed class ResultState<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultState<T>()
    data class Error(val exception: Exception) : ResultState<Nothing>()

    override fun toString() = when (this) {
        is Success<*> -> "Success[data=$data]"
        is Error -> "Error[Exception=$exception]"
    }

}
