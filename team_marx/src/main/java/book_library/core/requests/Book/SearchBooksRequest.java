package book_library.core.requests.Book;

import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;

public class SearchBooksRequest {

    private String title;
    private String author;

    private Ordering ordering;
    private Paging paging;

    public SearchBooksRequest() {
    }

    public SearchBooksRequest(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public SearchBooksRequest(String title, String author, Ordering ordering) {
        this.title = title;
        this.author = author;
        this.ordering = ordering;
    }

    public SearchBooksRequest(String title, String author, Paging paging) {
        this.title = title;
        this.author = author;
        this.paging = paging;
    }

    public SearchBooksRequest(String title, String author, Ordering ordering, Paging paging) {
        this.title = title;
        this.author = author;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public boolean isTitleProvided() {
        return this.title != null && !this.title.isEmpty();
    }

    public boolean isAuthorProvided() {
        return this.author != null && !this.author.isEmpty();
    }
}
