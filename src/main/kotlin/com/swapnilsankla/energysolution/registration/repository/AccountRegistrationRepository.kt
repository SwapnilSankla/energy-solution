package com.swapnilsankla.energysolution.registration.repository

import com.swapnilsankla.energysolution.registration.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRegistrationRepository: JpaRepository<Account, String>