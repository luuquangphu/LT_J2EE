package com.example.bai2_luuquangphu.service;

import com.example.bai2_luuquangphu.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>(
            List.of(new Book(1, "Sách 1", "Phú"),
                    new Book(2, "Sách 2", "Phú"),
                    new Book(3, "Sách 3", "Phú"),
                    new Book(4, "Sách 4", "Phú")
            )
    );

    public List<Book> getAllBook(){
        return books;
    }

    public Optional<Book> getById(int id){
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    public void add(Book newBook) {
        // Tìm ID lớn nhất hiện có và cộng thêm 1
        int newId = books.stream()
                .mapToInt(Book::getId)
                .max()
                .orElse(0) + 1;
        newBook.setId(newId);
        books.add(newBook);
    }

    public void updateBook(Book updatedBook) {
        books.stream()
                .filter(book -> book.getId() == updatedBook.getId())
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                });
    }

    public void delete(int id) {
        books.removeIf(b -> b.getId() == id);
    }
}
