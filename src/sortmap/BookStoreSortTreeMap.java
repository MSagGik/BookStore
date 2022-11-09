package sortmap;

import sortmap.entity.Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BookStoreSortTreeMap {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать!\nВ книжнем магазине имеются следующие книги:");
        List<Map<String, Book>> listBook = readData("src/resources/book_db.txt");
        viewBook(listBook.get(0), listBook.get(1));
        saveBook(listBook.get(0), listBook.get(1),"src/sortmap/output/book_sort.txt");
    }
    // считывание файла книг
    public static List<Map<String, Book>> readData(String fileName) {
        try {
            // считывание файла
            Scanner scanner = new Scanner(new File(fileName));
            Map<String, Book> bookMapAuthor = new TreeMap<>();
            Map<String, Book> bookMapNameBook = new TreeMap<>();
            while (scanner.hasNext()) {
                Book book = new Book();
                // считывание строки файла
                String[] bookAll = scanner.nextLine().split("   ");
                // инициализация полей книги
                book.setAuthor(bookAll[0]);
                book.setBookName(bookAll[1]);
                book.setGenre(bookAll[2]);
                book.setPrice(Double.parseDouble(bookAll[3]));
                // заполнение map
                bookMapAuthor.put(book.getAuthor(), book);
                bookMapNameBook.put(book.getBookName(), book);
            }
            scanner.close();
            // создание коллекции коллекций
            List<Map<String, Book>> listAll = new ArrayList<>();
            listAll.add(0, bookMapAuthor);
            listAll.add(1, bookMapNameBook);
            return listAll;
        } catch (FileNotFoundException e) {
            System.err.println("\nФайла с данными книг не найдено\n");
            return null;
        }
    }
    // вывод в консоль данных с двумя видами сортировки
    public static void viewBook(Map<String, Book> viewBookMapAuthor, Map<String, Book> viewBookMapName){
        int countAuthor = 1, countName = 1;
        // вывод в консоль данных отсортированных по автору книг
        System.out.println("Сортировка по авторам:");
        for (Map.Entry entryAuthor : viewBookMapAuthor.entrySet()) {
            System.out.println(countAuthor + ") " + entryAuthor.getKey() + "\n"+ entryAuthor.getValue());
            countAuthor++;
        }
        // вывод в консоль данных отсортированных по названию книг
        System.out.println("Сортировка по названию:");
        for (Map.Entry entryName : viewBookMapName.entrySet()) {
            System.out.println(countName + ") " + entryName.getKey() + "\n"+ entryName.getValue());
            countName++;
        }
    }
    // запись в файл данных с двумя видами сортировки
    public static void saveBook(Map<String, Book> viewBookMapAuthor, Map<String, Book> viewBookMapName, String fileName){
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName), false);
            int countAuthor = 1, countName = 1;
            String listAuthor, listName;
            // запись данных отсортированных по автору книг
            fileWriter.write("Сортировка по авторам:\n");
            for (Map.Entry entryAuthor : viewBookMapAuthor.entrySet()) {
                listAuthor = countAuthor + ") " + entryAuthor.getKey() + "\n"+ entryAuthor.getValue() + "\n";
                fileWriter.write(listAuthor);
                countAuthor++;
            }
            // запись данных отсортированных по названию книг
            fileWriter.write("\nСортировка по названию:\n");
            for (Map.Entry entryName : viewBookMapName.entrySet()) {
                listName = countName + ") " + entryName.getKey() + "\n"+ entryName.getValue() + "\n";
                fileWriter.write(listName);
                countName++;
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Данные не были записаны в файл");
        }
    }
}
