package org.example.labsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.labsystem.pojo.entity.Book;

import java.util.List;

@Mapper
public interface BookMapper {
    boolean insertBook(Book book);
    List<Book>getList(Book book);

    boolean updateBook(Book book);
    boolean deleteBook(Integer id);
}
