package com.gabdanho.hapibi.data.mapper

import com.gabdanho.hapibi.data.remote.model.ai.Message
import com.gabdanho.hapibi.domain.model.ai.Message as MessageDomain

fun MessageDomain.toData(): Message {
    return Message(
        role = role,
        content = content
    )
}

fun Message.toDomain(): MessageDomain {
    return MessageDomain(
        role = role,
        content = content
    )
}