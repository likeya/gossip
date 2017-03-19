package me.yufan.gossip.mybatis.mapper.base;

import me.yufan.gossip.mybatis.entity.base.IdEntity;

import java.util.List;

/**
 * Implements the method you need in mybatis xml file
 */
public interface BaseMapper<E extends IdEntity> {

    boolean insert(E e);

    void batchInsert(List<E> list);

    E queryOne(Long id);

    List<E> query(E e);

    List<E> queryAll();

    boolean update(E e);

    boolean delete(Long id);

    boolean deleteAll();
}
