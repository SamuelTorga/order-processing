package br.com.samueltorga.orderprocessing.repository;

import br.com.samueltorga.orderprocessing.model.Order;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.hibernate.jpa.HibernateHints.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("""
            SELECT o
            FROM Order o
            JOIN FETCH o.customer
            JOIN FETCH o.orderItems oi
                JOIN FETCH oi.product
            WHERE o.created BETWEEN :startDate AND :endDate
            ORDER BY o.created""")
    @QueryHints(value = {
            @QueryHint(name = HINT_FETCH_SIZE, value = "50"),
            @QueryHint(name = HINT_TIMEOUT, value = "15"),
            @QueryHint(name = HINT_READ_ONLY, value = "true")
    })
    Stream<Order> findOrdersForReportStream(LocalDateTime startDate, LocalDateTime endDate);

}