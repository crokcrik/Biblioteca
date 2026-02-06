package com.example.Biblioteca.repository;

import com.example.Biblioteca.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUserId(Long userId);

    Optional<Loan> findByBookIdAndActiveTrue(Long bookId);

    // Trova tutti i prestiti attivi di un utente
    List<Loan> findByUserIdAndActiveTrue(Long userId);

    // Trova se un libro è attualmente prestato
    boolean existsByBookIdAndActiveTrue(Long bookId);
}