package dev.crisdejchav.LiterAlura.Main;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import dev.crisdejchav.LiterAlura.model.Author;
import dev.crisdejchav.LiterAlura.model.AuthorData;
import dev.crisdejchav.LiterAlura.model.Book;
import dev.crisdejchav.LiterAlura.model.BookData;
import dev.crisdejchav.LiterAlura.repository.AuthorRepository;
import dev.crisdejchav.LiterAlura.repository.BookRepository;
import dev.crisdejchav.LiterAlura.services.ApiServices;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private boolean running = true;

    private BookRepository Brepository;
    private AuthorRepository Arepository;


    public Main(BookRepository Brepository, AuthorRepository Arepository){
        this.Brepository = Brepository;
        this.Arepository = Arepository;

    }
    public void showMenu(){
        
        
        while (running){
            String menu = """
                1 - Buscar libros por nombre
                2 - Listar todos los libros
                3 - Listar todos los autores
                4 - Buscar autores vivos por año
                5 - Listar todos los idiomas disponibles
                6 - Buscar libros por idioma
                7 - Buscar libros por pais

                """;
        System.out.println(menu);
        System.out.println("Ingrese el numero de la opcion que desea realizar: ");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                findByTitle();
                break;
            case 2:
                listAllBooks();
                break;
            case 3:
                listAllAuthors();
                break;
            case 4:
                searchAuthorsByYear();
                break;
            case 5:
                listAllLanguages();
            default:
                break;
        }
        }
        
        

    }

    private void findByTitle(){
        BookData data = getBookData();
        if (data == null){
            System.out.println("No se encontraron libros con ese nombre y/o autor");
            return;
        }
        Book book = new Book(data, Arepository.findByName(data.authors()[0].name()).get().getId());
        System.out.println("Libro encontrado: " + data);
        
        Brepository.save(book);
        
    }

    private void listAllBooks() {
        List<Book> books = Brepository.listAll();
        System.out.println("Libros encontrados("+ books.size()+" libros): ");
        books.forEach(b -> {
            Arepository.locateId(b.getAuthor()).ifPresent(author -> {
                AuthorData authorData = new AuthorData(author.getName(), author.getDateBirth(), author.getDateDeath());
                BookData bookData = new BookData(
                    b.getId().intValue(),
                    b.getTitle(),
                    new AuthorData[]{authorData},
                    new String[]{b.getCategory()},
                    new String[]{b.getLanguages()},
                    b.getDownloads()
                );
                System.out.println(bookData);
            });
        });
    }

    private void listAllAuthors(){
        Arepository.findAll().forEach(a -> {
            AuthorData authorData = new AuthorData(a.getName(), a.getDateBirth(), a.getDateDeath());
            System.out.println(authorData);
        });
    }

    private void searchAuthorsByYear() {
        System.out.println("Ingrese el año: ");
        Long year = sc.nextLong();
        sc.nextLine();
    
        List<Author> authors = Arepository.findByYearRange(year);
        if (authors.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año");
        } else {
            authors.forEach(a -> {
                AuthorData authorData = new AuthorData(a.getName(), a.getDateBirth(), a.getDateDeath());
                System.out.println(authorData);
            });
        }
    }

    private void listAllLanguages() {
        List<String> languages = Brepository.listAllLanguages();
        System.out.println("Idiomas disponibles: ");
        languages.forEach(System.out::println);
    }
    
    private BookData getBookData() {
        System.out.println("Ingrese el nombre del libro: ");
        String title = sc.nextLine();

        Optional<Book> existingBook = Brepository.findByTitle(title);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            System.out.println("El libro ya se encuentra en la base de datos");
            System.out.println(book);
            return BookData.getFromBook(book, Arepository.locateId(book.getAuthor()).orElse(null));
        }

        BookData[] foundBooks = ApiServices.findBook("search", title);
        if (foundBooks.length == 0) {
            return null;
        }

        BookData bookData = foundBooks[0];
        Arepository.findByName(bookData.authors()[0].name()).ifPresentOrElse(a -> {
            System.out.println("El autor ya se encuentra en la base de datos");
        }, () -> {
            Arepository.save(new Author(bookData.authors()[0]));
        });

        return bookData;
    }


}
