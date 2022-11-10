package sortmap.entity;

public class BookSortTreeMap {
    private String author;
    private String bookName;
    private String genre;
    private double price;

    public BookSortTreeMap() {
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
}
