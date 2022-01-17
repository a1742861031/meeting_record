package com.bobo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobo.vo.RecordListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bobo
 * @since 2022-01-12
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    IPage<RecordListVo> getRecordList(IPage<RecordListVo> page);
}
