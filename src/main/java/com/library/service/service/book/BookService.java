package com.library.service.service.book;

import com.library.service.entity.Book;

import java.util.List;

public interface BookService {

    Book register(Book book);

    List<Book> list();
}
