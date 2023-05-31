package com.rick.bookStore.controllers;


import com.rick.bookStore.Services.BookService;
import com.rick.bookStore.data.vo.v1.BookVO;
import com.rick.bookStore.exceptions.ResourceNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/book/v1")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    //@Operation(summary="Finds all books", description = "Finds all books", tags = {"Books"})
    public List<BookVO> findAll() {
        return bookService.findAll();
    }

    @Operation(summary = "Finds a book", description = "Finds a book", tags = {"Book"},responses = {
            @ApiResponse(description = "Not found",responseCode = "404", content = @Content(schema = @Schema(implementation = BookVO.class)))})
    @GetMapping(value = "/{id}")
    public ResponseEntity<BookVO> findById(@PathVariable(value = "id") Long bookId) {
        var book = bookService.findById(bookId);
        if (book == null) {
            throw new ResourceNotFound("Book not found");
        }
        return ResponseEntity.ok(book);
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
