package com.giwankim.outbox.integration.application.required

import org.springframework.integration.annotation.MessagingGateway

/**
 * Entry point for requesting a mail. Messages are stored in the outbox
 * within the caller's transaction and delivered asynchronously.
 */
@MessagingGateway(defaultRequestChannel = "mailInput")
interface MailGateway {
    fun send(message: MailMessage)
}
