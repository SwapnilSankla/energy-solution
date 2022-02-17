package com.swapnilsankla.energysolution.registration.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name ="Account")
@Table(name = "Account")
open class Account {
    @Id
    lateinit var smartMeterId: String
    @Column(nullable = false) lateinit var pricePlan: String

    class Builder(private val smartMeterId: String, private val pricePlan: String) {
        fun build(): Account {
            val account = Account()
            account.smartMeterId = smartMeterId
            account.pricePlan = pricePlan
            return account
        }
    }
}