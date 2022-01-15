package com.bobo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bobo.entity.Attendance;
import com.bobo.entity.NonAttendance;
import com.bobo.entity.Record;
import com.bobo.mapper.AttendanceMapper;
import com.bobo.mapper.NonAttendanceMapper;
import com.bobo.mapper.RecordMapper;
import com.bobo.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bobo
 * @since 2022-01-12
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private AttendanceMapper attendanceMapper;
    @Autowired
    private NonAttendanceMapper nonAttendanceMapper;

    @Override
    @Transactional
    public List<RecordVo> getRecords() {
        List<Record> records = recordMapper.selectList(null);
        ArrayList<RecordVo> recordVos = new ArrayList<>();
        for (Record record : records) {
            RecordVo recordVo = new RecordVo();
            BeanUtil.copyProperties(record, recordVo);
            QueryWrapper<Attendance> attendanceWrapper = new QueryWrapper<>();
            QueryWrapper<NonAttendance> nonAttendanceWrapper = new QueryWrapper<>();
            nonAttendanceWrapper.eq("record_id", record.getId());
            attendanceWrapper.eq("record_id", record.getId());
            List<Attendance> attendances = attendanceMapper.selectList(attendanceWrapper);
            List<NonAttendance> nonAttendances = nonAttendanceMapper.selectList(nonAttendanceWrapper);
            ArrayList<String > attPeople = new ArrayList<>();

            for (Attendance attendance : attendances) {
                attPeople.add(attendance.getPersonName());
                recordVo.setAttendances(attPeople);
            }
            ArrayList<RecordVo.NonAttendanceVo> nonAttPeople = new ArrayList<>();
            for (NonAttendance nonAttendance : nonAttendances) {
                RecordVo.NonAttendanceVo nonAttendanceVo = new RecordVo.NonAttendanceVo();
                nonAttendanceVo.setUserName(nonAttendance.getPersonName());
                nonAttendanceVo.setReason(nonAttendance.getReason());
                nonAttPeople.add(nonAttendanceVo);
            }
            recordVo.setNonAttendances(nonAttPeople);
            recordVos.add(recordVo);
        }
        return recordVos;
    }

    @Override
    @Transactional
    public Boolean addRecord(RecordVo recordVo) {
        System.out.println(recordVo);
        Record record = new Record();
        BeanUtil.copyProperties(recordVo, record);
        int insert = recordMapper.insert(record);
        if (insert > 0) {
            for (String attendance : recordVo.getAttendances()) {
                Attendance attendance1 = new Attendance();
                attendance1.setRecordId(record.getId());
                attendance1.setPersonName(attendance);
                attendanceMapper.insert(attendance1);
            }
            for (RecordVo.NonAttendanceVo nonAttendance : recordVo.getNonAttendances()) {
                NonAttendance nonAttendance1 = new NonAttendance();
                nonAttendance1.setRecordId(record.getId());
                nonAttendance1.setPersonName(nonAttendance.getUserName());
                nonAttendance1.setReason(nonAttendance.getReason());
                nonAttendanceMapper.insert(nonAttendance1);
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean editRecord(RecordVo recordVo) {
        Record record = new Record();
        BeanUtil.copyProperties(recordVo, record);
        int update = recordMapper.updateById(record);
        if (update > 0) {
            QueryWrapper<Attendance> attendanceWrapper = new QueryWrapper<>();
            QueryWrapper<NonAttendance> nonAttendanceWrapper = new QueryWrapper<>();
            attendanceMapper.delete(attendanceWrapper);
            nonAttendanceMapper.delete(nonAttendanceWrapper);
//            for (Attendance attendance : recordVo.getAttendances()) {
//                attendanceMapper.insert(attendance);
//            }
//            for (NonAttendance nonAttendance : recordVo.getNonAttendances()) {
//                nonAttendanceMapper.insert(nonAttendance);
//            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteByRecordId(Integer id) {
        int delete = recordMapper.deleteById(id);
        if (delete > 0) {
            QueryWrapper<Attendance> attendanceWrapper = new QueryWrapper<>();
            QueryWrapper<NonAttendance> nonAttendanceWrapper = new QueryWrapper<>();
            attendanceMapper.delete(attendanceWrapper);
            nonAttendanceMapper.delete(nonAttendanceWrapper);
            return true;
        }
        return false;
    }
}
