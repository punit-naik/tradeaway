package com.tradeaway.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "inventory", path = "inventory")
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long> {}
