package com.example.library.controller;

import com.example.library.model.Loan;
import com.example.library.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping
    public List<Loan> getAll() {
        return service.getAllLoans();
    }

    @GetMapping("/{id}")
    public Loan getById(@PathVariable Long id) {
        return service.getLoanById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    @PostMapping
    public Loan add(@RequestBody Loan loan) {
        return service.addLoan(loan);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteLoan(id);
    }
}
