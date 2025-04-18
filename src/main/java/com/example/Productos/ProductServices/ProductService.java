package com.example.Productos.ProductServices;


import com.example.Productos.ProductModel.ProductModel;
import com.example.Productos.ProductRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ArrayList<ProductModel> obtenerProductos(){
        return  (ArrayList <ProductModel>)productRepository.findAll();
    }
    public ProductModel guardarProducto (ProductModel producto){
        return productRepository.save(producto);
    }

    public  ProductModel actualizarProducto(ProductModel producto){
        return  productRepository.save(producto);
    }

    public Optional<ProductModel> obtenerid(Long id) {
        return productRepository.findById(id);  // Busca el producto por ID
    }

    public boolean eliminarProducto(Long id){
        try {
         productRepository.deleteById(id);
         return true;
        }catch (Exception err){
          return false;
        }
    }

}
