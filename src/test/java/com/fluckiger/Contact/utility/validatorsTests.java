package com.fluckiger.Contact.utility;

import com.fluckiger.Contact.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class validatorsTests {

    @Test
    public void personValidatorTrueIfPersonValid(){
        dataValidator validator = new personValidator();

        Person p = new Person((long)1, "Nathan", "Fluckiger", "Nathan Fluckiger", "Les replans 713, 1450 Ste-Croix", "nathan.fluckiger@hotmail.ch", "P@ssw0rd", null);


        assertEquals(true, validator.validation(p));

    }

    @Test
    public void personValidatorFalseIfPersonEmailInvalid(){
        dataValidator validator = new personValidator();

        Person p = new Person((long)1, "Nathan", "Fluckiger", "Nathan Fluckiger", "Les replans 713, 1450 Ste-Croix", "nathan.fluckiger", "P@ssw0rd", null);


        assertEquals(false, validator.validation(p));

    }

    @Test
    public void personValidatorFalseIfPersonPasswordInvalid(){
        dataValidator validator = new personValidator();

        Person p = new Person((long)1, "Nathan", "Fluckiger", "Nathan Fluckiger", "Les replans 713, 1450 Ste-Croix", "nathan.fluckiger@hotmail.ch", "password", null);


        assertEquals(false, validator.validation(p));

    }

}
