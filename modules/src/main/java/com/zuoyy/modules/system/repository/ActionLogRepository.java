package com.zuoyy.modules.system.repository;

import com.zuoyy.modules.system.domain.ActionLog;
import com.zuoyy.modules.common.BaseRepository;

import java.util.List;

public interface ActionLogRepository extends BaseRepository<ActionLog, String> {

    List<ActionLog> findByModelAndRecordId(String model, String recordId);


}
