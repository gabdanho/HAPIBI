package com.gabdanho.hapibi.presentation

sealed class MainActivityEvent {

    data object RefreshToken : MainActivityEvent()
}