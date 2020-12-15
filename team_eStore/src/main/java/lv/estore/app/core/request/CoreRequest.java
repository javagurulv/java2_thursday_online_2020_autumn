package lv.estore.app.core.request;

import java.math.BigDecimal;

public abstract class CoreRequest {
    public abstract String getName();
    public abstract String getDescription();
    public abstract BigDecimal getPrice();
    public abstract Long getId();
    public abstract Ordering getOrdering();
}
