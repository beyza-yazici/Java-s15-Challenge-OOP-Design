package models;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person{
    private String biography;
    private List<Book> books;

    public Author(int id, String name, String biography) {
        super(id, name);
        this.biography = biography;
        this.books = new ArrayList<>();
    }

    public String getBiography() {
        return biography;
    }

    public void newBook(Book book){
        books.add(book);
    }

    public void showBook(){
        if(books.isEmpty()){
            System.out.println("No books available.");
        } else {
            for(Book book : books){
                book.display();
            }
        }
    }

    public String whoYouAre(){
        return "Author: " + name + ", Biography: " + biography + "Books: " + books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", books=" + books.size() +
                '}';
    }
}
