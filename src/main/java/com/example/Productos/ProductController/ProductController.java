package com.example.Productos.ProductController;

import com.example.Productos.ProductModel.ProductModel;
import com.example.Productos.ProductServices.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("producto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ArrayList<ProductModel>> obtenerProducto() {
        return ResponseEntity.ok(productService.obtenerProductos());
    }

    // Crear un producto
    @PostMapping
    public ResponseEntity<ProductModel> guardarProducto(@RequestBody ProductModel producto) {
        ProductModel nuevoProducto = productService.guardarProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> actualizarProducto(@PathVariable Long id, @RequestBody ProductModel producto) {
        Optional<ProductModel> productoExistente = productService.obtenerid(id);

        if (productoExistente.isPresent()) {
            producto.setId(id); // Asegurar que el ID coincide
            ProductModel productoActualizado = productService.actualizarProducto(producto);
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductModel>> obtenerID(@PathVariable("id") Long id) {
        Optional<ProductModel> producto = productService.obtenerid(id);
        return producto.isPresent() ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProductos(@PathVariable("id") Long id) {
        boolean eliminado = productService.eliminarProducto(id);
        return eliminado ? ResponseEntity.ok("Producto eliminado: " + id) : ResponseEntity.notFound().build();
    }
}
