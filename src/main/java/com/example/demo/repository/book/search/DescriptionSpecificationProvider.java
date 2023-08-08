package com.example.demo.repository.book.search;

import com.example.demo.model.Book;
import com.example.demo.repository.SpecificationProvider;
import com.example.demo.repository.book.BookField;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DescriptionSpecificationProvider implements SpecificationProvider<Book> {

    @Override
    public String getKey() {
        return BookField.DESCRIPTION.getKey();
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                root.get(BookField.DESCRIPTION.getKey()).in(Arrays.stream(params).toArray());
    }
}
