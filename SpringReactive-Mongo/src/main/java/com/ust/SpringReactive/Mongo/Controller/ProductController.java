package com.ust.SpringReactive.Mongo.Controller;

import com.ust.SpringReactive.Mongo.Dto.ProductDto;
import com.ust.SpringReactive.Mongo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.channels.FileLockInterruptionException;
import java.security.Provider;

@RestController
@RequestMapping("/Products")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/products")
    public Flux<ProductDto> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/product")
    public Mono<ProductDto> getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }
    @GetMapping("/product-range")
    public Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max){
        return productService.getProductInRange(min,max);
    }
    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        System.out.println("controller method called ...");
        return productService.saveProduct(productDtoMono);
    }
    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono,@PathVariable String id){
        return productService.updateProduct(productDtoMono,id);
    }
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id);
    }
}
