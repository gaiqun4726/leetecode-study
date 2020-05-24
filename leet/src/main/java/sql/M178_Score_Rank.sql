-- sql嵌套了子查询，会先执行子查询
-- 子查询根据所用的位置，可以返回任意有效的查询结果，可以是表、列、值
-- 子查询可以用到父查询中的元素，但是反之不行，除非显示返回

-- 分数排名，相同的分数排名相同，下一个排名不要跳过序号
-- 子查询的意思是，越大的数，大于它的数越少，因此排名越高。主查询已经按照分数递减排序，因此子查询得到的就是递增的排名
-- Rank是关键字，需要用单引号引起来。
select b.Score as Score,
(select count(distinct a.Score) from Scores as a where a.Score>=b.Score)
as 'Rank' from Scores as b order by b.Score desc;