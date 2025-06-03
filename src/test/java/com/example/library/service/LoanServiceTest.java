package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.Member;
import com.example.library.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    private LoanRepository loanRepository;
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        loanRepository = mock(LoanRepository.class);
        loanService = new LoanService(loanRepository);
    }

    @Test
    void testAddLoan() {
        Book book = new Book("Book", "Author", "123", LocalDate.of(2023, 6, 1), true);
        Member member = new Member("Jane Doe", "jane@example.com");
        Loan loan = new Loan(book, member, LocalDate.of(2025, 6, 3), LocalDate.of(2025, 6, 10));

        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan result = loanService.addLoan(loan);

        assertEquals("Book", result.getBook().getTitle());
        assertEquals("Jane Doe", result.getMember().getName());
        verify(loanRepository).save(loan);
    }

    @Test
    void testGetAllLoans() {
        Book book = new Book("Book", "Author", "123", LocalDate.of(2023, 1, 1), true);
        Member member = new Member("John Doe", "john@example.com");

        List<Loan> loans = List.of(
                new Loan(book, member, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 7)),
                new Loan(book, member, LocalDate.of(2025, 6, 2), LocalDate.of(2025, 6, 8))
        );

        when(loanRepository.findAll()).thenReturn(loans);

        List<Loan> result = loanService.getAllLoans();

        assertEquals(2, result.size());
        verify(loanRepository).findAll();
    }
}
