package com.library.service.service.book.impl;

import com.library.service.entity.Book;
import com.library.service.repository.BookRepository;
import com.library.service.service.book.BookService;
import com.library.service.validation.BookValidation;
import com.library.service.validation.RequestBeanValidation;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService, RequestBeanValidation<Object> {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookValidation bookValidation;

    @Override
    @Transactional
    public Book register(Book book) {

        BindingResult bindingResult = validateRequestBean(book);
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream().findFirst().get().getDefaultMessage();
            log.error("Exception : {}", errorMessage);
            return new Book();
        } else {
            return bookRepository.save(book);
        }
    }

    @Override
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @Override
    public BindingResult validateRequestBean(Object o) {
        DataBinder dataBinder = new DataBinder(o);
        dataBinder.setValidator(bookValidation);
        dataBinder.validate();
        return dataBinder.getBindingResult();
    }
}
