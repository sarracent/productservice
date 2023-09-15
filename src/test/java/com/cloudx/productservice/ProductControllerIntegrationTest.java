package com.cloudx.productservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "springdoc.info.urlContact=https://www.linkedin.com/in/java-software-engineer/",
        "springdoc.info.mailContact=damiansarracent89@gmail.com",
        "springdoc.info.enabledServerHttps=0",
        "springdoc.info.description=API Rest to retrive product",
        "springdoc.info.urlGithub=https://github.com/sarracent/price-service",
        "springdoc.info.nameContact=Damian Sarracent",
        "springdoc.info.version=1.0.0",
        "springdoc.info.title=product-service"
})
class ProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRequestAt10AMOnDay14() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
                        .header("service-id", "price-service")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultCode").value("0"))
                .andExpect(jsonPath("$.resultMessage").value("OK"))
                .andExpect(jsonPath("$.product").isArray())
                .andExpect(jsonPath("$.product[0].productId").value(35455))
                .andExpect(jsonPath("$.product[0].brandId").value(1))
                .andExpect(jsonPath("$.product[0].priceList").value(1))
                .andExpect(jsonPath("$.product[0].startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.product[0].endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.product[0].price").value(35.5));
    }

    @Test
    void testRequestAt4PMOnDay14() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
                        .header("service-id", "price-service")
                        .param("applicationDate", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultCode").value("0"))
                .andExpect(jsonPath("$.resultMessage").value("OK"))
                .andExpect(jsonPath("$.product").isArray())

                .andExpect(jsonPath("$.product[0].productId").value(35455))
                .andExpect(jsonPath("$.product[0].brandId").value(1))
                .andExpect(jsonPath("$.product[0].priceList").value(2))
                .andExpect(jsonPath("$.product[0].startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.product[0].endDate").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("$.product[0].price").value(25.45))

                .andExpect(jsonPath("$.product[1].productId").value(35455))
                .andExpect(jsonPath("$.product[1].brandId").value(1))
                .andExpect(jsonPath("$.product[1].priceList").value(1))
                .andExpect(jsonPath("$.product[1].startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.product[1].endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.product[1].price").value(35.5));
    }

    @Test
    void testRequestAt9PMOnDay14() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
                        .header("service-id", "price-service")
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultCode").value("0"))
                .andExpect(jsonPath("$.resultMessage").value("OK"))
                .andExpect(jsonPath("$.product").isArray())

                .andExpect(jsonPath("$.product[0].productId").value(35455))
                .andExpect(jsonPath("$.product[0].brandId").value(1))
                .andExpect(jsonPath("$.product[0].priceList").value(1))
                .andExpect(jsonPath("$.product[0].startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.product[0].endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.product[0].price").value(35.5));
    }

    @Test
    void testRequestAt10AMOnDay15() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
                        .header("service-id", "price-service")
                        .param("applicationDate", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultCode").value("0"))
                .andExpect(jsonPath("$.resultMessage").value("OK"))
                .andExpect(jsonPath("$.product").isArray())

                .andExpect(jsonPath("$.product[0].productId").value(35455))
                .andExpect(jsonPath("$.product[0].brandId").value(1))
                .andExpect(jsonPath("$.product[0].priceList").value(3))
                .andExpect(jsonPath("$.product[0].startDate").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("$.product[0].endDate").value("2020-06-15T11:00:00"))
                .andExpect(jsonPath("$.product[0].price").value(30.5))

                .andExpect(jsonPath("$.product[1].productId").value(35455))
                .andExpect(jsonPath("$.product[1].brandId").value(1))
                .andExpect(jsonPath("$.product[1].priceList").value(1))
                .andExpect(jsonPath("$.product[1].startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.product[1].endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.product[1].price").value(35.5));
    }

    @Test
    void testRequestAt9PMOnDay16() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
                        .header("service-id", "price-service")
                        .param("applicationDate", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultCode").value("0"))
                .andExpect(jsonPath("$.resultMessage").value("OK"))
                .andExpect(jsonPath("$.product").isArray())

                .andExpect(jsonPath("$.product[0].productId").value(35455))
                .andExpect(jsonPath("$.product[0].brandId").value(1))
                .andExpect(jsonPath("$.product[0].priceList").value(4))
                .andExpect(jsonPath("$.product[0].startDate").value("2020-06-15T16:00:00"))
                .andExpect(jsonPath("$.product[0].endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.product[0].price").value(38.95))

                .andExpect(jsonPath("$.product[1].productId").value(35455))
                .andExpect(jsonPath("$.product[1].brandId").value(1))
                .andExpect(jsonPath("$.product[1].priceList").value(1))
                .andExpect(jsonPath("$.product[1].startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.product[1].endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.product[1].price").value(35.5));
    }
}