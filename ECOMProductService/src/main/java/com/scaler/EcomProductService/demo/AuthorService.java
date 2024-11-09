package com.scaler.EcomProductService.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public Author getAuthorWithLazyBooks(Integer authorId) {
        return authorRepository.findByIdCustomQuery(authorId);
    }
}
