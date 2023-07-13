package sg.bankaccount.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Client")
public class ClientTest {

    @Test
    @DisplayName("should instanced")
    public void testConstructor() {
        Client client = new Client("Foo", "Bar", new Money(1000));
        assertNotNull(client);
    }

    @Test
    @DisplayName("should have minimal firstName length")
    void testConstructorFirstNameException() {
        InvalidParameterException thrown = assertThrows(InvalidParameterException.class, () -> {
            Client c = new Client("", "Bar", new Money(-1));
        });
        assertEquals("Client FirstName and LastName must contain at least one character!", thrown.getMessage());
    }

    @Test
    @DisplayName("should have minimal lastName length")
    void testConstructorLastNameException() {
        InvalidParameterException thrown = assertThrows(InvalidParameterException.class, () -> {
            Client c = new Client("Foo", "", new Money(-1));
        });
        assertEquals("Client FirstName and LastName must contain at least one character!", thrown.getMessage());
    }

    @Test
    @DisplayName("should correctly printed")
    void testToString() {
        Client client = new Client("Foo", "Bar", new Money(1000));
        assertEquals("Foo Bar", client.toString());
    }

    @Test
    @DisplayName("should have an account")
    public void getAccount() {
        Client client = new Client("Foo", "Bar", new Money(1000));
        assertNotNull(client.getAccount());
    }
}