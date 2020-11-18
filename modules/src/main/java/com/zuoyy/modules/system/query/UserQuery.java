package com.zuoyy.modules.system.query;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.User;
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

@Getter
@Setter
public class UserQuery extends BaseQuery implements Specification<User> {

    private Integer status = StatusEnum.OK.code;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<Predicate>();
        list.add(cb.equal(root.get("status").as(Integer.class), status));
        if (!StringUtils.isEmpty(getKeyword())) {
            String keyword = getKeyword();
            Predicate p1 = cb.like(root.get("username").as(String.class), "%" + keyword + "%");
            Predicate p2 = cb.like(root.get("nickname").as(String.class), "%" + keyword + "%");
            Predicate p3 = cb.like(root.get("phone").as(String.class), "%" + keyword + "%");
            list.add(cb.or(p1, p2, p3));
        }
        Predicate[] p = new Predicate[list.size()];
        return cb.and(list.toArray(p));
    }

}
