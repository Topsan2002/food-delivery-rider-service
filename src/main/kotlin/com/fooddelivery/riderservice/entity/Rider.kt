package com.fooddelivery.riderservice.entity

import jakarta.persistence.*
import lombok.*
import java.time.LocalDateTime


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "`rider`")
data class Rider(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="rider_id", nullable = false)
    var riderId:Long? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "firstname")
    var firstname: String? = null,

    @Column(name = "lastname")
    var lastname: String? = null,

    @Column(name = "address")
    var address: String? = null,

    @Column(name = "phone")
    var phone: String? = null,

    @Column(name = "create_at")
    val createdAt: LocalDateTime,

    @Column(name = "update_at")
    var updatedAt: LocalDateTime,

    )
