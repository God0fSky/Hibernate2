package com.example.hibernate2.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "store")
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int count;

    @ManyToOne
    @JoinColumn(name = "fk_order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "fk_product_id", nullable = false)
    private Product product;

}
