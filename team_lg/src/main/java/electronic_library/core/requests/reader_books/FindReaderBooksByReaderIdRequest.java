package electronic_library.core.requests.reader_books;

public class FindReaderBooksByReaderIdRequest {

    private String ReaderId;

    public FindReaderBooksByReaderIdRequest(String ReaderId) {
        this.ReaderId = ReaderId;
    }

    public String getReaderId() {
        return ReaderId;
    }
}
