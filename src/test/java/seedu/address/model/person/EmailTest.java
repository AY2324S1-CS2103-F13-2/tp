package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@example.com")); // missing local part
        assertFalse(Email.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(Email.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Email.isValidEmail("e1234567@u.nus.edu")); // invalid domain name (no .com)
        assertFalse(Email.isValidEmail("peterjack@example.com")); // underscore in domain name (not gmail.com)
        assertFalse(Email.isValidEmail("peterjack@gm_ail.com")); // underscore in domain name
        assertFalse(Email.isValidEmail("peter jack@gmail.com")); // spaces in local part
        assertFalse(Email.isValidEmail("peterjack@gma il.com")); // spaces in domain name
        assertFalse(Email.isValidEmail(" peterjack@gmail.com")); // leading space
        assertFalse(Email.isValidEmail("peterjack@gmail.com ")); // trailing space
        assertFalse(Email.isValidEmail("peterjack@@gmail.com")); // double '@' symbol
        assertFalse(Email.isValidEmail("peter@jack@gmail.com")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("-peterjack@gmail.com")); // local part starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack-@gmail.com")); // local part ends with a hyphen
        assertFalse(Email.isValidEmail("peter..jack@gmail.com")); // local part has two consecutive periods
        assertFalse(Email.isValidEmail("peterjack@gmail@com")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("peterjack@.gmail.com")); // domain name starts with a period
        assertFalse(Email.isValidEmail("peterjack@gmail.com.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("peterjack@-gmail.com")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack@gmail.com-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("peterjack@gmail.c")); // top level domain has less than two chars

        // valid email
        assertTrue(Email.isValidEmail("PeterJack_1190@gmail.com")); // underscore in local part
        assertTrue(Email.isValidEmail("PeterJack.1190@gmail.com")); // period in local part
        assertTrue(Email.isValidEmail("PeterJack+1190@gmail.com")); // '+' symbol in local part
        assertTrue(Email.isValidEmail("PeterJack-1190@gmail.com")); // hyphen in local part
        assertTrue(Email.isValidEmail("a1+be.d@gmail.com")); // mixture of alphanumeric and special characters
        assertTrue(Email.isValidEmail("peter_jack@gmail.com")); // long domain name
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@gmail.com")); // long local part
    }

    @Test
    public void equals() {
        Email email = new Email("dave@gmail.com");

        // same values -> returns true
        assertTrue(email.equals(new Email("dave@gmail.com")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new Email("other.valid@gmail.com")));
    }

    @Test
    public void compareTo() {
        assertEquals(new Email("asdf@gmail.com").compareTo(new Email("bcda@gmail.com")), -1);
        assertEquals(new Email("asdf@gmail.com").compareTo(new Email("asdf@gmail.com")), 0);
        assertEquals(new Email("c@gmail.com").compareTo(new Email("bcda@gmail.com")), 1);
    }
}
