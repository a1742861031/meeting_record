package com.bobo.vo;

import com.bobo.entity.Attendance;
import com.bobo.entity.NonAttendance;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description 会议记录Vo
 * @Date 2022/1/12 10:35 PM
 * @Created by bobo
 */
@Data
public class RecordVo {
    private Long id;

    private String attachment;

    private String content;

    private Date date;

    private String place;

    private Long readNum;

    private String recorder;

    private String time;

    private Boolean verification;
    //出席人员
    private List<Attendance> attendances;
    //缺席人员
    private List<NonAttendance> nonAttendances;
}
