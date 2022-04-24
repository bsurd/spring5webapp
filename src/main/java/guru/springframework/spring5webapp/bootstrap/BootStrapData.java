package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        Publisher p0 = new Publisher();

        publisherRepository.save(p0);

        Author a1 = new Author();
        Book b1 = new Book();
        a1.getBooks().add(b1);
        b1.getAuthors().add(a1);
        b1.setPublisher(p0);
        p0.getBooks().add(b1);

        authorRepository.save(a1);
        bookRepository.save(b1);
        publisherRepository.save(p0);

        Author a2 = new Author();
        Book b2 = new Book();
        a2.getBooks().add(b2);
        b2.getAuthors().add(a2);
        b2.setPublisher(p0);
        p0.getBooks().add(b2);

        authorRepository.save(a2);
        bookRepository.save(b2);
        publisherRepository.save(p0);

        System.out.println("-----------------");
        System.out.println("Started in bootstrap");
        System.out.println("Publisher count: " + publisherRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + p0.getBooks().size());

    }

}
