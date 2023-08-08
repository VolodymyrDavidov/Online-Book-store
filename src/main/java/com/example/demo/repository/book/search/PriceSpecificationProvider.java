package com.example.demo.repository.book.search;

import com.example.demo.model.Book;
import com.example.demo.repository.SpecificationProvider;
import com.example.demo.repository.book.BookField;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Book> {

    @Override
    public String getKey() {
        return BookField.PRICE.getKey();
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                root.get(BookField.PRICE.getKey()).in(Arrays.stream(params).toArray());
    }
}
