package com.gabdanho.hapibi.presentation

/**
 * События, которые может обрабатывать MainActivityViewModel.
 */
sealed class MainActivityEvent {

    /** Обновление токена доступа. */
    data object RefreshToken : MainActivityEvent()
}