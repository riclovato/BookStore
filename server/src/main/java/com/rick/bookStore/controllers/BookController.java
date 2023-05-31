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

    @Operation(summary = "Finds all books", description = "Finds all books", tags = {"Books"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @GetMapping
    //@Operation(summary="Finds all books", description = "Finds all books", tags = {"Books"})
    public List<BookVO> findAll() {
        return bookService.findAll();
    }


    @Operation(summary = "Finds a book", description = "Finds a book", tags = {"Books"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @GetMapping(value = "/{id}")
    public ResponseEntity<BookVO> findById(@PathVariable(value = "id") Long bookId) {
        var book = bookService.findById(bookId);
        if (book == null) {
            throw new ResourceNotFound("Book not found");
        }
        return ResponseEntity.ok(book);
    }


    @Operation(summary = "Creates a book", description = "adds  a new book", tags = {"Books"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @PostMapping
    public BookVO create(@RequestBody BookVO bookVO) {
        return bookService.create(bookVO);
    }

    @Operation(summary = "Updates a book", description = "changes information about a book", tags = {"Books"}, responses = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @PutMapping
    public BookVO update(@RequestBody BookVO bookVO) {
        return bookService.update(bookVO);
    }


    @Operation(summary = "Deletes a book", description = "Deletes a book by passing id", tags = {
            "Books" }, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long bookId) {
        bookService.delete(bookId);
    }

}
