package com.shortthirdman.core.framework.spring.model;

public class Book {
    private Long id;
    private String title;
    private String author;
    private String type;
    private int pages;
	
	@Override
    public String toString() {
        return "Book{" +
                "title='" + title + ''' +
                ", author='" + author + ''' +
                ", pages=" + pages +
                '}';
    }
}