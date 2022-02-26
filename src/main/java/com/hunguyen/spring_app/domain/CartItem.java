package com.hunguyen.spring_app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cartItems")
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(name = "name", columnDefinition = "nvarchar(50) not null")
    private String name;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "unitPrice", nullable = false)
    private double unitPrice;
}
