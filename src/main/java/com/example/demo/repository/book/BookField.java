package com.example.demo.repository.book;

public enum BookField {
    TITLE("title"),
    AUTHOR("author"),
    ISBN("isbn"),
    PRICE("price"),
    DESCRIPTION("description"),
    COVER_IMAGE("coverImage");

    private final String key;

    BookField(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
