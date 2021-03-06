package com.bobo.vo;


import lombok.Data;

import java.util.List;

/**
 * @Description 会议记录Vo
 * @Date 2022/1/12 10:35 PM
 * @Created by bobo
 */
@Data
public class RecordVo {
    private String title;

    private Long id;

    private String attachment;

    private String content;

    private String date;

    private String place;

    private Long readNum;

    private String recorder;

    private String time;

    //出席人员
    private List<String> attendances;
    //缺席人员
    private List<NonAttendanceVo> nonAttendances;

    private List<FileVo> files;

    @Data
    public static class NonAttendanceVo {
        String userName;
        String reason;
    }

    @Data
    public static class FileVo {
        String name;
        String url;
    }
}
