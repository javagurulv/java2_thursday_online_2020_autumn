package internet_store.core.request.cart;

import lombok.Getter;

import java.math.BigDecimal;

public class UpdateCartRequest {
    @Getter
    private final long id;
    @Getter
    private final Long newQuantity;

    public UpdateCartRequest(long id, Long newQuantity) {
        this.id = id;
        this.newQuantity = newQuantity;
    }
}