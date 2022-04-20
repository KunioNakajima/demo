package com.example.demo.controller;

import com.example.demo.controller.input.PostForm;
import com.example.demo.controller.input.PostIndexParameter;
import com.example.demo.model.PostSearchModel;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/index")
    public String index(Model model, PostIndexParameter params, Pageable pageable) {
        Page<PostSearchModel> postPage = postService.getIndex(params, pageable);
        model.addAttribute("page", postPage);
        model.addAttribute("params", params);
        return "post/index";
    }

    @GetMapping("/create")
    public String create(Model model, PostForm form) {
        if (model.containsAttribute("errors")) {
            // Modelのキーを設定
            String key = BindingResult.MODEL_KEY_PREFIX + PostForm.ATTRIBUTE_NAME;
            // キーがある場合、コピーする
            model.addAttribute(key, model.getAttribute("errors"));
        }
        return "post/create";
    }

    @PostMapping("/save")
    public String save(@Validated PostForm form, BindingResult bindingResult, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            ra.addFlashAttribute("errors", bindingResult);
            ra.addFlashAttribute("errorFlg", true);
            ra.addFlashAttribute("postForm", form);
            return "redirect:/post/create";
        }

        postService.create(form);
        String message = messageSource.getMessage("default.posted.message", new Object[]{}, Locale.JAPAN);
        ra.addFlashAttribute("message", message);
        return "redirect:/post/index";
    }
}
