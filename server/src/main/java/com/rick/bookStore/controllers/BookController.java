package com.rick.bookStore.controllers;


import com.rick.bookStore.Services.BookService;
import com.rick.bookStore.data.vo.v1.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public BookVO findById(@PathVariable(value = "id") Long bookId) {
        return bookService.findById(bookId);
    }

    @PostMapping
    public BookVO create(@RequestBody BookVO bookVO) {
        return bookService.create(bookVO);
    }

    @PutMapping
    public BookVO update(@RequestBody BookVO bookVO) {
        return bookService.update(bookVO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long bookId) {
        bookService.delete(bookId);
    }

}
