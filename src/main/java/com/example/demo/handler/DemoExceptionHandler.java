package com.example.demo.handler;

import com.example.demo.exception.DuplicateException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Locale;

@ControllerAdvice
public class DemoExceptionHandler {

    MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView error(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        ModelAndView view = createModelAndView(e, request, "error", HttpStatus.INTERNAL_SERVER_ERROR);
        view.addObject("message", getMessage("default.exception"));
        return view;
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.OK)
    public String duplicateError(DuplicateException e, RedirectAttributes ra) {
        e.printStackTrace();
        String message = messageSourceAccessor.getMessage("default.duplicate.message",
                new Object[]{new DefaultMessageSourceResolvable("username")}, Locale.JAPAN);
        ra.addFlashAttribute("message", message);
        return "redirect:" + e.getRedirect();
    }

    private ModelAndView createModelAndView(Exception e, HttpServletRequest request, String viewName, HttpStatus status) {
        ModelAndView mav = new ModelAndView(viewName, status);
        mav.addObject("path", request.getRequestURI());
        mav.addObject("status", status.value());
        mav.addObject("exception", e.getClass().getSimpleName());

        return mav;
    }

    private String getMessage(String key) {
        try {
            return messageSourceAccessor.getMessage(key, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return messageSourceAccessor.getMessage("default.exception", LocaleContextHolder.getLocale());
        }
    }

    private String getMessage(String key, Object... params) {
        return MessageFormat.format(getMessage(key), params);
    }
}
