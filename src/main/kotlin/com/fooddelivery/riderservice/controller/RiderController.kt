package com.fooddelivery.riderservice.controller

import com.fooddelivery.riderservice.service.RiderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/rider")
class RiderController {
    @Autowired
    lateinit var riderService: RiderService
    @GetMapping
    fun getRiderAll(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(riderService.getRidersAll())
    }
    @GetMapping("/{id}")
    fun getRiderById(@PathVariable("id") riderId: String): ResponseEntity<Any> {
        return ResponseEntity.ok().body(riderService.getRiderById(riderId.toLong()))
    }
}