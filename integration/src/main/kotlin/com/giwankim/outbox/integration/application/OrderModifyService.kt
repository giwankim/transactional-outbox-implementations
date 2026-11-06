package com.giwankim.outbox.integration.application

import com.giwankim.outbox.integration.application.provided.CompleteOrderRequest
import com.giwankim.outbox.integration.application.provided.OrderCompleter
import com.giwankim.outbox.integration.application.required.MailGateway
import com.giwankim.outbox.integration.application.required.MailMessage
import com.giwankim.outbox.integration.application.required.OrderRepository
import com.giwankim.outbox.integration.domain.Order
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private val log = KotlinLogging.logger {}

@Service
@Transactional
class OrderModifyService(
    private val orderRepository: OrderRepository,
    private val mailGateway: MailGateway,
) : OrderCompleter {
    override fun complete(completeRequest: CompleteOrderRequest) {
        log.info { "Completing order for ${completeRequest.email}" }

        val order = orderRepository.save(Order(completeRequest.amount, completeRequest.email))
        log.info { "Order saved with ID: ${order.id}" }

        val message =
            MailMessage(
                subject = "Order ID ${order.id} completed",
                body = "Your order is registered in our system and will be processed.",
                to = order.customerEmail,
            )
        log.info { "Sending email for order ${order.id}" }
        mailGateway.send(message)
    }
}
