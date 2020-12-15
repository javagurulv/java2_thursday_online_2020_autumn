package lv.estore.app.core.request;

import java.math.BigDecimal;

public class UpdateRequest extends CoreRequest{
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;

    public UpdateRequest(final Long id, final String name, final String description, final BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Ordering getOrdering(){
        return null;
    }
}
