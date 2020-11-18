package com.zuoyy.modules.system.query;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.Dict;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author zuo
 */
@Getter
@Setter
public class DictQuery extends BaseQuery implements Specification<Dict> {

    private Integer status = StatusEnum.OK.code;
    private Integer type;

    @Override
    public Predicate toPredicate(Root<Dict> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<Predicate>();
        list.add(cb.equal(root.get("status").as(Integer.class), status));
        if (!StringUtils.isEmpty(getKeyword())) {
            String keyword = getKeyword();
            Predicate p1 = cb.like(root.get("name").as(String.class), "%" + keyword + "%");
            Predicate p2 = cb.like(root.get("title").as(String.class), "%" + keyword + "%");
            list.add(cb.or(p1, p2));
        }
        if(type!=null){
            list.add(cb.equal(root.get("type").as(Integer.class), type));
        }
        Predicate[] p = new Predicate[list.size()];
        return cb.and(list.toArray(p));
    }

}