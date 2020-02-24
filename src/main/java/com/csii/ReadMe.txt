=============================第一部分=================================================================
1. synchronized 锁定的是对象，如果该方法时静态的，锁定的是Class对象，非静态方法，锁定的是(this)对象——堆中对象
2. 同一对象的同步方法和非同步方法，可同时被多个线程调用，多个同步方法，其他线程只能等待释放对象锁，才能调用该对象的同步方法
3. 子类对象可以调用父类的同步方法
4. 程序执行过程中出现异常，默认释放锁
5.volatile 只解决了可见性，synchronized 及解决了可见性也解决了原子性
6.锁 是锁在堆空间里面，不是在栈空间里面;
7. 不要以字符常量作为锁对象，有可能类库和你锁的对象是同一个，容易产生死锁
8.案例：thread-1 进行容器累加，thread-2 在 容器累加到 5 进行结束
        ① 使用 wait() 和 notify() 实现
        ② 使用门栓 CountDownLatch 设置初始值，在这个初始值减少到0 则门栓打开 进行实现thread-2 停止

=============================第二部分==========包名：thread13开始=======================================================
1.使用 ReentrantLock 替换 synchronized
2. 区别：① ReentrantLock 需要手动释放锁；synchronized 是 jvm 管理释放锁，抛错也一样（ReentrantLock 抛错也不会自动关闭锁）
        ② ReentrantLock 有tryLock 方法，可以在方法中尝试获取锁对象，从而进行逻辑操作
        ③ ReentrantLock 可以指定公平锁，synchronized 是非公平锁
        注：公平锁：thread-2,thread-3,thread-4 同时竞争一把锁，假设 thread-2 已经等待 1s ，thread-3 等待 5s ，thread-4 等待 10s
                   本着公平原则，thread-4 应该先获取到锁
            非公平锁：thread-2,thread-3,thread-4 同时竞争一把锁，假设 thread-2 已经等待 1s ，thread-3 等待 5s ，thread-4 等待 10s
                    有可能 thread-2 先获取到锁
3. 案例 ：thread16包中：有售票的小程序，使用到了并发容器 ConcurrentLinkedDeque ，直接使用list 需要加锁，判断跟操作需要加锁，防止操作跟判断之间被打断
         thread17包中：容器，

=============================第三部分==========包名：thread18开始=======================================================
1. Executor 、ExecutorService （执行器）; Callable 、Runnable(任务)，Executors
     (1)Executor  :用于执行一个任务，只有execute
     (2)ExecutorService：执行的一个服务，除了有execute,还有submit方法——可以往里面扔Callable任务
     (3)Callable ,线程有返回值
     (4)Runnable , 线程无返回值
     (5)Executors , 工具类，操作Executor、ExecutorService、Callable 的工具类
