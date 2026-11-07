package com.giwankim.outbox.namastack

import java.math.BigDecimal

data class CreateOrderRequest(val customerId: String, val region: String, val amount: BigDecimal)
