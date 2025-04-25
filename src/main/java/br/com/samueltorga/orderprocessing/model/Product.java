package br.com.samueltorga.orderprocessing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "deleted")
@Table(name = "products", schema = "order_processing")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ColumnDefault("true")
    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private String name;

    @ColumnDefault("0.0")
    @Column(name = "default_price", nullable = false, precision = 10, scale = 4)
    private BigDecimal defaultPrice;

    @CreatedDate
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

}