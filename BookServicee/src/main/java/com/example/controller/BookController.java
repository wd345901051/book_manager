package com.example.controller;

import com.example.entity.Book;
import com.example.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BookController {
    @Resource
    BookService service;

    @RequestMapping("book/{bid}")
    public Book findBookById(@PathVariable("bid") int bid){
        return service.getBookById(bid);
    }
}
