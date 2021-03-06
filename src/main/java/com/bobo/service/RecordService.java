package com.bobo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bobo.vo.RecordListVo;
import com.bobo.vo.RecordShowVo;
import com.bobo.vo.RecordVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bobo
 * @since 2022-01-12
 */
public interface RecordService extends IService<Record> {

    List<RecordVo> getRecords();

    Boolean addRecord(RecordVo recordVo);

    void editRecord(RecordVo recordVo);

    boolean deleteByRecordId(Integer id);

    Page<RecordListVo> selectPage(Integer current, Integer limit);

    RecordShowVo getRecordShow(Integer id);

    RecordVo getEditRecord(Integer id);
}
