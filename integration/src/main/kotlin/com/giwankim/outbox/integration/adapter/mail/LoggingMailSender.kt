package com.giwankim.outbox.integration.adapter.mail

import com.giwankim.outbox.integration.adapter.integration.MailSender
import com.giwankim.outbox.integration.application.required.MailMessage
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import kotlin.random.Random

private val log = KotlinLogging.logger {}

/**
 * Fake mail sender that randomly fails to simulate a broker outage or network error.
 * A failure rolls back the outbox poller's transaction, so the message stays queued and is retried.
 */
@Component
class LoggingMailSender(
    @param:Value("\${mail.sender.failure-rate:0.3}") private val failureRate: Double,
) : MailSender {
    init {
        require(failureRate in 0.0..1.0) { "mail.sender.failure-rate must be in [0.0, 1.0], was $failureRate" }
    }

    override fun send(message: MailMessage) {
        if (Random.nextDouble() < failureRate) {
            log.warn { "Simulated mail delivery failure to=${message.to}, subject='${message.subject}'" }
            throw MailDeliveryException("Simulated broker down / network error while sending to ${message.to}")
        }
        log.info { "Sending mail to=${message.to}, subject='${message.subject}', body='${message.body}'" }
    }
}

class MailDeliveryException(
    message: String,
) : RuntimeException(message)
