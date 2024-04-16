package com.library.service.validation;

import org.springframework.validation.BindingResult;

@FunctionalInterface
public interface RequestBeanValidation<T> {
    BindingResult validateRequestBean(T t);
}
