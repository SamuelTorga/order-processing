package br.com.samueltorga.orderprocessing.product;

import br.com.samueltorga.orderprocessing.model.Product;
import br.com.samueltorga.orderprocessing.product.dto.ProductCreateRequest;
import br.com.samueltorga.orderprocessing.product.dto.ProductResponse;
import br.com.samueltorga.orderprocessing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductCreateRequest product) {
        Product productToSave = new Product();
        productToSave.setName(product.name());
        productToSave.setDefaultPrice(product.defaultPrice());
        Product productSaved = productRepository.save(productToSave);

        return new ProductResponse(productSaved.getId(), productSaved.getName(),
                productSaved.getDefaultPrice(), productSaved.getCreated(), productSaved.isActive());
    }
}
