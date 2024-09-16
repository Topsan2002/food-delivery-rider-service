package com.fooddelivery.riderservice.service

import com.fooddelivery.riderservice.entity.Rider
import com.fooddelivery.riderservice.repository.RiderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RiderService {
    @Autowired
    lateinit var repository: RiderRepository
    fun getRidersAll(): List<Rider?> {
        return repository.findAll()
    }
    fun getRiderById(riderId: Long): Rider? {
        return repository.findRiderByRiderId(riderId)
    }
}