package com.example.Biblioteca.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "Loans")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonBackReference // Indica che questa parte deve essere ignorata nel JSON per evitare il loop
    private Book book;

    private LocalDate loanDate;
    private LocalDate returnDate;

    private boolean active;



}
