package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Schedule;
import com.example.demo.entity.Session;
import com.example.demo.entity.dto.ScheduleDTO;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VaksinRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScheduleService {


    @Autowired
    private SessionRepository sessionRepository;

    @Autowired 
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getSchedule() {
       return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }
    public Schedule save(ScheduleDTO request) {
        try{    
            Schedule schedule = new Schedule();
            log.info("search session id {}", request.getIdSession());
            Session session = sessionRepository.searchById(request.getIdSession())
                .orElseThrow(()->  new Exception( " Id Session" + request.getIdSession() + "Not Found"));
          
            log.info("save session");
            schedule.setSession(session);
            schedule.setStart(request.getStart());
            schedule.setEnd(request.getEnd());;
            scheduleRepository.save(schedule);
    
            return schedule;
        }
            catch(Exception e){
                log.error("save error");
                return null;
            }
    }

    public Optional<Schedule> updateSchedule( Long id, ScheduleDTO request) {
        try{    
            Optional <Schedule> Schedule = scheduleRepository.findById(id);
            Session session = sessionRepository.searchById(request.getIdSession())
                .orElseThrow(()->  new Exception( " Id Session" + request.getIdSession() + "Not Found"));
          
        Schedule.ifPresent(res -> {
            res.setSession(session);
            res.setStart(request.getStart());
            res.setEnd(request.getEnd());
            scheduleRepository.save(res);
        });
        return Schedule;
    }
        catch(Exception e){
            return null;
        }
    }
    public String deleteSchedule(Long id) {
        Optional<Schedule> scheduleById = scheduleRepository.findById(id);
        scheduleById.ifPresent(res -> {
            scheduleRepository.delete(res);
        });
        return "Deleted";
    }
}
