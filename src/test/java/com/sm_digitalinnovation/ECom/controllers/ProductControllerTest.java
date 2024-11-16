package com.sm_digitalinnovation.ECom.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm_digitalinnovation.ECom.model.Products;
import com.sm_digitalinnovation.ECom.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.ui.ModelMap;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private static Products product1,product2,product3,product4;
    private static DateTimeFormatter formatter;
    private static List<Products>list=new ArrayList<>();

@BeforeAll
public static void  initilizeObject(){

    System.out.println("BeforeAll executed....");
    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    product1 = new Products(1, "Wireless Earbuds", "High-quality sound with noise cancellation and long battery life",
            "SoundWave", new BigDecimal("59.99"), "Electronics", toDate("2024-11-01 10:00:00", formatter), true, 120);
    product2 = new Products(2, "Smart LED TV 55\"", "4K Ultra HD with smart features and voice control",
            "VisionTech", new BigDecimal("499.99"), "Home Appliances", toDate("2024-10-15 12:00:00", formatter), true, 50);
    product3 = new Products(3, "Gaming Laptop", "Powerful gaming laptop with high-end graphics and 16GB RAM",
            "GamePro", new BigDecimal("1299.99"), "Computers", toDate("2024-11-05 09:00:00", formatter), true, 30);
    product4 = new Products(4, "Electric Kettle", "1.7L capacity with auto shut-off and quick boil feature",
            "HomeEase", new BigDecimal("29.99"), "Kitchen Appliances", toDate("2024-09-20 08:00:00", formatter), true, 200);

    list.add(product1);
    list.add(product2);
    list.add(product3);
    list.add(product4);
}

    @Test
    void testSaveProducts() throws Exception {

        Mockito.when(productService.saveProduct(product1)).thenReturn(product1);

        mockMvc.perform(post("/api/v1/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Wireless Earbuds"));

    }

    @Test
    void testGetProductById() throws Exception {
        Mockito.when(productService.getProductById(1)).thenReturn(product1);
        ResultActions resultActions = mockMvc.perform(get("/api/v1/getById/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value(product1.getBrand()));


    }

    @Test
    public  void testSaveAllProducts() throws Exception {
        Mockito.when(productService.saveAllProducts(list)).thenReturn(list);

        ResultActions actions = mockMvc.perform(post("/api/v1/saveAll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(list)));

        actions.andDo(print())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].name").value("Smart LED TV 55\""));

    }

    @Test
    public void testGetAllProducts() throws Exception {


        Mockito.when(productService.getAllProducts()).thenReturn(list);
        ResultActions actions = mockMvc.perform(get("/api/v1/products"));

        actions.andDo(print())
              .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].name").value("Smart LED TV 55\""));;

    }

    private static Date toDate(String dateTime, DateTimeFormatter formatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}