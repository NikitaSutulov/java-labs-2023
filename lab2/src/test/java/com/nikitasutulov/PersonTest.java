package com.nikitasutulov;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class PersonTest {

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Person.class).verify();
    }
}
