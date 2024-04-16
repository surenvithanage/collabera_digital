package com.library.service.controller;

import com.library.service.entity.Book;
import com.library.service.service.book.BookService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void registerTest() {
        Mockito.when(bookService.register(new Book())).thenReturn(new Book());
        Book result = bookController.register(new Book());
        Assertions.assertNotNull(result);
    }

    @Test
    public void listTest() {
        Mockito.when(bookService.list()).thenReturn((List<Book>) new Book());
        List<Book> result = bookController.list();
        Assertions.assertNotNull(result);
    }

}
