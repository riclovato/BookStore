package com.rick.bookStore.controllers;


import com.rick.bookStore.Services.BookService;
import com.rick.bookStore.data.vo.v1.BookVO;
import com.rick.bookStore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/book/v1")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookVO> findAll() {
        return bookService.findAll();
    }
}
