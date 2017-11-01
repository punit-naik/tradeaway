package com.tradeaway.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;



    @RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
    public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long> {

        Orders findById(@Param("id") long id);

        //List<Orders> findBySeller(@Param("id") Seller seller);

        List<Orders> findBySellerId(@Param("id") long id);



    }




