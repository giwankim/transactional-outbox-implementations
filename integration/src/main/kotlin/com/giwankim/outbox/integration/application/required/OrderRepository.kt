package com.giwankim.outbox.integration.application.required

import com.giwankim.outbox.integration.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>
