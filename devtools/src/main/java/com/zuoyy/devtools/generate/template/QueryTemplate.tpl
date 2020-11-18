##Service层模板


import lombok.Getter;
import lombok.Setter;
import com.zuoyy.common.enums.StatusEnum;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class #{entity}Query extends BaseQuery implements Specification<#{entity}> {

    private Integer status = StatusEnum.OK.code;

    @Override
    public Predicate toPredicate(Root<#{entity}> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<Predicate>();
        list.add(cb.equal(root.get("status").as(Integer.class), status));

        Predicate[] p = new Predicate[list.size()];
        return cb.and(list.toArray(p));
    }

}
