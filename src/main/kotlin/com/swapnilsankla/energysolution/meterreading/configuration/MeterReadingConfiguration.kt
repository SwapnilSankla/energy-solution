package com.swapnilsankla.energysolution.meterreading.configuration

import com.influxdb.client.InfluxDBClient
import com.influxdb.client.InfluxDBClientFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MeterReadingConfiguration {
    @Value("\${spring.influx.url}")
    private lateinit var influxDBUrl: String

    @Value("\${spring.influx.bucket}")
    private lateinit var bucket: String

    @Value("\${spring.influx.token}")
    private lateinit var token: String

    @Value("\${spring.influx.org}")
    private lateinit var org: String

    @Bean
    fun influxDBClient(): InfluxDBClient {
        val influxDBClient = InfluxDBClientFactory.create(influxDBUrl, token.toCharArray(), org, bucket)
        influxDBClient.ping()
        return influxDBClient
    }
}