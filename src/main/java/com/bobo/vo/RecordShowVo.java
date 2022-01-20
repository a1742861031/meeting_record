package com.bobo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description 查看单条记录显示的Vo
 * @Date 2022/1/20 10:22 AM
 * @Created by bobo
 */
@Data
public class RecordShowVo {
    private String title;
    private String content;
    private Date date;
    private String time;
    private String place;
    private List<FileVo> files;
    private List<String> attend;
    private List<RecordVo.NonAttendanceVo> nonAttend;

    @Data
    public static class FileVo {
        String filename;
        String url;
    }
}
