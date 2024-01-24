package mobi.foo.assignment.service;

import mobi.foo.assignment.Constants;
import mobi.foo.assignment.dto.ApiResponse;
import mobi.foo.assignment.dto.ProductDto;
import mobi.foo.assignment.entity.Product;
import mobi.foo.assignment.repository.ProductRepository;
import mobi.foo.assignment.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    // auto wiring productRepository
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ApiResponse saveProduct(ProductRequest request) {
        String message = "";
        String status = "";
        // assuming name is unique
        Optional<Product> optionalProduct = productRepository.findByName(request.getName());
        if (optionalProduct.isPresent()) {
            // product is already exist
            status = Constants.STATUS_FAILED;
            message = "Product " + request.getName() + " is already Exists";
        } else {
            // create new product
            Product product = new Product();
            product.setCategory(request.getCategory());
            product.setPrice(request.getPrice());
            product.setName(request.getName());
            product.setIsAvailable(request.getIsAvailable());
            // save product in the db
            productRepository.save(product);
            status = Constants.STATUS_SUCCESS;
            message = "Product " + request.getName() + " is added successfully";
        }
        return new ApiResponse(status, message);
    }

    public ProductDto fetchAllProducts() {
        ProductDto response = new ProductDto();
        List<Product> products = productRepository.findAll();
        response.setData(products);
        String status = Constants.STATUS_SUCCESS;
        String message = "Products Retrieved";
        if (products.isEmpty()) {
            status = Constants.STATUS_FAILED;
            message = "No Products Found!";
        }
        response.setStatus(status);
        response.setMessage(message);

        return response;
    }

    public ProductDto fetchProductById(Long id) {
        ProductDto productDto = new ProductDto();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            // product exists
            productDto.setStatus(Constants.STATUS_SUCCESS);
            productDto.setMessage("Product Retrieved!");
            productDto.setData(optionalProduct.get());
        } else {
            productDto.setStatus(Constants.STATUS_FAILED);
            productDto.setMessage("Product Not Found!");
        }
        return productDto;
    }

    public ApiResponse deleteProduct(Long id) {
        ApiResponse response = new ApiResponse();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            // delete product and return success status
            productRepository.delete(optionalProduct.get());
            response.setStatus(Constants.STATUS_SUCCESS);
            response.setMessage("Product Deleted Successfully!");
        } else {
            response.setStatus(Constants.STATUS_FAILED);
            response.setMessage("Product Not Found!");
        }

        return response;
    }

    public ApiResponse updateProduct(ProductRequest request, Long productId) {
        String message = "";
        String status = "";
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setCategory(request.getCategory());
            product.setPrice(request.getPrice());
            product.setName(request.getName());
            product.setIsAvailable(request.getIsAvailable());
            productRepository.save(product);
            status = Constants.STATUS_SUCCESS;
            message = "Product " + request.getName() + " is Updated Successfully";
        } else {
            status = Constants.STATUS_FAILED;
            message = "Product " + request.getName() + " is not Found";
        }
        return new ApiResponse(status, message);
    }
}
