package com.hunguyen.spring_app.domain;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "oderDetails")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;
//    @Column(name = "oderId", nullable = false)
//    private int orderId;
//    @Column(name = "productId", nullable = false)
//    private int productId;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "unitPrice", nullable = false)
    private double unitPrice;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
}
