1.对于map/set的选择使用
    HashMap
    TreeMap
    LinkedHashMap

    HashTable   —— 同步，并发小
    Collections.sychronizedXXX ——并发比较小

    ConcurrentHashMap   —— 高并发
    ConcurrentSKipListMap  —— 排序高并发
2.队列
    ArrayList
    LinkedList
    Collections.synchroniedXXX
    CopyOnWriteList
    Queue
        ConCurrentLinkedQueue
        BlockingQueue
            LinkedBQ
            ArrayBQ
            TransferQueue
            SynchronusQueue   容量为0，生产者起来找消费者
        DelayQueue  执行定时任务
