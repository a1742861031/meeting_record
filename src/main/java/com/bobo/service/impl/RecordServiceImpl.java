package com.bobo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.entity.Attendance;
import com.bobo.entity.File;
import com.bobo.entity.NonAttendance;
import com.bobo.entity.Record;
import com.bobo.mapper.AttendanceMapper;
import com.bobo.mapper.FileMapper;
import com.bobo.mapper.NonAttendanceMapper;
import com.bobo.mapper.RecordMapper;
import com.bobo.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.utils.HtmlSpiritUtils;
import com.bobo.vo.RecordListVo;
import com.bobo.vo.RecordShowVo;
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
    @Autowired
    private FileMapper fileMapper;

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
            ArrayList<String> attPeople = new ArrayList<>();

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
            //上传文件
            for (RecordVo.FileVo file : recordVo.getFiles()) {
                File file1 = new File();
                file1.setFilename(file.getName());
                file1.setUrl(file.getUrl());
                file1.setRecordId(Math.toIntExact(record.getId()));
                fileMapper.insert(file1);
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    /*修改记录*/
    public void editRecord(RecordVo recordVo) {
        Record record = new Record();
        BeanUtil.copyProperties(recordVo, record);
        recordMapper.updateById(record);
        //修改缺席人员
        QueryWrapper<NonAttendance> nonAttendanceWrapper = new QueryWrapper<>();
        nonAttendanceWrapper.eq("record_id", record.getId());
        nonAttendanceMapper.delete(nonAttendanceWrapper);
        for (RecordVo.NonAttendanceVo nonAttendance : recordVo.getNonAttendances()) {
            NonAttendance nonAttendance1 = new NonAttendance();
            nonAttendance1.setRecordId(record.getId());
            nonAttendance1.setPersonName(nonAttendance.getUserName());
            nonAttendance1.setReason(nonAttendance.getReason());
            nonAttendanceMapper.insert(nonAttendance1);
        }
        //修改出席人员
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>();
        wrapper.eq("record_id", record.getId());
        attendanceMapper.delete(wrapper);
        for (String attendance : recordVo.getAttendances()) {
            Attendance attendance1 = new Attendance();
            attendance1.setRecordId(record.getId());
            attendance1.setPersonName(attendance);
            attendanceMapper.insert(attendance1);
        }
        //修改文件列表
        QueryWrapper<File> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("record_id", record.getId());
        fileMapper.delete(wrapper1);
        for (RecordVo.FileVo file : recordVo.getFiles()) {
            File file1 = new File();
            file1.setFilename(file.getName());
            file1.setUrl(file.getUrl());
            file1.setRecordId(Math.toIntExact(record.getId()));
            fileMapper.insert(file1);
        }
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

    @Override
    public Page<RecordListVo> selectPage(Integer current, Integer limit) {
        Page<RecordListVo> page = new Page<>(current, limit);
        recordMapper.getRecordList(page);
        for (RecordListVo record : page.getRecords()) {
            String content = HtmlSpiritUtils.delHTMLTag(record.getContent());
            record.setContent(content);
        }
        return page;
    }

    @Override
    @Transactional
    public RecordShowVo getRecordShow(Integer id) {
        Record record = recordMapper.selectById(id);
        record.setReadNum(record.getReadNum() + 1);
        RecordShowVo recordShowVo = new RecordShowVo();
        BeanUtil.copyProperties(record, recordShowVo);
        QueryWrapper<File> fileWrapper = new QueryWrapper<>();
        fileWrapper.eq("record_id", id);
        List<File> files = fileMapper.selectList(fileWrapper);
        ArrayList<RecordShowVo.FileVo> fileVos = new ArrayList<>();
        for (File file : files) {
            RecordShowVo.FileVo fileVo = new RecordShowVo.FileVo();
            fileVo.setFilename(file.getFilename());
            fileVo.setUrl(file.getUrl());
            fileVos.add(fileVo);
        }
        recordShowVo.setFiles(fileVos);
        QueryWrapper<Attendance> attendanceWrapper = new QueryWrapper<>();
        attendanceWrapper.eq("record_id", id);
        ArrayList<String> attendString = new ArrayList<>();
        for (Attendance attendance : attendanceMapper.selectList(attendanceWrapper)) {
            attendString.add(attendance.getPersonName());
        }
        recordShowVo.setAttend(attendString);
        QueryWrapper<NonAttendance> nonAttendanceWrapper = new QueryWrapper<>();
        ArrayList<RecordVo.NonAttendanceVo> nonAttendString = new ArrayList<>();
        nonAttendanceWrapper.eq("record_id", id);
        for (NonAttendance nonAttendance : nonAttendanceMapper.selectList(nonAttendanceWrapper)) {
            RecordVo.NonAttendanceVo nonAttendanceVo = new RecordVo.NonAttendanceVo();
            nonAttendanceVo.setUserName(nonAttendance.getPersonName());
            nonAttendanceVo.setReason(nonAttendance.getReason());
            nonAttendString.add(nonAttendanceVo);
        }
        recordShowVo.setNonAttend(nonAttendString);
        recordMapper.updateById(record);
        return recordShowVo;
    }

    @Override
    public RecordVo getEditRecord(Integer id) {
        Record record = recordMapper.selectById(id);
        RecordVo recordVo = new RecordVo();
        BeanUtil.copyProperties(record, recordVo);
        QueryWrapper<File> fileWrapper = new QueryWrapper<>();
        fileWrapper.eq("record_id", id);
        List<File> files = fileMapper.selectList(fileWrapper);
        ArrayList<RecordVo.FileVo> fileVos = new ArrayList<>();
        for (File file : files) {
            RecordVo.FileVo fileVo = new RecordVo.FileVo();
            fileVo.setName(file.getFilename());
            fileVo.setUrl(file.getUrl());
            fileVos.add(fileVo);
        }
        recordVo.setFiles(fileVos);
        QueryWrapper<Attendance> attendanceWrapper = new QueryWrapper<>();
        attendanceWrapper.eq("record_id", id);
        ArrayList<String> attendString = new ArrayList<>();
        for (Attendance attendance : attendanceMapper.selectList(attendanceWrapper)) {
            attendString.add(attendance.getPersonName());
        }
        recordVo.setAttendances(attendString);
        QueryWrapper<NonAttendance> nonAttendanceWrapper = new QueryWrapper<>();
        ArrayList<RecordVo.NonAttendanceVo> nonAttendString = new ArrayList<>();
        nonAttendanceWrapper.eq("record_id", id);
        for (NonAttendance nonAttendance : nonAttendanceMapper.selectList(nonAttendanceWrapper)) {
            RecordVo.NonAttendanceVo nonAttendanceVo = new RecordVo.NonAttendanceVo();
            nonAttendanceVo.setUserName(nonAttendance.getPersonName());
            nonAttendanceVo.setReason(nonAttendance.getReason());
            nonAttendString.add(nonAttendanceVo);
        }
        recordVo.setNonAttendances(nonAttendString);
        return recordVo;
    }
}
