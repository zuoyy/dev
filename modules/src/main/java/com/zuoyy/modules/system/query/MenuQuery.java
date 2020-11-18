package com.zuoyy.modules.system.query;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.Menu;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuQuery extends BaseQuery implements Specification<Menu> {

    private Integer status = StatusEnum.OK.code;
    private Integer type;

    @Override
    public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<Predicate>();
        list.add(cb.equal(root.get("status").as(Integer.class), status));
        if(type!=null){
            list.add(cb.equal(root.get("type").as(Integer.class), type));
        }
        Predicate[] p = new Predicate[list.size()];
        return cb.and(list.toArray(p));
    }


}
