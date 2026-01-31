package com.example.bai2_luuquangphu.Controller;

import com.example.bai2_luuquangphu.model.Book;
import com.example.bai2_luuquangphu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private BookService bookService;
    @GetMapping("/index")
    public String Index(Model m){
        m.addAttribute("message", "Danh sách những cuốn sách");
        var books = bookService.getAllBook();
        m.addAttribute("books", books);
        return "index";
    }

    @PostMapping("/index")
    public String addBook (@ModelAttribute("newBook")    Book book){
        bookService.add(book);
        return "index";
    }
}
