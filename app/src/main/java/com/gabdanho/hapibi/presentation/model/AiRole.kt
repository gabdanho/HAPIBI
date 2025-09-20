package com.gabdanho.hapibi.presentation.model

import com.gabdanho.hapibi.presentation.constants.AiRoleTags.SYSTEM_TAG
import com.gabdanho.hapibi.presentation.constants.AiRoleTags.USER_TAG

enum class AiRole(val tag: String) {
    SYSTEM(tag = SYSTEM_TAG),
    USER(tag = USER_TAG);

    companion object {
        fun fromValue(role: String?): AiRole? {
            return when(role) {
                SYSTEM_TAG -> SYSTEM
                USER_TAG -> USER
                else -> null
            }
        }
    }
}