package com.mytests.spring.springDataArraysTest;

import com.mytests.spring.springDataArraysTest.model.MyArrays;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MyArraysService {

    private final MyArraysRepository repository;

    public MyArraysService(MyArraysRepository repository) {
        this.repository = repository;
    }

    public void populateDB(){

        MyArrays e1 = new MyArrays( new int[]{111,222,0}, new String[]{null,null,"foo"}, List.of("",""), new Date[]{new Date(122,2,21), new Date()},"element");

        repository.save(e1);
    }
    public void testArrayAndArrayListFunctions(){
        List<MyArrays> result = repository.arrayTest();
        System.out.println("===================================================");
        System.out.println("Test array() and array_list() functions:");
        for (MyArrays e : result) {
            System.out.println(e);
        }
    }
    public void testArrayAndArrayListFunctionsWithArgs(){
        List<MyArrays> result = repository.arrayWithArgTest();
        System.out.println("===================================================");
        System.out.println("Test array() and array_list() functions taking arguments:");
        for (MyArrays e : result) {
            System.out.println(e);
        }
    }
    public void testWhereInSelectClause(){
        List<String> result = repository.testWhereInSelectClause();
        System.out.println("===================================================");
        System.out.println("Test 'where' in select clause:");
        System.out.println(result);
        System.out.println("and: ");
        List<String> result1 = repository.testWhereInSelectClause0("element");
        System.out.println(result1);
    }
    public void testArrayAggWithinGroup(){
        List<String> result = repository.testArrayAggWithinGroup();
        System.out.println("===================================================");
        System.out.println("Test 'array agg' within group:");
        for (String e : result) {
            System.out.println(e);
        }
    }
    public void testArrayAggDistinct(){
        List<String> result = repository.testArrayAggDistinct();
        System.out.println("===================================================");
        System.out.println("Test 'array_agg' with distinct:");
        for (String e : result) {
            System.out.println(e);
        }
    }
    public void testArrayPosition(){
        List<MyArrays> result = repository.testArrayPosition(1);
        System.out.println("===================================================");
        System.out.println("Test 'array position' with 'null' as argument :");
        for (MyArrays e : result) {
            System.out.println(e);
        }
    }
    public void testArrayPositions(){
        List<Integer> result = repository.testArrayPositions();
        System.out.println("===================================================");
        System.out.println("Test 'array_positions' and array_concat() functions: :");
            System.out.println(result);
    }
    public void testArrayContainsNullable(){
        List<Integer> result = repository.testArrayContainsNullable(new String[]{"foo", "bar"});
        System.out.println("===================================================");
        System.out.println("Test 'array_contains_nullable' function:");
            System.out.println(result);
    }
    public void testArrayConcat(){
        List<Integer> result = repository.testArrayConcat();
        System.out.println("===================================================");
        System.out.println("Test 'array_concat()' and 'array_contains' functions: ");
        System.out.println(result);
    }
    public void testArrayConcatAndArrayGet(){
        List<MyArrays> result = repository.testArrayConcat2();
        System.out.println("===================================================");
        System.out.println("Test 'array_concat()' and 'array_get' functions: ");
        result.forEach(e -> System.out.println(e));
    }

    public void testArrayContainsArrays(){
        List<MyArrays> result = repository.testArrayContainsArray();
        System.out.println("===================================================");
        System.out.println("Test 'array_contains' with arrays as arguments :");
        for (MyArrays e : result) {
            System.out.println(e);
        }
    }

    public void testNullArgs(){
        List<String> result = repository.testNulls();
        System.out.println("===================================================");
        System.out.println("Test nulls as arguments of array() and array_concat():");
        for (String e : result) {
            System.out.println(e);
        }
    }
}
