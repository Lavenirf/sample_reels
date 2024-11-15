package org.example.labsystem.controller;

import jakarta.annotation.Resource;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.common.Result;
import org.example.labsystem.pojo.entity.Book;
import org.example.labsystem.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Resource
    private BookService service;
    @PostMapping
    public Result<?> insertBook(@RequestBody Book book){
        service.addBook(book);
        return Result.Success(CodeState.SUCCESS);
    }
    @PostMapping("/select")
    public Result<List<Book>> selectLabByTime(@RequestBody Book book){
        List<Book> list = service.getList(book);
        return Result.Success(CodeState.SUCCESS,list);
    }
    @GetMapping
    public Result<List<Book>> getBookListById(Book book){
        System.out.println(book.toString()+"-------------------Controller");
        List<Book> listById=service.getBookListById(book);
        return Result.Success(CodeState.SUCCESS,listById);
    }
    @PutMapping(value = "/{id}")
    public Result<?> updateBook(@PathVariable Integer id,@RequestBody Book book){
        book.setId(id);
        service.updateBook(book);
        return Result.Success(CodeState.SUCCESS);
    }
    @DeleteMapping(value = "/{id}")
    public Result<?> deleteBook(@PathVariable Integer id){
        service.deleteBook(id);
        return Result.Success(CodeState.SUCCESS);
    }
}
