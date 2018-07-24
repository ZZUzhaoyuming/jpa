package com.zym.dao;

import com.zym.entity.Persion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersionRepository extends JpaRepository<Persion,Long>{

    // 自定义方法
    //修改
    @Modifying
    @Query(value = "update persion set name = :name,agee = :agee where id = :id",nativeQuery = true)
    public void updatePersionById(@Param("id")Long id,@Param("name") String name,@Param("agee") String agee);

    //通过name模糊查询
    public List<Persion> findByNameLike(String name);

    public List<Persion> findAll(Specification<Persion> spc, Pageable pageable);

}
