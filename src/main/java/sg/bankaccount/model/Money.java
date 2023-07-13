package sg.bankaccount.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class Money {
    private final BigDecimal price;
    private final Currency currency;

    public Money(BigDecimal value) {
        this.price = value;
        currency = Currency.getInstance(Locale.FRANCE);
    }

    public Money(double value) {
        this.price = new BigDecimal(value);
        currency = Currency.getInstance(Locale.FRANCE);
    }

    @Override
    public String toString() {
        return currency.getCurrencyCode() + " " + price;
    }

    public Money add(Money money) {
        return new Money(price.add(money.price));
    }

    public int compareTo(Money money) {
        return price.compareTo(money.price);
    }

    public int compareTo(double price) {
        return this.price.compareTo(new BigDecimal(price));
    }

    public Money subtract(Money money) {
        return new Money(price.subtract(money.price));
    }
}
