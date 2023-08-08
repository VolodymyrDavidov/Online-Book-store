package com.example.demo.repository;

public interface SpecificationProviderManager<T> {
    com.example.demo.repository.SpecificationProvider<T> getSpecificationProvider(String key);
}
