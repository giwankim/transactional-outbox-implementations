package com.giwankim.outbox.integration.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "orders")
class Order(
    amount: BigDecimal,
    customerEmail: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
    var amount: BigDecimal = amount
        protected set
    var customerEmail: String = customerEmail
        protected set
}
