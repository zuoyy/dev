package com.zuoyy.devtools.generate.domain;

import lombok.Data;

/**
 * @author zuo
 */
@Data
public class Template {
    private boolean entity;
    private boolean controller;
    private boolean service;
    private boolean repository;
    private boolean query;
    private boolean index;
    private boolean add;
    private boolean detail;
}
