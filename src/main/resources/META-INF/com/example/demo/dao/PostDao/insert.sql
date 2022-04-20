insert into post(
    post_id,
    account_id,
    title,
    post_content)
values (
    nextval('post_id_seq'),
    /* entity.accountId */1,
    /* entity.title */'test',
    /* entity.postContent */'aaaaaaaaaa'
)