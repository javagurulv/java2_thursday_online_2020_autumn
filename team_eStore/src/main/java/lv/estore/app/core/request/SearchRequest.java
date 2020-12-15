package lv.estore.app.core.request;

import java.math.BigDecimal;

public class SearchRequest extends CoreRequest{

    private final String name;
    private final Ordering ordering;
    private Integer pageNumber;
    private Integer pageSize;

    public SearchRequest(final String name, final Ordering ordering, final Integer pageNumber, final Integer pageSize) {
        this.name = name;
        this.ordering = ordering;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public Ordering getOrdering(){
        return ordering;
    }

    public Integer getPageNumber(){
        return pageNumber;
    }

    public Integer getPageSize(){
        return pageSize;
    }
}
