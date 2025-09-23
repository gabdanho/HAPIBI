package com.gabdanho.hapibi.presentation.utils

/**
 * Форматирует дату в виде "dd.MM.yyyy", добавляя ведущие нули.
 *
 * @param date Исходная строка с датой, разделённая точками.
 * @return Отформатированная дата или пустая строка, если вход пустой.
 */
fun convertDate(date: String): String {
    return if (date.isNotBlank()) {
        date
            .split(".")
            .joinToString(".") { it.padStart(2, '0') }
    } else ""
}