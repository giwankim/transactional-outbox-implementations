package com.giwankim.outbox.integration.application.provided

interface OrderCompleter {
    fun complete(completeRequest: CompleteOrderRequest)
}
