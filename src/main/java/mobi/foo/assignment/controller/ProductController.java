package mobi.foo.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import mobi.foo.assignment.dto.ApiResponse;
import mobi.foo.assignment.dto.ProductDto;
import mobi.foo.assignment.request.ProductRequest;
import mobi.foo.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product", description = "Products API")
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

    @Operation(
            summary = "Retrieve a Product by Id",
            description = "Fetch a Product object by specifying its id",
            tags = {"products", "get"}
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ProductDto.class), mediaType = "application/json")})
    })
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
