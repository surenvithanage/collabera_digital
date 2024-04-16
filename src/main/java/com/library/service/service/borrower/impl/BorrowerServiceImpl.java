package com.library.service.service.borrower.impl;

import com.library.service.entity.Book;
import com.library.service.entity.Borrower;
import com.library.service.repository.BorrowerRepository;
import com.library.service.service.borrower.BorrowerService;
import com.library.service.validation.BorrowerValidation;
import com.library.service.validation.RequestBeanValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

@Slf4j
@Service
public class BorrowerServiceImpl implements BorrowerService, RequestBeanValidation<Object> {

    @Autowired
    private BorrowerRepository borrowerRepository;

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
    public BindingResult validateRequestBean(Object o) {
        DataBinder dataBinder = new DataBinder(o);
        dataBinder.setValidator(borrowerValidation);
        dataBinder.validate();
        return dataBinder.getBindingResult();
    }
}
