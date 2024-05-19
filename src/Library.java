import java.util.ArrayList;
import java.util.List;

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(String title, String author, int year) throws DuplicateBookException, InvalidYearException {
        if (year < 0 || year > java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)) {
            throw new InvalidYearException("Invalid year: " + year);
        }

        Book newBook = new Book(title, author, year);
        if (books.contains(newBook)) {
            throw new DuplicateBookException("The book already exists in the library: " + title + " by " + author);
        }

        books.add(newBook);
    }

    public void removeBook(String author, String title) throws BookNotFoundException {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getAuthor().equals(author) && book.getTitle().equals(title)) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove == null) {
            throw new BookNotFoundException("The book with title \"" + title + "\" by author \"" + author + "\" was not found.");
        }

        books.remove(bookToRemove);
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) {
                result.add(book);
            }
        }
        return result;
    }
}