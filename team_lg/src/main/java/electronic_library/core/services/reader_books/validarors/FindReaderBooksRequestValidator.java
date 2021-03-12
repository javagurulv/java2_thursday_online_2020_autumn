package electronic_library.core.services.reader_books.validarors;

import electronic_library.core.requests.Ordering;
import electronic_library.core.requests.Paging;
import electronic_library.core.requests.reader_books.FindReaderBooksRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FindReaderBooksRequestValidator {
    public List<CoreError> validate(FindReaderBooksRequest request) {

        List<CoreError> errors = new ArrayList<>(validateFindFields(request));

        if (request.getOrdering() != null) {
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validateObligatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateObligatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }
        if (request.getPaging() != null) {
            validatePageNumber(request.getPaging()).ifPresent(errors::add);
            validatePageSize(request.getPaging()).ifPresent(errors::add);
            validateObligatoryPageNumber(request.getPaging()).ifPresent(errors::add);
            validateObligatoryPageSize(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }

    private List<CoreError> validateFindFields(FindReaderBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(String.valueOf(request.getBookId())) && isEmpty(String.valueOf(request.getReaderId()))) {
            errors.add(new CoreError("reader_id", "Must not be empty!"));
            errors.add(new CoreError("book_id", "Must not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("out") || ordering.getOrderBy().equals("return")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'out' or 'return' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASC") || ordering.getOrderDirection().equals("DESC")))
                ? Optional.of(new CoreError("orderDirection", "Must contain 'ASC' or 'DESC' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateObligatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateObligatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageNumber(Paging paging) {
        return (paging.getPageNumber() != null
                && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("pageNumber", "Must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(Paging paging) {
        return (paging.getPageSize() != null
                && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("pageSize", "Must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateObligatoryPageNumber(Paging paging) {
        return (paging.getPageNumber() == null && paging.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateObligatoryPageSize(Paging paging) {
        return (paging.getPageSize() == null && paging.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "Must not be empty!"))
                : Optional.empty();
    }
}
