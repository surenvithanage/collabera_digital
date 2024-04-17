package com.library.service.service.borrower;

import com.library.service.dto.BorrowDto;
import com.library.service.entity.Borrower;

public interface BorrowerService {

    Borrower register(Borrower borrower);

    Borrower borrow(BorrowDto dto);
}
