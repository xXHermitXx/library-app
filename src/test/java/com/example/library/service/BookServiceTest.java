package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testAddBook() {
        Book book = new Book("Test Title", "Test Author", "1234", LocalDate.of(2024, 5, 20), true);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.addBook(book);

        assertEquals("Test Title", result.getTitle());
        assertTrue(result.isAvailable());
        verify(bookRepository).save(book);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(
                new Book("A", "Author A", "111", LocalDate.of(2022, 1, 1), true),
                new Book("B", "Author B", "222", LocalDate.of(2023, 2, 2), true)
        );
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository).findAll();
    }
}
