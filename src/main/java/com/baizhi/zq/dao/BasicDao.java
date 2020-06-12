package com.baizhi.zq.dao;

import java.util.List;

public interface BasicDao<T> {
    List<T> queryAll();

    T queryById(String id);

    void add(T t);

    void update(T t);

    void delete(String id);
}
