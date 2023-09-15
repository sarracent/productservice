package com.cloudx.productservice;

import com.cloudx.productservice.model.Product;
import com.cloudx.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ComponentScan(basePackages = {"com.cloudx"})
@SpringBootApplication
public class Application {

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public void initPricesDatoToH2Database() {
        String startDate1String = "2020-06-14 00:00:00";
        DateTimeFormatter startDate1formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate1 = LocalDateTime.parse(startDate1String, startDate1formatter);

        String endDate1String = "2020-12-31 23:59:59";
        DateTimeFormatter endDate1Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endDate1 = LocalDateTime.parse(endDate1String, endDate1Formatter);

        String startDate2String = "2020-06-14 15:00:00";
        DateTimeFormatter startDate2formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate2 = LocalDateTime.parse(startDate2String, startDate2formatter);

        String endDate2String = "2020-06-14 18:30:00";
        DateTimeFormatter endDate2Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endDate2 = LocalDateTime.parse(endDate2String, endDate2Formatter);

        String startDate3String = "2020-06-15 00:00:00";
        DateTimeFormatter startDate3formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate3 = LocalDateTime.parse(startDate3String, startDate3formatter);

        String endDate3String = "2020-06-15 11:00:00";
        DateTimeFormatter endDate3Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endDate3 = LocalDateTime.parse(endDate3String, endDate3Formatter);

        String startDate4String = "2020-06-15 16:00:00";
        DateTimeFormatter startDate4formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate4 = LocalDateTime.parse(startDate4String, startDate4formatter);

        String endDate4String = "2020-12-31 23:59:59";
        DateTimeFormatter endDate4Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endDate4 = LocalDateTime.parse(endDate4String, endDate4Formatter);

        productRepository.save(Product.builder()
                .brandId(1L)
                .startDate(startDate1)
                .endDate(endDate1)
                .priceList(1L)
                .productId(35455L)
                .priority(0)
                .price(35.50)
                .curr("EUR")
                .build());

        productRepository.save(Product.builder()
                .brandId(1L)
                .startDate(startDate2)
                .endDate(endDate2)
                .priceList(2L)
                .productId(35455L)
                .priority(1)
                .price(25.45)
                .curr("EUR")
                .build());

        productRepository.save(Product.builder()
                .brandId(1L)
                .startDate(startDate3)
                .endDate(endDate3)
                .priceList(3L)
                .productId(35455L)
                .priority(1)
                .price(30.50)
                .curr("EUR")
                .build());

        productRepository.save(Product.builder()
                .brandId(1L)
                .startDate(startDate4)
                .endDate(endDate4)
                .priceList(4L)
                .productId(35455L)
                .priority(1)
                .price(38.95)
                .curr("EUR")
                .build());
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}