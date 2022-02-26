package com.hunguyen.spring_app.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int customerId;
    @Column(name = "name", columnDefinition = "nvarchar(50) not null")
    private String name;
    @Column(name = "email", columnDefinition = "nvarchar(100) not null")
    private String email;
    @Column(name = "password", length = 20, nullable = false)
    private String password;
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;
    @Temporal(TemporalType.DATE)
    private Date registeredDate;
    @Column(nullable = false)
    private short status;
}
