package electronic_library.core.requests.reader_books;

public class ReturnReaderBooksByIdRequest {

    private Long readerBooksIdToReturn;

    public ReturnReaderBooksByIdRequest(Long readerBooksIdToReturn) {
        this.readerBooksIdToReturn = readerBooksIdToReturn;
    }

    public Long getReaderBooksIdToReturn() {
        return readerBooksIdToReturn;
    }
}
