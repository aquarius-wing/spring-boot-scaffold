package com.example.demo.service.base;

import java.util.List;

public interface BaseService<T, Q> {

    /**
     * 新增
     * @param t
     * @return 操作结果
     */
    Integer insert(T t);

    /**
     * 插入一列对象
     * @param list
     * @return 操作结果
     */
    Integer insertBatch(List<T> list);

    /**
     * 根据id删除
     * @param id
     * @return 操作结果
     */
    Integer delete(Long id);

    /**
     * 根据新对象的id修改已有数据
     * @param t
     * @return 操作结果
     */
    Integer update(T t);

    /**
     * 根据id获取
     * @param id
     * @return 对象
     */
    T getById(Long id);

    /**
     * 根据查询条件获取一列对象
     * @param q
     * @return 一列对象
     */
    List<T> selectListByCondition(Q q);

    /**
     * 根据查询条件获取一列对象的id
     * @param q
     * @return 一列id
     */
    List<Long> selectIdsByCondition(Q q);

    /**
     * 根据一列id获取对应的对象
     * @param ids
     * @return 一列对象
     */
    List<T> selectBatchByIds(List<Long> ids);

}
