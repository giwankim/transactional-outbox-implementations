package com.giwankim.outbox.integration.application.required

import java.io.Serial
import java.io.Serializable

data class MailMessage(
    val subject: String,
    val body: String,
    val to: String,
) : Serializable {
    companion object {
        @Serial
        private const val serialVersionUID: Long = 1L
    }
}
