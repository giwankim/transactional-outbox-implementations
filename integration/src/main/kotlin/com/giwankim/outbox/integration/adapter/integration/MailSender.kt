package com.giwankim.outbox.integration.adapter.integration

import com.giwankim.outbox.integration.application.required.MailMessage

/**
 * Delivery endpoint invoked by the outbox poller in [MailConfiguration].
 * Not a core port: the application only knows [com.giwankim.outbox.integration.application.required.MailGateway].
 */
interface MailSender {
    fun send(message: MailMessage)
}
