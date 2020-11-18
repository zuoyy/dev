package com.zuoyy.modules.system.query;

import com.zuoyy.modules.system.domain.ActionLog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zuo
 */
@Getter
@Setter
public class ActionLogQuery extends BaseQuery implements Specification<ActionLog> {

    private Integer type;

    @Override
    public Predicate toPredicate(Root<ActionLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<Predicate>();
        if (!StringUtils.isEmpty(getKeyword())) {
            String keyword = getKeyword();
            Predicate p1 = cb.like(root.get("name").as(String.class), "%" + keyword + "%");
            Predicate p2 = cb.like(root.get("message").as(String.class), "%" + keyword + "%");
            list.add(cb.or(p1, p2));
        }
        if(type!=null){
            list.add(cb.equal(root.get("type").as(Integer.class), type));
        }
        Predicate[] p = new Predicate[list.size()];
        return cb.and(list.toArray(p));
    }

}