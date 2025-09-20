package com.gabdanho.hapibi.presentation.navigation.model.nav_type

import com.gabdanho.hapibi.presentation.model.Friend
import kotlinx.serialization.KSerializer

/**
 * [NavType] для передачи объектов [Friend] между экранами.
 */
class FriendNavType(serializer: KSerializer<Friend> = Friend.serializer()) :
    NavTypeSerializer<Friend>(serializer = serializer)