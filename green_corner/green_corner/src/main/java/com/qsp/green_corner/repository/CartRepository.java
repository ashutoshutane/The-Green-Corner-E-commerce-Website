package com.qsp.green_corner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.green_corner.entity.Cart;
import com.qsp.green_corner.entity.Plant;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
