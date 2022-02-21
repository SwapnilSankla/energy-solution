package com.swapnilsankla.energysolution.usagecost.controller

import com.swapnilsankla.energysolution.usagecost.exception.PricePlanNotAttachedToMeterException
import com.swapnilsankla.energysolution.usagecost.service.UsageCostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cost-usage")
class UsageCostController(val usageCostService: UsageCostService) {
    @GetMapping("/meters/{meterId}")
    fun get(@PathVariable meterId: String, @RequestParam days: Int): ResponseEntity<Double> {
        // TODO: Controller advice
        return try {
            ResponseEntity.ok(usageCostService.cost(meterId))
        } catch(e: PricePlanNotAttachedToMeterException) {
            ResponseEntity.unprocessableEntity().build()
        }
    }
}