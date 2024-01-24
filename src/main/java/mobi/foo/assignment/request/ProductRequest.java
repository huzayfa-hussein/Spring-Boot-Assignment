package mobi.foo.assignment.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Validation <a href="https://www.bezkoder.com/spring-boot-validate-request-body/">reference</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Name is required!")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    public String name;

    @NotBlank(message = "Category is required!")
    @Size(min = 3, max = 100, message = "Category must be between 3 and 100 characters")
    public String category;

    @DecimalMin(value = "0.0", message = "Price cannot be less than 0")
    public Double price;

    public Boolean isAvailable;
}
