package lv.estore.app.core.request;

import java.math.BigDecimal;

public class AddRequest extends CoreRequest {
    private final String name;
    private final String description;
    private final BigDecimal price;

    public AddRequest(final String name, final String description, final BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
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
