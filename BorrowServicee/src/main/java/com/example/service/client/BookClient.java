package com.example.service.client;

import com.example.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("BookService")
public interface BookClient {
    @RequestMapping("book/{bid}")
    Book findBookById(@PathVariable("bid") int bid);
}
