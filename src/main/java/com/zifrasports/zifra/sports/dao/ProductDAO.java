package com.zifrasports.zifra.sports.dao;

import com.zifrasports.zifra.sports.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {
}
