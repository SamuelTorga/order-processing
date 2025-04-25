package br.com.samueltorga.orderprocessing.repository;

import br.com.samueltorga.orderprocessing.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}