package com.gabdanho.hapibi.presentation.mappers

import com.gabdanho.hapibi.presentation.model.AiRole
import com.gabdanho.hapibi.presentation.model.Message
import com.gabdanho.hapibi.domain.model.ai.Message as MessageDomain

fun MessageDomain.toPresentation(): Message {
    return Message(
        role = AiRole.fromValue(role) ?: AiRole.SYSTEM,
        content = content
    )
}

fun Message.toDomain(): MessageDomain {
    return MessageDomain(
        role = role?.tag ?: AiRole.SYSTEM.tag,
        content = content
    )
}