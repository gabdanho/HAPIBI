package com.gabdanho.hapibi.presentation.mappers

import com.gabdanho.hapibi.presentation.model.Message
import com.gabdanho.hapibi.domain.model.ai.Message as MessageDomain

fun MessageDomain.toPresentation(): Message {
    return Message(
        role = role,
        content = content
    )
}

fun Message.toPresentation(): MessageDomain {
    return MessageDomain(
        role = role,
        content = content
    )
}