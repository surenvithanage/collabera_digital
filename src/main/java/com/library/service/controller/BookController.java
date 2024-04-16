package com.library.service.controller;

import com.library.service.entity.Book;
import com.library.service.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/vi/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book register(@RequestBody Book book) {
        return bookService.register(book);
    }

    @GetMapping
    public List<Book> list() {
        return bookService.list();
    }
}