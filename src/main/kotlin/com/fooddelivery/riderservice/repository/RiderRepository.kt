package com.fooddelivery.riderservice.repository

import com.fooddelivery.riderservice.entity.Rider
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RiderRepository: JpaRepository<Rider?, Long> {
    fun findRiderByRiderId(id: Long): Rider?
}