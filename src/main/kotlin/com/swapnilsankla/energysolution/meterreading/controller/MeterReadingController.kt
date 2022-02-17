package com.swapnilsankla.energysolution.meterreading.controller

import com.swapnilsankla.energysolution.meterreading.repository.MeterReadingRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/meter-reading")
class MeterReadingController(val meterReadingRepository: MeterReadingRepository) {
    @PostMapping("/{meterId}")
    fun store(@PathVariable meterId: String, @RequestBody reading: Reading): ResponseEntity<String> {
        meterReadingRepository.save(meterId, reading)
        return ResponseEntity.ok().build()
    }
}

data class Reading(val reading: Double, val time: LocalDateTime)
