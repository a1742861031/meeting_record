package com.bobo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description 会议列表
 * @Date 2022/1/17 12:20 AM
 * @Created by bobo
 */
@Data
public class RecordListVo {
    private Integer id;
    private String recorder;
    private String userAvatar;
    private Date date;
    private String time;
    private Integer readNum;
    private String content;
}
