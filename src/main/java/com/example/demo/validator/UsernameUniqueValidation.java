package com.example.demo.validator;

import com.example.demo.annotation.UsernameUniqueValidate;
import com.example.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class UsernameUniqueValidation implements ConstraintValidator<UsernameUniqueValidate, String> {

    private final MessageSource messageSource;
    private final AccountRepository accountRepository;
    private String message;

    @Override
    public void initialize(UsernameUniqueValidate annotation) {
        ConstraintValidator.super.initialize(annotation);
        this.message = annotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (!StringUtils.hasText(value)) {
            return true;
        }

        if (accountRepository.selectByUsername(value).isEmpty()) {
            return true;
        }
        HibernateConstraintValidatorContext hibernateContext =
                context.unwrap(HibernateConstraintValidatorContext.class);

        hibernateContext.disableDefaultConstraintViolation();

        hibernateContext
                .addMessageParameter("target", messageSource.getMessage("username", new String[]{}, Locale.JAPAN))
                .addMessageParameter("value", value)
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}
