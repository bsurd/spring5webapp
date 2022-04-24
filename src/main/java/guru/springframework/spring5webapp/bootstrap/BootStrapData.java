package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher(randomString(), randomString(), randomString(), randomString(), randomString());
        publisherRepository.save(publisher);

        generateData(publisher);
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());

    }



    private void generateData(Publisher publisher) {
        for (int i = 0, j = 0; i < 20; i++) {
            Author author = new Author(randomString(), randomString());
            Book book = new Book(randomString(), randomString());
            author.getBooks().add(book);
            book.getAuthors().add(author);
            book.setPublisher(publisher);
            publisher.getBooks().add(book);
            authorRepository.save(author);
            bookRepository.save(book);
            publisherRepository.save(publisher);
        }
    }

    private String randomString() {
        return new Random()
                .ints(97, 123)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
