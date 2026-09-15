package marek.horvath.java.eshopify.controller;

import marek.horvath.java.eshopify.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import(ProductControllerValidationTest.TestConfig.class)
class ProductControllerValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createProductWithEmptyPayloadReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isBadRequest());
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        ProductService productService() {
            return Mockito.mock(ProductService.class);
        }
    }
}
