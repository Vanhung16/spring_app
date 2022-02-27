package com.hunguyen.spring_app.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
//    @Column(name = "customerId", nullable = false)
//    private int customerId;
    @Column(name = "amount", nullable = false)
    private double amount;
    @Column(name = "status", nullable = false)
    private short status;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

}
