package com.swapnilsankla.energysolution.registration

import com.swapnilsankla.energysolution.registration.controller.RegistrationRequest
import com.swapnilsankla.energysolution.registration.repository.AccountRegistrationRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AccountRegistrationComponentTest {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Autowired
    lateinit var accountRegistrationRepository: AccountRegistrationRepository

    @Test
    fun `should allow user to register for a given meter id against given energy plan and return success response`() {
        val requestEntity = HttpEntity(RegistrationRequest("1", "abc"))
        val statusCode =
            testRestTemplate.exchange("/register", HttpMethod.POST, requestEntity, String::class.java).statusCode

        Assertions.assertEquals(HttpStatus.OK, statusCode)
    }

    @Test
    fun `should allow user to register for a given meter id against given energy plan and persists information`() {
        val requestEntity = HttpEntity(RegistrationRequest("1", "abc"))
        testRestTemplate.exchange("/register", HttpMethod.POST, requestEntity, String::class.java)

        val insertedRecord = accountRegistrationRepository.getById("1")
        Assertions.assertEquals("1", insertedRecord.smartMeterId)
        Assertions.assertEquals("abc", insertedRecord.pricePlan)
    }
}