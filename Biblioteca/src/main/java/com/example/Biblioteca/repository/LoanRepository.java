package com.example.Biblioteca.repository;

import com.example.Biblioteca.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // <-- AGGIUNGI QUESTO IMPORT

import java.util.List;
import java.util.Optional;

@Repository  // <-- AGGIUNGI QUESTA RIGA
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);
    Optional<Loan> findByBookIdAndActiveTrue(Long bookId);
    List<Loan> findByUserIdAndActiveTrue(Long userId);
    boolean existsByBookIdAndActiveTrue(Long bookId);
}