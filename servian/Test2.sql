-- MY SQL

select  p.id,
        p.name,
        count(ifnull(c.id, 0))
from person p
left join calls_xpto c on p.id = c.p_id
group by  p.id,
          p.name