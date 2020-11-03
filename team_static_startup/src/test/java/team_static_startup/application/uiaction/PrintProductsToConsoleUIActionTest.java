package team_static_startup.application.uiaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import team_static_startup.application.Product;
import team_static_startup.application.ProductDatabase;
import team_static_startup.application.ProductDatabaseImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PrintProductsToConsoleUIActionTest {

    private final PrintStream standartOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    ProductDatabase productDatabase = new ProductDatabaseImpl();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standartOut);
    }

    @Test
    public void shouldPrintOutProducts() {
        productDatabase.save(new Product("iPhone", "mobile phone", new BigDecimal("950.00")));
        productDatabase.save(new Product("Lenovo ThinkPad", "notebook", new BigDecimal("3000.00")));
        PrintProductsToConsoleUIAction victim = new PrintProductsToConsoleUIAction(productDatabase);
        victim.execute();
        assertEquals("Product{id = 1, productName = 'iPhone', productDescription = 'mobile phone', price = 950.00}\n" +
                "Product{id = 2, productName = 'Lenovo ThinkPad', productDescription = 'notebook', price = 3000.00}", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldNotPrintOutProducts_whenDatabaseIsEmpty() {
        PrintProductsToConsoleUIAction victim = new PrintProductsToConsoleUIAction(productDatabase);
        victim.execute();
        assertEquals("", outputStreamCaptor.toString().trim());
    }
}