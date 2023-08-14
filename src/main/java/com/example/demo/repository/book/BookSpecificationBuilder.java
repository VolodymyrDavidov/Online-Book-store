package com.example.demo.repository.book;

import com.example.demo.dto.book.BookSearchParametersDto;
import com.example.demo.model.Book;
import com.example.demo.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> spec = Specification.where(null);
        if (searchParameters.title() != null && searchParameters.title().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(BookField.TITLE.getKey())
                    .getSpecification(searchParameters.title()));
        }
        if (searchParameters.author() != null && searchParameters.author().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(BookField.AUTHOR.getKey())
                    .getSpecification(searchParameters.author()));
        }
        if (searchParameters.isbn() != null && searchParameters.isbn().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(BookField.ISBN.getKey())
                    .getSpecification(searchParameters.isbn()));
        }
        if (searchParameters.price() != null && searchParameters.price().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(BookField.PRICE.getKey())
                    .getSpecification(searchParameters.price()));
        }
        if (searchParameters.description() != null && searchParameters.description().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(BookField.DESCRIPTION.getKey())
                    .getSpecification(searchParameters.description()));
        }
        if (searchParameters.coverImage() != null && searchParameters.coverImage().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(BookField.COVER_IMAGE.getKey())
                    .getSpecification(searchParameters.coverImage()));
        }
        return spec;
    }
}
