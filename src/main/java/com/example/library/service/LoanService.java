package com.example.library.service;

import com.example.library.model.Loan;
import com.example.library.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public List<Loan> getAllLoans() {
        return repository.findAll();
    }

    public Optional<Loan> getLoanById(Long id) {
        return repository.findById(id);
    }

    public Loan addLoan(Loan loan) {
        return repository.save(loan);
    }

    public void deleteLoan(Long id) {
        repository.deleteById(id);
    }
}
