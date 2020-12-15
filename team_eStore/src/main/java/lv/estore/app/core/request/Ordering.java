package lv.estore.app.core.request;

public class Ordering {
    private final String orderBy;
    private final String orderDirection;

    public Ordering(final String orderBy, final String orderDirection){
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }
}
