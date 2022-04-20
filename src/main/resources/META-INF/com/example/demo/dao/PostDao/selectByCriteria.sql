select
    p.account_id,
    p.title,
    p.post_content,
    p.ins_ts,
    a.username
from
    post p
    inner join account a
        on a.account_id = p.account_id
where
/*%if criteria.searchUsername != null */
    a.username LIKE /* @infix(criteria.searchUsername) */'user'
/*%end*/
/*%if criteria.searchTitle != null */
    and p.title LIKE /* @infix(criteria.searchTitle) */'テスト'
/*%end*/
/*%if criteria.searchKeyword != null */
    and p.post_content LIKE /* @infix(criteria.searchKeyword) */'テスト'
/*%end*/
order by p.ins_ts
