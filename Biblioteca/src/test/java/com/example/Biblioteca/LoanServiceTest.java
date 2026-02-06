package com.example.Biblioteca.service;

import com.example.Biblioteca.model.Book;
import com.example.Biblioteca.model.User;
import com.example.Biblioteca.repository.BookRepository;
import com.example.Biblioteca.repository.LoanRepository;
import com.example.Biblioteca.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @Mock
    private BookRepository bookRepository; // Mock = Database finto

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoanService loanService; // Il servizio vero che userà i mock sopra

    @Test
    void testCreateLoan_ShouldFail_WhenNoCopiesAvailable() {
        // 1. Mock Utente
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // 2. Mock Libro con ZERO copie
        Book book = new Book();
        book.setId(1L);
        book.setAvailableCopies(0);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // 3. Diciamo che il libro NON è già in prestito (altrimenti il test si fermerebbe lì)
        when(loanRepository.existsByBookIdAndActiveTrue(1L)).thenReturn(false);

        // 4. Esecuzione: ora il Service troverà 0 copie e lancerà l'eccezione
        assertThrows(RuntimeException.class, () -> {
            loanService.createLoan(1L, 1L);
        });
    }
}
