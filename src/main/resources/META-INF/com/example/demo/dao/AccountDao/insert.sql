insert into account(
    account_id,
    username,
    password)
values (
    nextval('account_id_seq'),
    /* entity.username */'test',
    /* entity.password */'Test1234'
)