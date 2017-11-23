package com.example.demo.dao.base;

import java.util.List;

/**
 * Created by rapid-generator.
 */
public interface BaseDao<T, Q> {

    Integer insert(T t);

    Integer update(T t);

    Integer delete(Long id);

    T getById(Long id);

    List<T> selectListByCondition(Q q);

    List<Long> selectIdsByCondition(Q q);

    List<T> selectBatchByIds(List<Long> ids);

    Integer insertBatch(List<T> list);
}
