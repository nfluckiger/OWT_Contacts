package com.fluckiger.Contact.utility;

import com.fluckiger.Contact.model.Person;
import com.fluckiger.Contact.model.Skill;
import org.apache.commons.validator.routines.EmailValidator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class personValidator implements dataValidator{
    @Override
    public boolean validation(Object s) {
        dataValidator sValidator = new skillValidator();
        if(s instanceof Person){
            Person toValidate = (Person)s;
            boolean result = true;

            if(toValidate.getEmail() != null){
                result = EmailValidator.getInstance().isValid(toValidate.getEmail());
            }

            if(toValidate.getPassword() != null){
                String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(toValidate.getPassword());
                result = result && matcher.matches();
            }
            return result;
        }
        return false;
    }
}
