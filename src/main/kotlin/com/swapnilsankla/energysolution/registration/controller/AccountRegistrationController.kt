package com.swapnilsankla.energysolution.registration.controller

import com.swapnilsankla.energysolution.registration.entity.Account
import com.swapnilsankla.energysolution.registration.repository.AccountRegistrationRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/register")
class AccountRegistrationController(val accountRegistrationRepository: AccountRegistrationRepository) {
    @PostMapping("")
    fun create(@RequestBody registrationRequest: RegistrationRequest): ResponseEntity<String> {
        accountRegistrationRepository.save(Account.Builder(registrationRequest.smartMeterId, registrationRequest.pricePlan).build())
        return ResponseEntity.ok().build()
    }
}

data class RegistrationRequest(val smartMeterId: String, val pricePlan: String)