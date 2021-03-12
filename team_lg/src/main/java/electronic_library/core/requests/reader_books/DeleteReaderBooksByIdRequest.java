package electronic_library.core.requests.reader_books;

public class DeleteReaderBooksByIdRequest {

    private Long readerBooksIdToDelete;

    public DeleteReaderBooksByIdRequest(Long readerBooksIdToDelete) {
        this.readerBooksIdToDelete = readerBooksIdToDelete;
    }

    public Long getReaderBooksIdToDelete() {
        return readerBooksIdToDelete;
    }

}
