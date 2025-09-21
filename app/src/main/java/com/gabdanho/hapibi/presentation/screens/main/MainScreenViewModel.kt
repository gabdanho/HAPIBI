package com.gabdanho.hapibi.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.gabdanho.hapibi.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel для [MainScreen].
 *
 * @property navigator Навигатор, который управляет переходами между экранами приложения.
 */
@HiltViewModel
class MainScreenViewModel @Inject constructor(
    val navigator: Navigator,
) : ViewModel()