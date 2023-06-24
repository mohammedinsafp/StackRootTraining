package com.ust.SpringReactive.Mongo.Repository;

import com.ust.SpringReactive.Mongo.Dto.ProductDto;
import com.ust.SpringReactive.Mongo.Entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
    Flux<ProductDto> findByPriceBetween(Range<Double> closed);
}
