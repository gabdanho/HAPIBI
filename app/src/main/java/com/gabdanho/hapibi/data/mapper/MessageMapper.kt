package com.gabdanho.hapibi.data.mapper

import com.gabdanho.hapibi.data.remote.model.ai.Message
import com.gabdanho.hapibi.domain.model.ai.Message as MessageDomain

/**
 * Преобразует доменную модель сообщения в сетевую модель.
 *
 * @receiver [MessageDomain] Доменная модель сообщения.
 * @return [Message] Сетевая модель.
 */
fun MessageDomain.toData(): Message {
    return Message(
        role = role,
        content = content
    )
}

/**
 * Преобразует сетевую модель сообщения в доменную модель.
 *
 * @receiver [Message] Сетевая модель.
 * @return [MessageDomain] Доменная модель.
 */
fun Message.toDomain(): MessageDomain {
    return MessageDomain(
        role = role,
        content = content
    )
}