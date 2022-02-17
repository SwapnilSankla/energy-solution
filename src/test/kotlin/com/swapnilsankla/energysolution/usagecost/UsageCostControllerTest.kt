package com.swapnilsankla.energysolution.usagecost

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.swapnilsankla.energysolution.usagecost.controller.UsageCostController
import com.swapnilsankla.energysolution.usagecost.exception.PricePlanNotAttachedToMeterException
import com.swapnilsankla.energysolution.usagecost.service.UsageCostService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class UsageCostControllerTest {
    @Test
    fun `should return error if plan is not attached to the given meter`() {
        jacksonObjectMapper().findAndRegisterModules().writeValueAsString(LocalDateTime.now())

        val usageCostService = DummyUsageCostService(false)
        val usageCostController = UsageCostController(usageCostService)

        val costResponse = usageCostController.get("1", 7)

        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, costResponse.statusCode)
    }

    @Test
    fun `should return cost if plan is attached to the given meter`() {
        val usageCostService = DummyUsageCostService()
        val usageCostController = UsageCostController(usageCostService)

        val costResponse = usageCostController.get("1", 7)

        Assertions.assertEquals(10.0, costResponse.body)
    }
}

class DummyUsageCostService(val pricePlanAttached: Boolean = true) : UsageCostService() {
    override fun cost(meterId: String): Double {
        return if (pricePlanAttached) 10.0
        else throw PricePlanNotAttachedToMeterException("")
    }
}