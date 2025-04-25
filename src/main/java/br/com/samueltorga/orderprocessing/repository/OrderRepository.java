package br.com.samueltorga.orderprocessing.repository;

import br.com.samueltorga.orderprocessing.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("""
            SELECT o
            FROM Order o
            JOIN FETCH o.customer
            JOIN FETCH o.orderItems oi
                JOIN FETCH oi.product
            WHERE o.created BETWEEN :startDate AND :endDate""")
    Stream<Order> findOrdersForReportStream(LocalDateTime startDate, LocalDateTime endDate);

}