package com.gabdanho.hapibi.presentation.utils

fun convertDate(date: String): String {
    return date
        .split(".")
        .joinToString(".") { it.padStart(2, '0') }
}