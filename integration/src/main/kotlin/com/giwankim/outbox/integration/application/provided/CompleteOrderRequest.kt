package com.giwankim.outbox.integration.application.provided

import java.math.BigDecimal

data class CompleteOrderRequest(
    val amount: BigDecimal,
    val email: String,
)
