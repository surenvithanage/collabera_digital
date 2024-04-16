package com.library.service.validation;

import com.library.service.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.lang.reflect.Field;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
@Component
public class BookValidation  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        if (clazz.equals(Book.class)) {
            return Book.class.isAssignableFrom(clazz);
        } else {
            return false;
        }
    }

    @Override
    public void validate(Object o, Errors errors) {
        try {
            SortedMap<String, Field> allFields = new TreeMap<>();
            for (Field field : o.getClass().getDeclaredFields()) {
                allFields.put(field.getName(), field);
            }

            if(o.getClass().equals(Book.class)) {
                Field[] requiredFields = this.getRequiredFields(allFields);
                for(Field field : requiredFields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    switch (fieldName) {
                        case "isbn" -> {
                            ValidationUtils.rejectIfEmptyOrWhitespace(errors, fieldName, "ER01", "Empty ISIN");
                        }
                        case "title" -> {
                            ValidationUtils.rejectIfEmptyOrWhitespace(errors, fieldName, "ER01", "Empty Title");
                        }
                        case "author" -> {
                            ValidationUtils.rejectIfEmptyOrWhitespace(errors, fieldName, "ER01", "Empty Author");
                        }
                    }
                }
            } else {
                errors.reject("ER00");
            }
        } catch (Exception e) {
            log.error("Exception : {}", e.getMessage());
            errors.reject("ER99");
        }
    }

    private Field[] getRequiredFields(SortedMap<String, Field> allFields) {
        return new Field[] {
                allFields.get("isbn"),
                allFields.get("title"),
                allFields.get("author")
        };
    }
}
