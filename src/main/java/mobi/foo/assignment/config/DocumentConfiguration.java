package mobi.foo.assignment.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import mobi.foo.assignment.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DocumentConfiguration {

    @Value("${openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenApi() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development Environment");

        Contact contact = new Contact();
        contact.setEmail(Constants.CONTACT_EMAIL);
        contact.setName(Constants.CONTACT_NAME);
        contact.setUrl(Constants.CONTACT_URL);

        License license = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .contact(contact)
                .license(license)
                .title("Products API")
                .version("1.0")
                .description("This API exposes endpoints to manage products.");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
