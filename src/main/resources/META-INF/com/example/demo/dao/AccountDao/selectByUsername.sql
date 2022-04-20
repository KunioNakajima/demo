select
    a.account_id,
    a.username,
    a.password
from
    account a
where
    a.username = /* username */'user'
