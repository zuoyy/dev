package com.zuoyy.modules.system.repository;

import com.zuoyy.modules.system.domain.Dict;
import com.zuoyy.modules.common.BaseRepository;

public interface DictRepository extends BaseRepository<Dict, String> {


    Dict findByNameAndStatus(String name, int status);

    Dict findByNameAndIdNot(String name, String id);


}
