package com.example.demo.service;

import com.example.demo.controller.input.PostForm;
import com.example.demo.controller.input.PostIndexParameter;
import com.example.demo.entity.Account;
import com.example.demo.entity.Post;
import com.example.demo.model.PostSearchModel;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Page<PostSearchModel> getIndex(PostIndexParameter params, Pageable pageable) {
        SelectOptions options = SelectOptions
                .get()
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .count();

        List<PostSearchModel> postList = postRepository.selectByCriteria(params.toCriteria(), options);

        return new PageImpl<>(postList, pageable, options.getCount());
    }

    @Transactional
    public void create(PostForm form) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        List<Account> accounts = accountRepository.selectByUsername(username);
        Account account = accounts.get(0);
        Post entity = createEntity(account.getAccountId(), form);
        postRepository.insert(entity);
    }

    private Post createEntity(Long accountId, PostForm form) {
        Post entity = new Post();
        entity.setAccountId(accountId);
        entity.setPostContent(form.getPostContent());
        entity.setTitle(form.getTitle());
        return entity;
    }
}
