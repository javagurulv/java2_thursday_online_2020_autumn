package internet_store.core.search;

import internet_store.core.domain.Product;
import internet_store.core.persistence.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SearchByProductTitlePagingService {
    private final int RECORDS_COUNT_ON_PAGE = 6;
    private final int PAGE_OFFSET = 1;
    private final int FIRST_PAGE = 1;
    private final int START_FROM_FIRST_RECORD = 0;
    @Autowired
    private ProductRepository productRepository;
    private int startRecordOffset;
    @Getter
    private int pagesQuantity;
    @Getter
    private int currentPage;
    @Getter
    private boolean isFirstPage;
    @Getter
    private boolean isLastPage;
    @Getter
    @Setter
    private List<Product> listOnePage;
    private String searchTitle;

    public void startPaging(String searchTitle) {
        this.searchTitle = searchTitle;
        isFirstPage = true;
        isLastPage = false;
        startRecordOffset = START_FROM_FIRST_RECORD;
        currentPage = FIRST_PAGE;
        calculatePagesQuantity();
        listOnePage = productRepository.searchByTitle(this.searchTitle, RECORDS_COUNT_ON_PAGE, startRecordOffset);
        if ((startRecordOffset + RECORDS_COUNT_ON_PAGE) >= productRepository.searchByProductTitleCount(this.searchTitle)) {
            isLastPage = true;
        }
    }

    public void nextPage(boolean pagingDirection) {
        if (pagingDirection) {
            nextPage();
        } else {
            prevPage();
        }
    }

    private void nextPage() {
        if (currentPage + PAGE_OFFSET >= pagesQuantity) {
            currentPage++;
            startRecordOffset += RECORDS_COUNT_ON_PAGE;
            isLastPage = true;
            isFirstPage = false;
        } else {
            currentPage++;
            startRecordOffset += RECORDS_COUNT_ON_PAGE;
            isLastPage = false;
            isFirstPage = false;
        }
        listOnePage = productRepository.searchByTitle(this.searchTitle, RECORDS_COUNT_ON_PAGE, startRecordOffset);
    }

    private void prevPage() {
        if (currentPage - PAGE_OFFSET <= FIRST_PAGE) {
            currentPage--;
            startRecordOffset = START_FROM_FIRST_RECORD;
            isFirstPage = true;
            isLastPage = false;
        } else {
            currentPage--;
            startRecordOffset -= RECORDS_COUNT_ON_PAGE;
            isFirstPage = false;
            isLastPage = false;
        }
        listOnePage = productRepository.searchByTitle(this.searchTitle, RECORDS_COUNT_ON_PAGE, startRecordOffset);
    }

    private void calculatePagesQuantity() {
        final int NO_EXTRA_PAGE = 0;

        long searchResultCount = productRepository.searchByProductTitleCount(searchTitle);

        if (isAllRecordsCanSetOnePage(searchResultCount)) return;

        if ((searchResultCount % RECORDS_COUNT_ON_PAGE) == NO_EXTRA_PAGE) {
            pagesQuantity = (int) searchResultCount / RECORDS_COUNT_ON_PAGE;
        } else {
            pagesQuantity = ((int) searchResultCount / RECORDS_COUNT_ON_PAGE) + PAGE_OFFSET;
        }
    }

    private boolean isAllRecordsCanSetOnePage(long searchResultCount) {
        final int ONLY_ONE_PAGE = 1;
        if (searchResultCount < RECORDS_COUNT_ON_PAGE) {
            pagesQuantity = ONLY_ONE_PAGE;
            return true;
        }
        return false;
    }
}