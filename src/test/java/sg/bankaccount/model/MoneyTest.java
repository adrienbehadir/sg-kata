package sg.bankaccount.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Money")
class MoneyTest {
    Money money;

    @BeforeEach
    void initMoney() {
        money = new Money(new BigDecimal(1000));
    }

    @Test
    @DisplayName("can be instanced with double")
    void instanceFromDouble() {
        assertEquals("EUR 1000", new Money(1000).toString());
    }

    @Test
    @DisplayName("can be instanced with BigDecimal")
    void instanceFromBigDecimal() {
        assertEquals("EUR 1000", new Money(new BigDecimal(1000)).toString());
    }

    @Test
    @DisplayName("should correctly printed")
    void testToString() {
        assertEquals("EUR 1000", money.toString());
    }

    @Test
    @DisplayName("can be added")
    void add() {
        assertEquals("EUR 1500", money.add(new Money(500)).toString());
    }

    @Test
    @DisplayName("can be subtracted")
    void subtract() {
        assertEquals("EUR 500", money.subtract(new Money(500)).toString());
    }

    @Test
    @DisplayName("value is equal to double:1000")
    void compareEqualDouble() {
        assertEquals(0, money.compareTo(1000));
    }

    @Test
    @DisplayName("value is greater double:500")
    void compareGreaterDouble() {
        assertEquals(1, money.compareTo(500));
    }

    @Test
    @DisplayName("value is less than double:1500")
    void compareLessDouble() {
        assertEquals(-1, money.compareTo(1500));
    }

    @Test
    @DisplayName("value is equal to Money:1000")
    void compareEqual() {
        assertEquals(0, money.compareTo(new Money(1000)));
    }

    @Test
    @DisplayName("value is greater than Money:500")
    void compareGreater() {
        assertEquals(1, money.compareTo(new Money(500)));
    }

    @Test
    @DisplayName("value is less than Money:1500")
    void compareLess() {
        assertEquals(-1, money.compareTo(new Money(1500)));
    }
}