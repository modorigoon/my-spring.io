package me.modorigoon.springio.springdatajpa.user

import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@EntityListeners(AuditingEntityListener::class)
class User(var name: String, var birth: Int) {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null

    @CreationTimestamp
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null

    @ManyToOne
    @CreatedBy
    var createdBy: User? = null

    @ManyToOne
    @LastModifiedBy
    var updatedBy: User? = null
}
