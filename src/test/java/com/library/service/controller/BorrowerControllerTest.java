package com.library.service.controller;

import com.library.service.entity.Borrower;
import com.library.service.service.borrower.BorrowerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class BorrowerControllerTest {

    @Mock
    private BorrowerService borrowerService;

    @InjectMocks
    private BorrowerController borrowerController;

    @Test
    public void registerTest() {
        Mockito.when(borrowerService.register(new Borrower())).thenReturn(new Borrower());
        Borrower result = borrowerController.register(new Borrower());
        Assertions.assertNotNull(result);
    }

}
