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
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(name = "name", columnDefinition = "nvarchar(100) not null" )
    private String name;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "unitPrice", nullable = false)
    private int unitPrice;
    @Column(name = "image", length = 200)
    private String image;
    @Column(name = "description", columnDefinition = "nvarchar(500) not null")
    private String description;
    @Column(name = "discount", nullable = false)
    private double discount;
    @Temporal(TemporalType.DATE)
    private Date enteredDate;
    @Column(name = "status", nullable = false)
    private short status;
//    @Column(name = "CategoryId", nullable = false)
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
}
