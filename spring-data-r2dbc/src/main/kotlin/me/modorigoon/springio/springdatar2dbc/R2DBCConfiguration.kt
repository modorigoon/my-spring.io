package me.modorigoon.springio.springdatar2dbc

import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import io.r2dbc.h2.H2ConnectionOption
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EnableR2dbcRepositories
@EnableTransactionManagement
class R2DBCConfiguration {

    @Bean
    fun connectionFactory() = H2ConnectionFactory(
        H2ConnectionConfiguration.builder()
            .inMemory("test")
            .property(H2ConnectionOption.DB_CLOSE_DELAY, "-1")
            .build()
    )

    @Bean
    fun r2dbcEntityTemplate(connectionFactory: ConnectionFactory) = R2dbcEntityTemplate(connectionFactory)

    @Bean
    fun init(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory)
        val populator = CompositeDatabasePopulator()
        populator.addPopulators(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
        initializer.setDatabasePopulator(populator)
        return initializer
    }
}
