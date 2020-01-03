package com.csii.ljj.thread17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03_SynchroniziedList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        // 传入一个list，返回一个加了锁的list
        List<String> list2 = Collections.synchronizedList(list);
    }
}
