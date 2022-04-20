package com.example.demo.controller;

import com.example.demo.controller.input.AccountForm;
import com.example.demo.security.DemoUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@SessionAttributes(names = AccountForm.ATTRIBUTE_NAME)
public class LoginController {

    @Autowired
    DemoUserDetailsService userService;

    @ModelAttribute(AccountForm.ATTRIBUTE_NAME)
    public AccountForm setupAccountForm() {
        return AccountForm.builder().build();
    }

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute(AccountForm.ATTRIBUTE_NAME) AccountForm form) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, @ModelAttribute(AccountForm.ATTRIBUTE_NAME) @Validated AccountForm form, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            // Modelのキーを設定
            String key = BindingResult.MODEL_KEY_PREFIX + AccountForm.ATTRIBUTE_NAME;
            // キーがある場合、コピーする
            model.addAttribute(key, bindingResult);
            return "signup";
        }

        try {
            userService.register(form.getUsername(), form.getPassword());
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("signupError", true);
            return "signup";
        }

        try {
            request.login(form.getUsername(), form.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:/index";
    }
}
