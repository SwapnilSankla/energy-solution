package com.swapnilsankla.energysolution.meterreading.repository

import com.influxdb.client.InfluxDBClient
import com.influxdb.client.domain.WritePrecision
import com.influxdb.client.write.Point
import com.swapnilsankla.energysolution.extensions.utc
import com.swapnilsankla.energysolution.meterreading.controller.Reading
import org.springframework.stereotype.Repository
import java.time.ZoneOffset

@Repository
class MeterReadingRepository(val influxDBClient: InfluxDBClient) {
    fun save(meterId: String, reading: Reading) {
        val writeApi = influxDBClient.writeApiBlocking
        val point = Point
            .measurement("meter-reading")
            .addField("reading", reading.reading)
            .addTag("meterId", "meterId:$meterId")
            .time(reading.time.utc()!!.toInstant(ZoneOffset.UTC).toEpochMilli(), WritePrecision.MS)
        writeApi.writePoint(point)
    }
}