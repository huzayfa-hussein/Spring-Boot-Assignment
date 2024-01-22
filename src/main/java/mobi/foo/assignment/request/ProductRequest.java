package mobi.foo.assignment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    public String name;
    public String category;
    public Double price;
    public Boolean isAvailable;
}
