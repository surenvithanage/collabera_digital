package com.library.service.controller;

import com.library.service.dto.BorrowDto;
import com.library.service.entity.Borrower;
import com.library.service.service.borrower.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/vi/borrower")
@RestController
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    @PostMapping
    public Borrower register(@RequestBody Borrower borrower) {
        return borrowerService.register(borrower);
    }

    @PostMapping
    public Borrower borrow(@RequestBody BorrowDto dto) {
        return borrowerService.borrow(dto);
    }

}
