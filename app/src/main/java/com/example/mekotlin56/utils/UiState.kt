package com.example.mekotlin56.utils

data class UiState<T>(
    val IsLoading: Boolean = true,
    val error: String? = null,
    val success: T? = null
)