package internet_store.core.operation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderSumProperty {
    @Autowired
    private Arithmetic arithmetic;
    @Getter
    @Setter
    @Value("${tax}")
    private BigDecimal taxRate;
    @Getter
    @Setter
    @Value("${currency-symbol}")
    private String currencySymbol;
    @Getter
    @Setter
    private BigDecimal taxAmount;
    @Getter
    @Setter
    private BigDecimal amountWithTax;

    public BigDecimal getTaxAmount(BigDecimal sumWithoutTax) {
        BigDecimal index = arithmetic.divideBigDecimalAndRound(taxRate, new BigDecimal("100"), 2);
        taxAmount = arithmetic.multiplyBigDecimalAndRound(index, sumWithoutTax, 2);
        return taxAmount;
    }

    public BigDecimal getAmountWithTax(BigDecimal sumWithoutTax) {
        BigDecimal index = new BigDecimal("1." + taxRate.toString());
        amountWithTax = arithmetic.multiplyBigDecimalAndRound(sumWithoutTax, index, 2);
        return amountWithTax;
    }
}