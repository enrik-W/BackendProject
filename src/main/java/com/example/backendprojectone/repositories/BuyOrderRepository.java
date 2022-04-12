package com.example.backendprojectone.repositories;

import com.example.backendprojectone.models.BuyOrder;
import org.springframework.data.repository.CrudRepository;

public interface BuyOrderRepository extends CrudRepository<BuyOrder, Long> {
     Iterable<BuyOrder> findAllByCustomer_Id(long customer_id);
}
