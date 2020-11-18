package com.zuoyy.modules.common;

import com.zuoyy.common.constant.StatusConst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T> {

    /**
     * 批量更新数据状态
     * #{#entityName} 实体类对象
     * @param status 状态
     * @param id ID列表
     * @return 更新数量
     */
    @Modifying
    @Transactional
    @Query("update #{#entityName} set status = ?1  where id in ?2 and status <> " + StatusConst.DELETE)
    Integer updateStatus(int status, List<String> id);


}
