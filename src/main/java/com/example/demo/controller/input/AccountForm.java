package com.example.demo.controller.input;

import com.example.demo.annotation.UsernameUniqueValidate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@ToString
public class AccountForm {
    public static final String ATTRIBUTE_NAME = "accountForm";

    @NotNull(message = "{default.blank.message}")
    @Size(max = 20, message = "{default.invalid.max.size.message}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{invalid.alphanumeric.pattern}")
    @UsernameUniqueValidate
    private String username;

    @NotNull(message = "{default.blank.message}")
    @Size(min = 8, max = 50, message = "{invalid.password.size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$", message = "{invalid.password.pattern}")
    private String password;

    @NotNull(message = "{default.blank.message}")
    @Size(min = 8, max = 50, message = "{invalid.password.size}")
    private String passwordConfirmation;

    @AssertTrue(message = "{invalid.confirmation}")
    public boolean isPasswordValid() {
        //必須チェックは別でやっているのでここではtrueにする
        if (password == null || passwordConfirmation == null) {
            return true;
        }
        if (password.equals(passwordConfirmation)) {
            return true;
        }
        return false;
    }
}
