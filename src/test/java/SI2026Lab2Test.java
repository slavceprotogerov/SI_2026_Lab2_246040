import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SI2026Lab2Test {

    private Library library = new Library();

    @Test
    void searchBookEveryStatementTest() {
        assertThrows(IllegalArgumentException.class, () -> library.searchBookByTitle(""));

        library.books = new ArrayList<>();
        library.books.add(new Book("Software Engineering", "Author", "Comedy"));
        List<Book> found = library.searchBookByTitle("Software Engineering");
        assertNotNull(found);
        assertEquals(1, found.size());

        assertNull(library.searchBookByTitle("NonExistent"));
    }

    @Test
    void borrowBookEveryBranchTest() {
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("", "Author"));

        library.books = new ArrayList<>();
        Book b=new Book("Title1", "Author1", "Comedy");
        b.setBorrowed(true);
        library.books.add(b);
        assertThrows(RuntimeException.class, () -> library.borrowBook("Title1", "Author1"));

        library.books.add(new Book("Title2", "Author2", "Comedy"));
        library.borrowBook("Title2", "Author2");
        assertTrue(library.books.get(1).isBorrowed());

        assertThrows(RuntimeException.class, () -> library.borrowBook("Unknown", "Unknown"));

        library.books = new ArrayList<>();
        Book testBook = new Book("Software Engineering", "Ian Sommerville", "asdas");
        library.books.add(testBook);

        library.borrowBook("Software Engineering", "Ian Sommerville");

        assertTrue(testBook.isBorrowed());
    }

    @Test
    void searchBookMultipleConditionTest() {
        library.books = new ArrayList<>();
        library.books.add(new Book("Test", "Author", "Comedy"));

        assertNotNull(library.searchBookByTitle("Test"));

        library.books.get(0).setBorrowed(true);
        assertNull(library.searchBookByTitle("Test"));

        assertNull(library.searchBookByTitle("WrongTitle"));
    }

    @Test
    void borrowBookMultipleConditionTest() {
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("", "Author"));
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("Title", ""));

        library.books = new ArrayList<>();
        library.books.add(new Book("Title", "Author", "Comedy"));
        library.borrowBook("Title", "Author");
        assertTrue(library.books.get(0).isBorrowed());

        assertThrows(RuntimeException.class, () -> library.borrowBook("Unknown", "Author"));
        assertThrows(RuntimeException.class, () -> library.borrowBook("Title", "Unknown"));
    }
}