package internet_store.core.operation;

import internet_store.configuration.StoreConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StoreConfiguration.class)
@WebAppConfiguration
public class TaxTest {
    @Autowired
    Tax tax;

    @Test
    public void taxAmount_Tax_Rate_21_1() {
        tax.setTaxRate(new BigDecimal("21"));
        BigDecimal result = tax.getTaxAmount(new BigDecimal("5"));
        assertEquals(new BigDecimal("1.05"), result);
    }

    @Test
    public void taxAmount_Tax_Rate_18_2() {
        tax.setTaxRate(new BigDecimal("18"));
        BigDecimal result = tax.getTaxAmount(new BigDecimal("5"));
        assertEquals(new BigDecimal("0.90"), result);
    }

    @Test
    public void taxAmount_Tax_Rate_21_3() {
        tax.setTaxRate(new BigDecimal("21"));
        BigDecimal result = tax.getTaxAmount(new BigDecimal("75.90"));
        assertEquals(new BigDecimal("15.94"), result);
    }

    @Test
    public void taxAmount_Tax_Rate_25_4() {
        tax.setTaxRate(new BigDecimal("25"));
        BigDecimal result = tax.getTaxAmount(new BigDecimal("75.90"));
        assertEquals(new BigDecimal("18.98"), result);
    }

    @Test
    public void amountWithTax_Tax_Rate_21_1() {
        tax.setTaxRate(new BigDecimal("21"));
        BigDecimal result = tax.getAmountWithTax(new BigDecimal("5.00"));
        assertEquals(new BigDecimal("6.05"), result);
    }

    @Test
    public void amountWithTax_Tax_Rate_15_2() {
        tax.setTaxRate(new BigDecimal("15"));
        BigDecimal result = tax.getAmountWithTax(new BigDecimal("5.00"));
        assertEquals(new BigDecimal("5.75"), result);
    }

    @Test
    public void amountWithTax_Tax_Rate_30_3() {
        tax.setTaxRate(new BigDecimal("30"));
        BigDecimal result = tax.getAmountWithTax(new BigDecimal("79.20"));
        assertEquals(new BigDecimal("102.96"), result);
    }
}