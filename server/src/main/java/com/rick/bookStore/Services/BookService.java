package com.rick.bookStore.Services;

import com.rick.bookStore.data.vo.v1.BookVO;
import com.rick.bookStore.mapper.DozerMapper;
import com.rick.bookStore.model.Book;
import com.rick.bookStore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
        Optional<Book> bookOptional = repository.findById(id);
        Book book = bookOptional.orElse(null);
        if(book !=null) {
            BookVO bookVO = DozerMapper.parseObject(book, BookVO.class);
            return bookVO;
        }else {
            return null;
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
