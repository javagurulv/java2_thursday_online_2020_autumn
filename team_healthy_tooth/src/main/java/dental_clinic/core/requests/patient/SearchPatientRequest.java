package dental_clinic.core.requests.patient;

import dental_clinic.core.domain.OrderingDirection;

public class SearchPatientRequest {

    private String inputForSearch;
    private String orderBy = "name";
    private OrderingDirection orderDirection = OrderingDirection.ASC;
    private Integer pageNumber = 1;
    private Integer pageSize = 100;

    public SearchPatientRequest() { }

    public SearchPatientRequest(String inputForSearch, String orderBy, OrderingDirection orderDirection, Integer pageNumber, Integer pageSize) {
        this.inputForSearch = inputForSearch;
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public String getInputForSearch() {
        return inputForSearch;
    }

    public void setInputForSearch(String inputForSearch) {
        this.inputForSearch = inputForSearch;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public OrderingDirection getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(OrderingDirection orderDirection) {
        this.orderDirection = orderDirection;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
