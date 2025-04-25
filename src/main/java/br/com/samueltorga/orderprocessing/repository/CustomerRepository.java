package br.com.samueltorga.orderprocessing.repository;

import br.com.samueltorga.orderprocessing.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}