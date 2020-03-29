package com.example.webscraper.data.repository

sealed class Outcome<out T: Any?>

data class Success<out T: Any>(val data: T): Outcome<T>()

data class Failure(val throwable: Throwable): Outcome<Nothing>()