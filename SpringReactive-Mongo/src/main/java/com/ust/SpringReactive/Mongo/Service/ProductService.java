package com.ust.SpringReactive.Mongo.Service;

import com.ust.SpringReactive.Mongo.Dto.ProductDto;
import com.ust.SpringReactive.Mongo.Repository.ProductRepository;
import com.ust.SpringReactive.Mongo.Utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts(){
        return productRepository.findAll().map(AppUtils::entityToDto);
    }
    public Mono<ProductDto> getProduct(String id){
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }
    public Flux<ProductDto> getProductInRange(double min,double max){
        return productRepository.findByPriceBetween(Range.closed(min,max));
    }
    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        System.out.println("service method called ...");
        return  productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);
    }
    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String id){
        return productRepository.findById(id)
                .flatMap(p->productDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }
    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}
