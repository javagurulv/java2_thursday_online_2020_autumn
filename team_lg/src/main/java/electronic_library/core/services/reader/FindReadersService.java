package electronic_library.core.services.reader;

import electronic_library.core.database.reader.ReaderRepository;
import electronic_library.core.domain.Reader;
import electronic_library.core.requests.Ordering;
import electronic_library.core.requests.Paging;
import electronic_library.core.requests.reader.FindReadersRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader.FindReadersResponse;
import electronic_library.core.services.reader.validators.FindReadersRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class FindReadersService {
    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private FindReadersRequestValidator validator;

    public FindReadersResponse execute(FindReadersRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindReadersResponse(null,errors);
        }

        List<Reader> readers = find(request);
        readers = order(readers, request.getOrdering());
        readers = paging(readers, request.getPaging());
        return new FindReadersResponse(readers, null);
    }

    private List<Reader> order(List<Reader> readers, Ordering ordering) {
        if (ordering != null) {
            Comparator<Reader> comparator = ordering.getOrderBy().equals("firstName")
                    ? Comparator.comparing(Reader::getReaderFirstName)
                    : Comparator.comparing(Reader::getReaderLastName);
            if (ordering.getOrderDirection().equals("DESC")) {
                comparator = comparator.reversed();
            }
            return readers.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return readers;
        }
    }

    private List<Reader> find(FindReadersRequest request) {
        List<Reader> readers = new ArrayList<>();
        if (request.isReaderFirstNameProvided() && !request.isReaderLastNameProvided()) {
            readers = readerRepository.findReaderByFirstName(request.getReaderFirstName());
        }
        if (!request.isReaderFirstNameProvided() && request.isReaderLastNameProvided()) {
            readers = readerRepository.findReaderByLastName(request.getReaderLastName());
        }
        if (request.isReaderFirstNameProvided() && request.isReaderLastNameProvided()) {
            readers = readerRepository.findByFirstNameAndLastName(request.getReaderFirstName(), request.getReaderLastName());
        }
        if (!request.isReaderFirstNameProvided() && !request.isReaderLastNameProvided() && request.isReaderPersonalCodeProvided()) {
            readers = readerRepository.findReaderByPersonalCode(request.getReaderPersonalCode());
        }
        return readers;
    }

    private List<Reader> paging(List<Reader> readers, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return readers.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return readers;
        }
    }
}
