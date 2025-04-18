package com.example.Productos.ProductRepository;

import com.example.Productos.ProductModel.ProductModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends CrudRepository <ProductModel,Long> {
    public abstract ArrayList<ProductModel> findByid(Long id);
}
