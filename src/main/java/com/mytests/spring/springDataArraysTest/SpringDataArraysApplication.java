package com.mytests.spring.springDataArraysTest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataArraysApplication implements CommandLineRunner {

    private final MyArraysService service;

    public SpringDataArraysApplication(MyArraysService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataArraysApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        service.testArrayAndArrayListFunctions();
        service.testArrayAndArrayListFunctionsWithArgs();
        service.testWhereInSelectClause();
        service.testArrayAggWithinGroup();
        service.testArrayAggDistinct();
        service.testArrayPosition();
        service.testArrayPositions();
        service.testArrayContainsNullable();
        service.testArrayConcat();
        service.testArrayConcatAndArrayGet();
        service.testArrayContainsArrays();
        service.testNullArgs();
        System.out.println("===================================================");
    }
}
