package com.tradeaway.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "buyer", path = "buyer")
public interface BuyerRepository extends PagingAndSortingRepository<Buyer, Long> {
    List<User> findByName(@Param("name") String name);
    List<User> findByUserName(@Param("userName") String userName);
}
