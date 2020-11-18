##Service层模板

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

/**
 * @author zuo
 */
@Service
public class #{entity}ServiceImpl extends BaseServiceImpl<#{entity}> implements #{entity}Service {

    @Autowired
    private #{entity}Repository #{name}Repository;



    @Override
    public Page<#{entity}> getPageList(#{entity}Query query) {
        // 创建分页对象
        Pageable pageable = PageRequest.of(query.getPageIndex(), query.getPageSize(), new Sort(query.getSort(),query.getColumn()));
        return #{name}Repository.findAll(query,pageable);
    }

    @Override
    public List<#{entity}> findByQuery(#{entity}Query query) {
        return #{name}Repository.findAll(query);
    }


}