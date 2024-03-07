package com.mikeshen.countrydemo1.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Hipo {
    private String alpha_two_code;
    private List<String> web_pages;
    private String state_province;
    private String name;
    private List<String> domains;
    private String country;
}
