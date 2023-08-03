package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {
    private final BookService bookService;

    @Autowired
    public DemoApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book book = new Book();
                book.setTitle("1984. Animal Farm");
                book.setAuthor("George Orwell");
                book.setIsbn("978-0-241-43089-7");
                book.setPrice(BigDecimal.valueOf(14.99));
                book.setDescription("\"Animal Farm\" is a classic allegorical novella written " +
                        "by George Orwell. It tells the story of a group of farm animals who " +
                        "rebel against their human farmer, hoping to create a society where " +
                        "the animals can be equal, free, and independent. ");
                book.setCoverlmage("Hardcover");

                bookService.save(book);

                System.out.println(bookService.findAll());

            }
        };
    }

}
