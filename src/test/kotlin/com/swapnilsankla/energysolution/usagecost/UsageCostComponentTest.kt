package com.swapnilsankla.energysolution.usagecost

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsageCostComponentTest {
    @Autowired
    private lateinit var client: TestRestTemplate

    @Test
    fun `usage cost should return OK response`() {
        val actualStatusCode = client.exchange(
            "/cost-usage/meters/1?days=7",
            HttpMethod.GET,
            null,
            Any::class.java
        ).statusCode

        Assertions.assertEquals(HttpStatus.OK, actualStatusCode)
    }

    @Test
    fun `usage cost should return actual usage cost for the given number of days`() {
        val actualCost = client.exchange(
            "/cost-usage/meters/1?days=7",
            HttpMethod.GET,
            null,
            Double::class.java
        ).body

        Assertions.assertEquals(0.0, actualCost!!)
    }
}
