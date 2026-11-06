package com.giwankim.outbox.integration.adapter.webapi

import com.giwankim.outbox.integration.application.provided.CompleteOrderRequest
import com.giwankim.outbox.integration.application.provided.OrderCompleter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderApi(
    private val orderCompleter: OrderCompleter,
) {
    @PostMapping
    fun completeOrder(
        @RequestBody completeRequest: CompleteOrderRequest,
    ) {
        orderCompleter.complete(completeRequest)
    }
}
