package com.library.service.service.borrower.impl;

import com.library.service.dto.BorrowDto;
import com.library.service.entity.Book;
import com.library.service.entity.Borrower;
import com.library.service.repository.BookRepository;
import com.library.service.repository.BorrowerRepository;
import com.library.service.service.borrower.BorrowerService;
import com.library.service.validation.BorrowerValidation;
import com.library.service.validation.RequestBeanValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class BorrowerServiceImpl implements BorrowerService, RequestBeanValidation<Object> {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowerValidation borrowerValidation;

    @Override
    public Borrower register(Borrower borrower) {
        BindingResult bindingResult = validateRequestBean(borrower);
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream().findFirst().get().getDefaultMessage();
            log.error("Exception : {}", errorMessage);
            return new Borrower();
        } else {
            return borrowerRepository.save(borrower);
        }
    }

    @Override
    public Borrower borrow(BorrowDto dto) {
        Optional<Book> bookOptional = bookRepository.findById(dto.getBookId());
        Optional<Borrower> borrowOptional = borrowerRepository.findById(dto.getUserId());

        if (bookOptional.isPresent() && borrowOptional.isPresent()) {
            Borrower borrower = borrowOptional.get();
            Book book = bookOptional.get();

            Set<Book> bookSet = new HashSet<>();
            bookSet.add(book);

            borrower.setBooks(bookSet);

           return  borrowerRepository.save(borrower);
        }
        return null;
    }

    @Override
    public BindingResult validateRequestBean(Object o) {
        DataBinder dataBinder = new DataBinder(o);
        dataBinder.setValidator(borrowerValidation);
        dataBinder.validate();
        return dataBinder.getBindingResult();
    }
}
