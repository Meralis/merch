package org.klim.istock.repository;

import org.klim.istock.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Integer>{

}
