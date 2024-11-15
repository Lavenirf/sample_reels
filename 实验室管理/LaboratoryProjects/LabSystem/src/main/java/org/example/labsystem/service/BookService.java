package org.example.labsystem.service;

import org.example.labsystem.pojo.entity.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    List<Book> getList(Book book);
    List<Book> getBookListById(Book book);

    void updateBook(Book book);
    void deleteBook(Integer id);
}
