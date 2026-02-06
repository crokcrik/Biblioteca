package com.example.Biblioteca.service;

import com.example.Biblioteca.model.Loan;
import com.example.Biblioteca.model.Book;
import com.example.Biblioteca.model.User;
import com.example.Biblioteca.repository.LoanRepository;
import com.example.Biblioteca.repository.BookRepository;
import com.example.Biblioteca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public Loan createLoan(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Libro non trovato"));

        if (loanRepository.existsByBookIdAndActiveTrue(bookId)) {
            throw new RuntimeException("Il libro è già in prestito a qualcun altro!");
        }

        // AGGIUNGI QUESTE 3 RIGHE QUI:
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Non ci sono copie disponibili per questo libro!");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setActive(true);

        return loanRepository.save(loan);
    }

    @Transactional
    public Loan returnBook(Long bookId) {
        // Cerchiamo il prestito attivo
        Loan loan = loanRepository.findByBookIdAndActiveTrue(bookId)
                .orElseThrow(() -> new RuntimeException("Nessun prestito attivo per questo libro"));

        // AGGIORNO COPIE: Il libro torna, quindi aumento le copie disponibili
        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        // Chiudo il prestito
        loan.setReturnDate(LocalDate.now());
        loan.setActive(false);

        return loanRepository.save(loan);
    }

    public java.util.List<Loan> getMyLoans(Long userId) {
        return loanRepository.findByUserId(userId);
    }
}
