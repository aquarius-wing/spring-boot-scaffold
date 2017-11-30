package ${basepackageservice}.base;

import ${basepackagedao}.base.BaseDao;
import org.springframework.context.support.ApplicationObjectSupport;

import java.util.List;


public abstract class BaseServiceImpl<T,Q> extends ApplicationObjectSupport implements BaseService<T,Q> {
	
	/**
	 * 获取业务实体对象对应的数据访问对象实例
	 * 
	 * @return 业务实体对象对应的数据访问对象实例
	 */
	protected abstract BaseDao<T,Q> getDao();

    /**
     * 新增
     *
     * @param t
     * @return 操作结果
     */
    @Override
    public Integer insert(T t) {
        return getDao().insert(t);
    }

    /**
     * 插入一列对象
     *
     * @param list
     * @return 操作结果
     */
    @Override
    public Integer insertBatch(List<T> list) {
        return getDao().insertBatch(list);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return 操作结果
     */
    @Override
    public Integer delete(Long id) {
        return getDao().delete(id);
    }

    /**
     * 根据新对象的id修改已有数据
     *
     * @param t
     * @return 操作结果
     */
    @Override
    public Integer update(T t) {
        return getDao().update(t);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return 对象
     */
    @Override
    public T getById(Long id) {
        return getDao().getById(id);
    }

    /**
     * 根据查询条件获取一列对象
     *
     * @param q
     * @return 一列对象
     */
    @Override
    public List<T> selectListByCondition(Q q) {
        return getDao().selectListByCondition(q);
    }

    /**
     * 根据查询条件获取一列对象的id
     *
     * @param q
     * @return 一列id
     */
    @Override
    public List<Long> selectIdsByCondition(Q q) {
        return getDao().selectIdsByCondition(q);
    }

    /**
     * 根据一列id获取对应的对象
     *
     * @param ids
     * @return 一列对象
     */
    @Override
    public List<T> selectBatchByIds(List<Long> ids) {
        return getDao().selectBatchByIds(ids);
    }
}
