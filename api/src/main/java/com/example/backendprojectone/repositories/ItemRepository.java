package com.example.backendprojectone.repositories;

import com.example.backendprojectone.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
