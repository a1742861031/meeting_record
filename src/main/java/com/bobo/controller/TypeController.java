package com.bobo.controller;


import com.bobo.entity.Type;
import com.bobo.service.TypeService;
import com.bobo.vo.ErrorCode;
import com.bobo.vo.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bobo
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/type")
@ApiOperation("类型操作")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/list")
    @ApiOperation("获取用户类型列表")
    public R getTypeList() {
        List<Type> list = typeService.list();
        return new R(list);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除用户类型")
    public R deleteType(@PathVariable Integer id) {
        boolean remove = typeService.removeById(id);
        if (remove) {
            return new R();
        }
        return new R(ErrorCode.Operation_error.getCode(), "删除失败");
    }

    @PostMapping()
    @ApiOperation("增加用户类型")
    public R addType(@RequestBody Type type) {
        boolean save = typeService.save(type);
        if (save) {
            return new R();
        }
        return new R(ErrorCode.Operation_error.getCode(), "增加失败");
    }
}

