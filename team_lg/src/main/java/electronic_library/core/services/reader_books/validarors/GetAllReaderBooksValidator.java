package electronic_library.core.services.reader_books.validarors;

import electronic_library.core.requests.reader_books.GetAllReaderBooksRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllReaderBooksValidator {
    public List<CoreError> validate (GetAllReaderBooksRequest getAllReaderBooksRequest){
        return new ArrayList<>();
    }
}
