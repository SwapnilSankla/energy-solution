package com.swapnilsankla.energysolution.meterreading

import com.swapnilsankla.energysolution.extensions.utc
import com.swapnilsankla.energysolution.meterreading.controller.Reading
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeterReadingComponentTest {
    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `should be able to store the readings for a given meter id`() {
        val date = LocalDateTime.now().utc()!!
        val requestEntity = HttpEntity<Reading>(Reading(3.0, date))
        val actualResponseCode = testRestTemplate.exchange("/meter-reading/12345", HttpMethod.POST, requestEntity, String::class.java).statusCode

        Assertions.assertEquals(HttpStatus.OK, actualResponseCode)
    }
}