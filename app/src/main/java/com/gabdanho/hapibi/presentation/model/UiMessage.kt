package com.gabdanho.hapibi.presentation.model

data class UiMessage(
    val id: Long = System.currentTimeMillis(),
    val message: StringResNamePresentation? = null,
    val details: String = "",
)
