package com.example.Biblioteca.controller;

import com.example.Biblioteca.model.Loan;
import com.example.Biblioteca.model.User;
import com.example.Biblioteca.repository.UserRepository;
import com.example.Biblioteca.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List; // <--- FONDAMENTALE: aggiungi questo import

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final UserRepository userRepository;

    @PostMapping("/book/{bookId}")
    public ResponseEntity<Loan> createLoan(
            @PathVariable Long bookId,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        Loan newLoan = loanService.createLoan(user.getId(), bookId);
        return ResponseEntity.ok(newLoan);
    }

    @PutMapping("/return/book/{bookId}")
    public ResponseEntity<Loan> returnBook(@PathVariable Long bookId) {
        // Ora è pulito: se il service lancia eccezione, ci pensa il GlobalExceptionHandler
        Loan updatedLoan = loanService.returnBook(bookId);
        return ResponseEntity.ok(updatedLoan);
    }

    @GetMapping("/my-loans")
    public ResponseEntity<List<Loan>> getMyLoans(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        return ResponseEntity.ok(loanService.getMyLoans(user.getId()));
    }
}


