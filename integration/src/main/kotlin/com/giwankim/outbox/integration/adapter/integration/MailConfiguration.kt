package com.giwankim.outbox.integration.adapter.integration

import com.giwankim.outbox.integration.application.required.MailMessage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.channel.QueueChannel
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.MessageChannels
import org.springframework.integration.dsl.Pollers
import org.springframework.integration.jdbc.store.JdbcChannelMessageStore
import org.springframework.integration.jdbc.store.channel.MySqlChannelMessageStoreQueryProvider
import java.time.Duration
import javax.sql.DataSource

@Configuration
class MailConfiguration {
    @Bean
    fun outboxMessageStore(dataSource: DataSource): JdbcChannelMessageStore {
        return JdbcChannelMessageStore(dataSource).apply {
            setChannelMessageStoreQueryProvider(MySqlChannelMessageStoreQueryProvider())
            region = "outbox"
        }
    }

    @Bean
    fun mailInput(): DirectChannel {
        return DirectChannel()
    }

    @Bean
    fun mailOutbox(outboxMessageStore: JdbcChannelMessageStore): QueueChannel {
        return MessageChannels.queue(outboxMessageStore, "mail-outbox").`object`
    }

    @Bean
    fun mailFlow(
        mailInput: DirectChannel,
        mailOutbox: QueueChannel,
        mailSender: MailSender,
    ): IntegrationFlow {
        return IntegrationFlow
            .from(mailInput)
            .channel(mailOutbox)
            .handle(
                MailMessage::class.java,
                { payload, _ ->
                    mailSender.send(payload)
                    null
                },
            ) {
                it.poller(Pollers.fixedDelay(Duration.ofSeconds(1)).transactional())
            }.get()
    }
}
