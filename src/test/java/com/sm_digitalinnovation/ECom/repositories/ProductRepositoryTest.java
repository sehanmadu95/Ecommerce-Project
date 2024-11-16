package com.sm_digitalinnovation.ECom.repositories;

import com.sm_digitalinnovation.ECom.model.Products;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    private static Products product1,product2,product3,product4;
    private static DateTimeFormatter formatter;
    private static List<Products> list=new ArrayList<>();

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
    public void testSaveProduct(){

        Products p1=productRepository.save(product1);

        Products expectP1=productRepository.findById(product1.getId()).get();

        assertEquals(p1,expectP1);
    }


    private static Date toDate(String dateTime, DateTimeFormatter formatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}