package mobi.foo.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mobi.foo.assignment.dto.ApiResponse;
import mobi.foo.assignment.dto.ProductDto;
import mobi.foo.assignment.dto.ProductResponse;
import mobi.foo.assignment.request.ProductRequest;
import mobi.foo.assignment.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Product", description = "Products API")
@RestController
@RequestMapping("/api/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    Logger logger = LoggerFactory.getLogger(ProductController.class);


    @PostMapping("/create")
    public ApiResponse saveProduct(@Validated @RequestBody ProductRequest productRequest) {
        logger.info("saveProduct {}", productRequest.getName());
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
    public ProductDto fetchALlProducts() throws InterruptedException {
        return productService.fetchAllProducts().join();
    }

    @GetMapping("/v2/get/all")
    public ProductResponse fetchALlProductsV2() throws InterruptedException {
        return productService.fetchAllProductsVersion().join();
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
