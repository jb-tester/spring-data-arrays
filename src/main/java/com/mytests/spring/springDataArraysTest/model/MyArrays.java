package com.mytests.spring.springDataArraysTest.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * *
 * <p>Created by irina on 1/24/2024.</p>
 * <p>Project: spring-data-arrays</p>
 * <a href="https://youtrack.jetbrains.com/issue/IDEA-342315/">IDEA-342315: false error is-displayed for Basic entity field that maps the array type column</a>
 * <p> </p>
 */
@Entity
@Table(name = "my_arrays", schema = "jbtests", catalog = "postgres")
public class MyArrays {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "int_arrays")
    private int[] intArrays;
    @Basic
    @Column(name = "str_arrays_one")
    private String[] strArraysOne;
    @Basic
    @Column(name = "str_arrays_two")
    private List<String> strArraysTwo;
    @Basic
    @Column(name = "dates_array")
    private Date[] datesArray;
    @Basic
    @Column(name = "str")
    private String str;

    public int[] getIntArrays() {
        return intArrays;
    }

    public void setIntArrays(int[] intArrays) {
        this.intArrays = intArrays;
    }

    public String[] getStrArraysOne() {
        return strArraysOne;
    }

    public void setStrArraysOne(String[] strArraysOne) {
        this.strArraysOne = strArraysOne;
    }

    public List<String> getStrArraysTwo() {
        return strArraysTwo;
    }

    public void setStrArraysTwo(List<String> strArraysTwo) {
        this.strArraysTwo = strArraysTwo;
    }

    public Date[] getDatesArray() {
        return datesArray;
    }

    public void setDatesArray(Date[] datesArray) {
        this.datesArray = datesArray;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Integer getId() {
        return id;
    }

    public MyArrays(int[] intArrays, String[] strArraysOne, List<String> strArraysTwo, Date[] datesArray, String str) {
        this.intArrays = intArrays;
        this.strArraysOne = strArraysOne;
        this.strArraysTwo = strArraysTwo;
        this.datesArray = datesArray;
        this.str = str;
    }

    public MyArrays() {
    }

    @Override
    public String toString() {
        return "MyArrays{" +
               "id=" + id +
               ", intArrays=" + Arrays.toString(intArrays) +
               ", strArraysOne=" + Arrays.toString(strArraysOne) +
               ", strArraysTwo=" + strArraysTwo +
               ", datesArray=" + Arrays.toString(datesArray) +
               ", str='" + str + '\'' +
               '}';
    }
}
