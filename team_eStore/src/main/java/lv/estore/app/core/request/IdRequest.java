package lv.estore.app.core.request;

import java.math.BigDecimal;

public class IdRequest extends CoreRequest{
    private final Long id;

    public IdRequest(final Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return null;
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
        return id;
    }

    @Override
    public Ordering getOrdering(){
        return null;
    }
}
