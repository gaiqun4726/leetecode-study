# Guidance

## java基础

### ArrayList扩容机制（java8）
默认初始容量为10。每次扩容时，尝试扩为原来的1.5倍，如果不满足对容量的需求，则扩为所需容量。
扩容时内部使用了Arrays.copyOf()，把原数组拷贝到新数组。
modCount用于快速失败。

### hashCode与equals
重写了equals必须重写hashCode，反之不需要。因为hashCode的实现会用到equals，因此必须重写hashCode。

### 浅拷贝深拷贝
对于对象中的引用，深拷贝需要另创建一个对象，而不是引用原对象。

### HashMap底层实现
当n为2的幂次时，(n-1)&hash等价于hash%n，这在计算hash值对应的数组下标时更快，因此HashMap的数组长度总是2的幂次。
HashMap每次扩容，都是扩容为原大小的2倍。初始化时，默认大小是16。
在size>threshold时，HashMap扩容。即实际元素的个数，大于数组长度*负载因子时，扩容为原来的2倍。
resize时，遍历原HashMap的每个元素及其链表元素，重新hash到新数组的索引。
查找、解决hash冲突时考虑的都是key，不用考虑value。
java8中，HashMap底层实现使用了数组+链表/红黑树的数据结构。
在链表长度大于8的时候，链表会转换为红黑树。

HashSet底层使用HashMap实现，每个值作为HashMap的key，HashMap的value用了一个空Object。
HashSet检查重复的过程，先通过hashCode，再通过equals判断两个元素是否相同。
ConcurrentHashMap底层与HashMap基本一致，只是在数组每个Node上都加了锁。

## 并发与多线程

### CAS的使用
乐观锁的实现有版本号和CAS算法两种方式。CAS的算法，可能会存在ABA问题。其实如果加上版本号，即用版本号的方法就不会有ABA问题。
通过AtomicStampReference类来避免ABA问题。如果对于ABA没有要求，则直接用Atomic相关的CAS操作即可。

### 自旋锁
[面试必备自旋锁](https://zhuanlan.zhihu.com/p/40729293)
如果一个线程在没获取到锁的时候，不是阻塞，而是不断自旋等待锁，则这把锁就是自旋锁。
自旋锁的好处是不需要进行线程上下文切换，即从内核态到用户态的切换，执行速度更快。缺点是对cpu的耗用较大。
通过增加线程计数器可实现可重入自旋锁；通过增加服务号与排队号可实现公平自旋锁。

## 数据库

### spring事务传播行为
spring共有七种事务传播行为
PROPAGATION_REQUIRED: 外层有事务则加入外层，否则开启事务。内外层一体，同时回滚
PROPAGATION_SUPPORTS: 外层有事务则加入事务，否则不开启事务。
PROPAGATION_REQUIRES_NEW: 外层有事务则挂起外层，重新开启事务。内外层的回滚相互独立。
PROPAGATION_MANDATORY: 外层必须有事务，否则抛出异常
PROPAGATION_NOT_SUPPORTED: 外层有事务则挂起，内层以非事务方式执行
PROPAGATION_NEVER: 外层不能有事务，否则抛出异常
PROPAGATION_NESTED: 内层事务是外层事务的子事务，即外层事务提交/回滚，子事务会一同提交/回滚。
内层事务回滚，外层事务只会回到savepoint，不会整体回滚。

## redis

### skiplist
跳表是redis中zset的底层实现数据结构。其实zset的实现包括skiplist，dic，ziplist。
从zset的功能来看，需要实现排序的set。排序功能使用skiplist，去重功能使用dict。

跳表是在有序链表的基础上进行改进，适合进行查找与范围查找。
每次插入新节点时，会随机决定该节点的层数，层数越大概率越低。插入新节点不会更改已有节点的层数。

相对于平衡二叉树和哈希表，跳表的查询时间复杂度也是O(logN)。
范围查找直接从起始节点向后遍历（平衡二叉树如果保存中序后继指针也可以）。
插入、删除都是常数时间复杂度。平衡二叉树的插入删除较麻烦。
哈希表适合等值查找，范围查找并不适合。

### 分布式锁
[基于redis的分布式锁](https://zhuanlan.zhihu.com/p/40915772)

单线程锁：`set resource random nx px timeout`; 释放锁 if `get resource` == random then `rm resource`
加锁尝试把资源设为全局唯一的随机值（可以用全局ID），并设置超时时间。
释放锁如果当前被锁定的随机值与自己的随机值相同，则可以释放锁。

分布式锁：RedLock算法。
顺序从五个实例获取单线程锁，获取到3个（超过半数）则获取锁成功，每个锁的获取方法与获取单线程锁的方法相同。
获取失败或使用完毕会主动释放锁，否则超时被动释放锁。

## zookeeper
关键特性：永久节点、临时节点、顺序节点；watcher机制。
应用：全局唯一ID、发布订阅、分布式锁、master选举

### zab协议
崩溃恢复阶段：每台机器以<id, zxid>作为提案，各个机器选择提案中zxid较大或id较大的提案，过半机器选择提案后选举结束。
广播阶段：leader把消息广播给follower，收到过半机器的ack以后，让follower commit。就是2PC。
通过zxid的epoch id，过滤掉崩溃恢复前未commit的消息，恢复已经commit但是没有被同步的消息。

### 分布式锁
redis和zookeeper都可以提供分布式锁功能，zookeeper基于watcher机制可以收到锁释放的通知，redis只能客户端不断自旋尝试获取锁。

curator框架对常用的zookeeper用法进行了封装。
分布式锁的实现原理是，每个请求锁的应用在锁结点下新建临时顺序节点。如果自己的节点是第一个则获取锁，否则监听前一个节点。

实现读写锁的方法，读写锁都在锁结点下创建临时顺序节点，读写节点的序号有区别。
读节点get锁节点下的第一个临时顺序节点，如果是读节点则获取锁成功，否则监听第一个结点。
锁节点判断自己是不是第一个节点，如果不是则监听前一个节点。