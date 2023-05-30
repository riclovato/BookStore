package com.rick.bookStore.Services;

import com.rick.bookStore.data.vo.v1.BookVO;
import com.rick.bookStore.mapper.DozerMapper;
import com.rick.bookStore.model.Book;
import com.rick.bookStore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    @Autowired
    BookRepository repository;
    @Autowired
    PagedResourcesAssembler<BookVO> assembler;


    public List<BookVO> findAll() {
        var books = repository.findAll();
        var booksVO = DozerMapper.parseListObject(books, BookVO.class);
        return booksVO;
    }

    public BookVO findById(Long id) {
        var book = repository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID!"));
        BookVO bookVO = DozerMapper.parseObject(book, BookVO.class);
        return bookVO;
    }

    public BookVO create(BookVO book) {
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public BookVO update(BookVO book) {
        var entity = repository.findById(book.getKey()).orElseThrow(() -> new RuntimeException("No records found for this"));
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
