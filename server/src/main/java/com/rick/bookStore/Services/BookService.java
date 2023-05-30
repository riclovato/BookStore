package com.rick.bookStore.Services;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.rick.bookStore.data.vo.v1.BookVO;
import com.rick.bookStore.mapper.DozerMapper;
import com.rick.bookStore.model.Book;
import com.rick.bookStore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
}
