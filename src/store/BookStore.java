package store;

import store.entity.Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BookStore {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static void main(String[] args) {
        System.out.println(ANSI_BLUE + "Добро пожаловать!\nВ книжнем магазине имеются следующие книги:" + ANSI_RESET);
        List<Book> listBook = readData("src/resources/book_db.txt");
        interfaceUser(listBook,"");
    }

    // интерфейс взаимодействия с пользователем
    public static int interfaceUser(List<Book> listBook, String action) {
        switch (action) {
            case ("по автору"):
                System.out.println("Сортировка по автору");
                Collections.sort(listBook);
                break;
            case ("по цене"):
                System.out.println("Сортировка по цене");
                Collections.sort(listBook, new Book.BookPriceComparator());
                break;
            default:
                if(action.matches("\\d{1} \\d+")) {
                    String[] userAction = action.split(" ");
                    //System.out.println(Arrays.toString(userAction));
                    System.out.println(ANSI_BLUE + "Вы выбрали книгу:");
                    System.out.println(ANSI_PURPLE + listBook.get(Integer.parseInt(userAction[0])-1).getAuthor() + " " +
                            listBook.get(Integer.parseInt(userAction[0])-1).getBookName() + " стоимостью " +
                            listBook.get(Integer.parseInt(userAction[0])-1).getPrice() + " рублей" + ANSI_RESET);
                    if (listBook.get(Integer.parseInt(userAction[0])-1).getPrice() < Integer.parseInt(userAction[1])) {
                        System.out.println(ANSI_BLUE + "Ваша сдача составит " + ((Integer.parseInt(userAction[1])-1) -
                                listBook.get(Integer.parseInt(userAction[0])-1).getPrice()) + " рублей");
                        System.out.println("Поздравляем с покупкой!"+ ANSI_RESET);
                        return 0;
                    } else {
                        System.out.println(ANSI_BLUE + "Для покупки вам не хватает " + (listBook.get(Integer.parseInt(userAction[0])-1).getPrice() -
                                (Integer.parseInt(userAction[1])-1)) + " рублей");
                        System.out.println("Попробуйте ещё раз или выберете что-нибудь другое"+ ANSI_RESET);
                    }
                }
                break;
        }
        viewBook(listBook);
        saveBook(listBook, "src/store/output/book_sort.txt");
        System.out.println(ANSI_BLUE + "Ваши дейтвия:\n" + ANSI_PURPLE +
                "а) отсортировать (введите в консоль команды " + ANSI_CYAN + "по автору" + ANSI_PURPLE + " или " + ANSI_CYAN + "по цене" + ANSI_PURPLE + ")\n" +
                "б) купить книгу (введите в консоль через пробел номер книги и ваш бюджет, пример: " + ANSI_CYAN + "1 1000" + ANSI_PURPLE + ")" + ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        interfaceUser(listBook, message);
        return 0;
    }
    // считывание файла книг
    public static List<Book> readData(String fileName) {
        try {
            // считывание файла
            Scanner scanner = new Scanner(new File(fileName));
            List<Book> listBook = new ArrayList<>();
            while (scanner.hasNext()) {
                Book book = new Book();
                // считывание строки файла в массив
                String[] bookAll = scanner.nextLine().split("   ");
                // инициализация полей книги
                book.setAuthor(bookAll[0]);
                book.setBookName(bookAll[1]);
                book.setGenre(bookAll[2]);
                book.setPrice(Double.parseDouble(bookAll[3]));
                // заполнение map
                listBook.add(book);
            }
            scanner.close();
            return listBook;
        } catch (FileNotFoundException e) {
            System.err.println("\nФайла с данными книг не найдено\n");
            return null;
        }
    }

    // вывод в консоль данных с двумя видами сортировки
    public static void viewBook(List <Book> viewBook) {
        int count = 1;
        // вывод в консоль данных отсортированных по автору книг
        System.out.println("Список произведений:");
        for (Book book: viewBook) {
            System.out.println(count + ") " + book);
            count++;
        }
    }

    // запись в файл данных с двумя видами сортировки
    public static void saveBook(List<Book> viewBook, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName));
            int count = 1;
            String listAuthor;
            // запись данных отсортированных по автору книг
            fileWriter.write("В магазине имеется список произведений:\n");
            for (Book book: viewBook) {
                listAuthor = count + ") " + book + "\n";
                fileWriter.write(listAuthor);
                count++;
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Данные не были записаны в файл");
        }
    }
}
