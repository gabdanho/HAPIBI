package com.gabdanho.hapibi.presentation.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gabdanho.hapibi.presentation.theme.AppTheme

/**
 * Контейнер с Pull-to-Refresh функционалом.
 *
 * @param isRefreshing Флаг состояния обновления.
 * @param onRefresh Лямбда вызывается при pull-to-refresh.
 * @param modifier Модификатор для настройки компонента.
 * @param content Содержимое, которое будет отображаться внутри контейнера.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshContainer(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    if (enabled) {
        val refreshState = rememberPullToRefreshState()

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            state = refreshState,
            onRefresh = onRefresh,
            indicator = {
                Indicator(
                    state = refreshState,
                    isRefreshing = isRefreshing,
                    color = AppTheme.colors.azureA100,
                    containerColor = AppTheme.colors.white,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            },
            modifier = modifier
        ) {
            content()
        }
    } else content()
}