package store.entity;

import java.util.Comparator;

public class Book implements Comparable<Book>{
    private String author;
    private String bookName;
    private String genre;
    private double price;

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "автор " + author +
                ", название книги " + bookName +
                ", жанр " + genre +
                ", цена " + price +
                " рублей";
    }

    // метод сравнения для сортировки книг по автору
    @Override
    public int compareTo(Book o) {
        return author.compareTo(o.author);
    }
    // класс сравнения для сортировки книг по цене
    public static class BookPriceComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return Double.compare(o1.getPrice(), o2.getPrice());
        }
    }
}
