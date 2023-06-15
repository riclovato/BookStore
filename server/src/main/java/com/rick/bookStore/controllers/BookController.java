package com.rick.bookStore.controllers;


import com.rick.bookStore.services.BookService;
import com.rick.bookStore.data.vo.v1.BookVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<PagedModel<EntityModel<BookVO>>> findAll(@RequestParam(value = "page", defaultValue = "0")Integer page, @RequestParam(value = "limit", defaultValue = "12")Integer limit, @RequestParam(value = "direction", defaultValue = "asc")String direction)
    {
            var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page,limit,Sort.by(sortDirection,"title"));
        return ResponseEntity.ok(bookService.findAll(pageable));
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
        return ResponseEntity.ok(book);
    }


    @Operation(summary = "Creates a book", description = "adds  a new book", tags = {"Books"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @PostMapping
    @Secured("ROLE_ADMIN")
    public BookVO create(@Valid @RequestBody BookVO bookVO) {
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
