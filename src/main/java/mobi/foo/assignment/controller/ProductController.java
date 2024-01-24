package mobi.foo.assignment.controller;

import lombok.extern.log4j.Log4j2;
import mobi.foo.assignment.dto.ApiResponse;
import mobi.foo.assignment.dto.ProductDto;
import mobi.foo.assignment.request.ProductRequest;
import mobi.foo.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@Log4j2
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ApiResponse saveProduct(@Validated @RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    @GetMapping("/get")
    public ProductDto fetchProductById(@RequestParam(name = "product_id") Long id) {
        return productService.fetchProductById(id);
    }

    @GetMapping("/get/all")
    public ProductDto fetchALlProducts() {
        return productService.fetchAllProducts();
    }

    @DeleteMapping("/delete")
    public ApiResponse deleteProduct(@RequestParam(name = "product_id") Long id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/update")
    public ApiResponse updateProduct(@Validated @RequestBody ProductRequest productRequest, @RequestParam("product_id") Long productId) {
        return productService.updateProduct(productRequest, productId);
    }
}
