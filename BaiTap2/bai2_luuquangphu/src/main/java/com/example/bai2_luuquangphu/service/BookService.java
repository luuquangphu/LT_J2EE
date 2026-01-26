package com.example.bai2_luuquangphu.service;

import com.example.bai2_luuquangphu.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Book getById(int id){
        var item = books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        return item;
    }

    public void add(Book newBook) {
        books.add(newBook);
    }

    public void update(int id, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    public void delete(int id) {
        books.removeIf(b -> b.getId() == id);
    }
}
