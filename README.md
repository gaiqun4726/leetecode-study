# 如何有效的做算法题

[如何有效的做算法题](https://www.cnblogs.com/sskyy/p/8268976.html)

1. 使用番茄时钟法，25分钟之内高效完成任务。如果低效，立即停止番茄时钟。番茄时钟内，给自己留下积极的正向反馈。

2. 每个番茄时钟，开始前有规划，结束时有总结、有产出。

3. 第一个番茄时钟用来分析题目。目标：分析出题目类型，需要用到的技术，边界条件。 产出：上述目标是否达成，产出可以是解题思路、边界条件。总结：题目的关键点是什么。

4. 第二个番茄时钟用来实现代码。目标：高效验证解题思路。产出：是否高效实现了代码。总结：实现上有哪些不高效的地方。

5. 题目大致分为三类：需要考虑复杂边界的问题（逻辑思维），需要利用特定数据结构的问题（数据结构），数学问题、剪枝、动态规划等算法问题（算法）。

6. 可以到leetcode上，根据过滤器，每种题目筛一部分题目做一下，找找分类的感觉。

7. 持续的练习，积累，找到分类的感觉；找到使用番茄时钟的感觉；找到高效coding的感觉。

8. 写好的代码，添加注释。无论是上机或是白板

## 计划

leetcode上共有39个标签。在2020年3月31号之前，至少一个标签做一题。即一天至少做两题。

3月11日：leet1013

3月12日：leet1288, leet1071

3月13日：leet169, leet1019

3月14日：leet300

3月15日：leet695

3月16日：P0106

3月17日：P0204，leet1160，P1606

3月18日：E836，Q37

3月19日：M846，E409

3月20日：无

3月21日：Q40，M365，M93

3月22日：M945，E674，H128，H42，H354

3月23日：E876，M56，M103

3月24日：P1716，Review：E121,E122

3月25日：E892

3月26日：E999

3月27日：E914，M93回溯学习，M120

3月28日：M236，M820，H84

3月29日：H85，M221，M46，M60，M93，M120，M1162，M148

3月30日：Q62

3月31日：M912

4月1日：M1111

4月2日：E225，M289，M60

4月3日：M8，M153

4月4日：H42，E543，E1103，E994

4月5日：H460

4月6日：M1143，H72

4月7日：P0107

4月8日：Q13

4月9日：M22，P1001

4月10日：Q57，M151

4月11日：H887，M150，Q59

4月12日：P1603，H224，P1626

4月13日：M355

4月14日：M445，M322

4月15日：M542，M114，Q03，Q09

4月16日：温习M56，Q24，E234

4月17日：M55，E461

4月18日：M11，E617，M102，M105

4月19日：M337，E108，E125，E412

4月20日：M200，M36

4月21日：M1248，M1114

## 解题技巧总结

O(n^2)复杂度一般是暴力求解的复杂度，一般不是目标解。可以考虑使用特定的算法降低复杂度。

O(nlogn)复杂度一般是快排的复杂度，看看能不能通过排序求解问题。

O(n)复杂度一般是一次遍历的复杂度，看看能不能通过特定数据结构或者数学规律求解。使用栈可以用线性时间求解一些问题。

O(logn)复杂度一般是二分查找的复杂度。前提是数组有序

有直观的想法后，评估时间复杂度。然后看看能不能在当前复杂度的基准下，选用适当的方法降低复杂度。

在时间复杂度和空间复杂度不可兼得时，优先考虑时间复杂度。即用空间换时间的思想。

子串vs子序列：子串（substring）必须是连续的；子序列（subsequence）可以是不连续的，但需要是有序的。

对于数据结构类问题，想办法找到合适的数据结构求解。思路不要只局限于哈希、栈、队列，还有树。
要不断学习新的数据结构，比如并查集、字典树等。

字典树：对于需要频繁的判断一个单词是否是另一个单词的前缀、后缀的问题，可以考虑用字典树解决。

回溯法可以在递归函数的入口判断是否返回，也可以在循环里判断。使用第二种在循环里判断。避免不必要的麻烦。

做题一定要充分发掘题目中给定的信息。漏掉信息可能导致无法求解问题，这跟解数学问题是一样的。

一定要充分理解题意，记住题目信息。别做着做着忘了要求什么。

对于while循环，循环结束有可能是不满足循环条件了，一定要注意此时条件里的值取值可能是越界的。