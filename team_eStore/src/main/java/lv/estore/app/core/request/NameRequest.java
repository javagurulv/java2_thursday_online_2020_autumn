package lv.estore.app.core.request;

import java.math.BigDecimal;

public class NameRequest extends CoreRequest{
    private final String name;

    public NameRequest(final String name) {
        this.name = name;
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
        return null;
    }
}
