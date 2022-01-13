package com.bobo.service.impl;

import com.bobo.entity.Type;
import com.bobo.mapper.TypeMapper;
import com.bobo.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bobo
 * @since 2022-01-13
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
