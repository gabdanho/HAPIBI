package com.gabdanho.hapibi.presentation.screens.congratulation

sealed class CongratulationScreenEvent {

    data object GenerateCongratulation : CongratulationScreenEvent()

    data object FixCongratulation: CongratulationScreenEvent()

    data object ClearMessage : CongratulationScreenEvent()

    data class InitPersonName(val name: String) : CongratulationScreenEvent()

    data class UpdateHolidayInput(val value: String) : CongratulationScreenEvent()

    data class UpdatePersonStatusInput(val value: String) : CongratulationScreenEvent()

    data class UpdateImportantWordsInput(val value: String) : CongratulationScreenEvent()

    data class UpdateStyleInput(val value: String) : CongratulationScreenEvent()

    data class UpdateFixProblemsInput(val value: String) : CongratulationScreenEvent()
}