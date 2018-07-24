package com.zym.service;

import com.zym.dao.PersionRepository;
import com.zym.entity.Persion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service("persionService")
@Transactional
public class PersionServiceImpl implements PersionService {

    @Autowired
    private PersionRepository persionRepository;

    @Override
    public List<Persion> selectAll() {
        List<Persion> alllist = persionRepository.findAll();
        return alllist;
    }

    @Override
    public Persion selectOne(Long id) {
        Persion one = persionRepository.findOne(id);
        return one;
    }

    /**
     * 分页查(成功)
     * new PageRequest(pageone, pagetwo) pageone表示第几页，下标从0开始，pagetwo表示展示多少条数据
     */
    @Override
    public List<Persion> selectPage(int page, int count) {
        List<Persion> list = new ArrayList<Persion>();
        Page<Persion> all = persionRepository.findAll(new PageRequest(page, count));
        for (Persion persion : all) {
            list.add(persion);
        }
        return list;
    }

    @Override
    public void modify(Persion persion) {
        persionRepository.updatePersionById(persion.getId(), persion.getName(), persion.getAge());
    }

    @Override
    public void delete(Long id) {
        persionRepository.delete(id);

    }

    @Override
    public void add(Persion persion) {
        persionRepository.save(persion);
    }

    @Override
    public List<Persion> selectLike(String name) {
        List<Persion> persions = persionRepository.findByNameLike(name);
        return persions;
    }

    @Override
    public List<Persion> findAll(int pageNum, int pageSize, final Persion persion) {
        Pageable pageable = new PageRequest(pageNum, pageSize);

        List<Persion> persionList = persionRepository.findAll(new Specification<Persion>() {
            @Override
            public Predicate toPredicate(Root<Persion> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                /*if (persion.getId() != null && persion.getId().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + persion.getId() + "%"));
                }*/
                if (persion.getName() != null && persion.getName().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + persion.getName() + "%"));
                }
                if (persion.getAge() != null && persion.getAge().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("age").as(String.class), "%" + persion.getAge() + "%"));
                }

                Predicate[] pre = new Predicate[predicates.size()];
                criteriaQuery.where(predicates.toArray(pre));
                return criteriaBuilder.and(predicates.toArray(pre));

            }
        }, pageable);

        return persionList;
    }
}
