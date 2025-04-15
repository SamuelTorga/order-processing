package br.com.samueltorga.orderprocessing.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends PagingAndSortingRepository<Test, Integer>, ListCrudRepository<Test, Integer> {
    // Custom query methods can be defined here
}
