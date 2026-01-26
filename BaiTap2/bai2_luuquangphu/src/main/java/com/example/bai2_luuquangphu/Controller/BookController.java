package com.example.bai2_luuquangphu.Controller;

import com.example.bai2_luuquangphu.model.Book;
import com.example.bai2_luuquangphu.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/books")
@RestController
public class BookController {
    private BookService bookService;

    public BookController (BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> getAll(){
        return bookService.getAllBook();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable int id){
        return bookService.getById(id);
    }

    @PostMapping()
    public String create(@RequestBody Book b){
        bookService.add(b);
        return "Them thanh cong";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Book book) {
        bookService.update(id, book);
        return "Cập nhật thành công!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        bookService.delete(id);
        return "Xóa thành công!";
    }
}
