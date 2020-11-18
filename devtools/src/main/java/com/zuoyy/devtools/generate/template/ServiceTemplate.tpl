##Service层模板

import java.util.List;


/**
 * @author zuo
 */
public interface #{entity}Service extends BaseService<#{entity}> {

    Page<#{entity}> getPageList(#{entity}Query query);
    List<User> findByQuery(#{entity}Query query);

}
