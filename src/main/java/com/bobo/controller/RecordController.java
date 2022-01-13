package com.bobo.controller;


import com.bobo.service.RecordService;
import com.bobo.vo.ErrorCode;
import com.bobo.vo.R;
import com.bobo.vo.RecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bobo
 * @since 2022-01-12
 */
@RestController
@RequestMapping("/record")
@Api(tags = "会议记录管理")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @ApiOperation("获取所有的会议记录")
    @GetMapping("list")
    public R getRecords() {
        List<RecordVo> records = recordService.getRecords();
        return new R(records);
    }

    @ApiOperation("新增会议记录")
    @PostMapping("")
    public R addRecord(@RequestBody RecordVo recordVo) {
        Boolean add = recordService.addRecord(recordVo);
        if (add) {
            return new R();
        }
        return new R(ErrorCode.Operation_error.getCode(), "添加会议失败");
    }

    @ApiOperation("修改会议记录")
    @PutMapping("")
    public R editRecord(RecordVo recordVo) {
        Boolean edit = recordService.editRecord(recordVo);
        if (edit) {
            return new R();
        }
        return new R(ErrorCode.Operation_error.getCode(), "编辑会议失败");
    }
    @ApiOperation("删除会议")
    @DeleteMapping("{id}")
    public R deleteRecord(@PathVariable Integer id){
        boolean delete = recordService.deleteByRecordId(id);
        if(delete){
            return new R();
        }
        return new R(ErrorCode.Operation_error.getCode(),"删除会议失败");
    }
}

