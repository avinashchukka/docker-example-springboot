package com.ac.example.docker.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@code Book} represents a book entity.
 * <p/>
 *
 * @author Avinash
 * @since 3/8/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private UUID id;
    private String title;
    private Genre genre;
    private String publisher;
    private int star;
    private Author author;
}
