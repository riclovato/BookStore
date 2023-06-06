package com.rick.bookStore.services;

import com.rick.bookStore.controllers.BookController;
import com.rick.bookStore.data.vo.v1.BookVO;
import com.rick.bookStore.exceptions.ResourceNotFoundException;
import com.rick.bookStore.mapper.DozerMapper;
import com.rick.bookStore.model.Book;
import com.rick.bookStore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookService {

    @Autowired
    BookRepository repository;
    @Autowired
    PagedResourcesAssembler<BookVO> assembler;


    public PagedModel<EntityModel<BookVO>> findAll(Pageable pageable) {
        var bookPage = repository.findAll(pageable);
        var booksVOPage = bookPage.map(b -> DozerMapper.parseObject(b,BookVO.class));

        booksVOPage.map(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getId())).withSelfRel()));
        Link link = linkTo(methodOn(BookController.class).findAll(pageable.getPageNumber(),pageable.getPageSize(),"asc")).withSelfRel();
        return assembler.toModel(booksVOPage,link);
    }

    public BookVO findById(Long id) {
        Optional<Book> bookOptional = repository.findById(id);
        Book book = bookOptional.orElse(null);
        if (book != null) {
            BookVO bookVO = DozerMapper.parseObject(book, BookVO.class);
            bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
            return bookVO;
        } else {
            throw new ResourceNotFoundException("Book not found");
        }
    }

    public BookVO create(BookVO book) {
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public BookVO update(BookVO book) {
        var entity = repository.findById(book.getId()).orElseThrow(() -> new RuntimeException("No records found for this"));
        entity.setAuthor(book.getAuthor());
        entity.setTitle(book.getTitle());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());

        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public void delete(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this"));
        repository.delete(entity);
    }
}
