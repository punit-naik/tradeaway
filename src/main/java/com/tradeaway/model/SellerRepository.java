package com.tradeaway.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "seller", path = "seller")
public interface SellerRepository extends PagingAndSortingRepository<Seller, Long> {
    List<User> findByName(@Param("name") String name);
}
