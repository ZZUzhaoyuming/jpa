package com.zym.service;

import com.zym.entity.Persion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersionService {

    //查所有
    public List<Persion> selectAll();
    //查单个
    public Persion selectOne(Long id);
    //分页查
    public List<Persion> selectPage(int page,int count);
    //修改
    public void modify(Persion persion);
    //删除
    public void delete(Long id);
    //添加
    public void add(Persion persion);
    //模糊查询
    public List<Persion> selectLike(String name);

    //多条件查询
    public List<Persion> findAll(int pageNum,int pageSize,Persion persion);

}
