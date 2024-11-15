package org.example.labsystem.service.impl;

import jakarta.annotation.Resource;
import org.example.labsystem.exception.ReadableException;
import org.example.labsystem.mapper.BookMapper;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.entity.Book;
import org.example.labsystem.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
    @Resource
    private BookMapper mapper;
    @Override
    public void addBook(Book book) {
        boolean b = mapper.insertBook(book);
        if (!b) throw new ReadableException(CodeState.INSERT_ERROR);
    }

    @Override
    public List<Book> getList(Book book) {
        return mapper.getList(book);
    }

    @Override
    public List<Book> getBookListById(Book book) {
        return mapper.getList(book);
    }

    @Override
    public void updateBook(Book book) {
       boolean b =  mapper.updateBook(book);
       if (!b) throw new ReadableException(CodeState.UPDATE_ERROR);
    }

    @Override
    public void deleteBook(Integer id) {
        boolean b = mapper.deleteBook(id);
        if (!b) throw new ReadableException(CodeState.DELETE_ERROR);
    }
}
