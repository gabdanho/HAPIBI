package com.gabdanho.hapibi.presentation.mappers

import com.gabdanho.hapibi.presentation.model.AiRole
import com.gabdanho.hapibi.presentation.model.Message
import com.gabdanho.hapibi.domain.model.ai.Message as MessageDomain

/**
 * Преобразует [MessageDomain] в [Message].
 *
 * Если роль не определена, по умолчанию используется [AiRole.SYSTEM].
 *
 * @return [Message] для presentation-слоя.
 */
fun MessageDomain.toPresentation(): Message {
    return Message(
        role = AiRole.fromValue(role) ?: AiRole.SYSTEM,
        content = content
    )
}

/**
 * Преобразует [Message] в [MessageDomain].
 *
 * Если роль не указана, по умолчанию используется тег [AiRole.SYSTEM].
 *
 * @return [MessageDomain] для domain-слоя.
 */
fun Message.toDomain(): MessageDomain {
    return MessageDomain(
        role = role?.tag ?: AiRole.SYSTEM.tag,
        content = content
    )
}