package com.ac.example.docker.model;

import lombok.Data;

/**
 * {@code BookRequest} represents a request to create or update a book entity.
 * <p/>
 *
 * @author Avinash
 * @since 3/13/17
 */
@Data
public class BookRequest {

    private String title;
    private Genre genre;
    private String publisher;
    private int star;
    private Author author;
}
