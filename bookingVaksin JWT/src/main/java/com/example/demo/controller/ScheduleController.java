package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Schedule;
import com.example.demo.entity.dto.ScheduleDTO;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.service.ScheduleService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "")
    public List<Schedule> getSchedule() {
        return scheduleService.getSchedule();
    }
    @GetMapping(value = "/{id}")
    public Optional <Schedule> getSchedule(@PathVariable(value = "id") Long id) {
        return scheduleService.getScheduleById(id);
    }
    @PostMapping(value = "")
    public Schedule createNewSchedule(@RequestBody ScheduleDTO request) {
        return scheduleService.save(request);
    }
    @DeleteMapping(value = "/{id}")
    public String deleteSchedule(@PathVariable (value = "id") Long id) {
      return scheduleService.deleteSchedule(id);
    }
    @PutMapping(value = "/{id}")
    public Optional<Schedule> updateSchedule(
        @PathVariable Long id, @RequestBody ScheduleDTO  request) {
            return scheduleService.updateSchedule(id, request);
    }
    // @PutMapping("/schedule/{id}")
    // public Optional<Schedule> updateSchedule(
    //         @PathVariable Long id,
    //         @RequestBody Schedule schedule) {
    //     Optional<Schedule>scheduleById = scheduleRepository.findById(id);

    //     scheduleById.ifPresent(res -> {
    //         res.setIdSchedule(schedule.getIdSchedule());
    //         res.setIdSession(schedule.getIdSession());
    //         res.setStart(schedule.getStart());
    //         res.setEnd(schedule.getEnd());
    //         scheduleRepository.save(res);
    //     });
    //     return scheduleById;
    // }
  
    // @GetMapping("/scheduleDTO")
    //     public List<ScheduleDTO> getScheduleDTO() {
    //         List<Schedule> schedule = scheduleRepository.findAll();
    //         List<ScheduleDTO> scheduleDTO = new ArrayList<>();
    //         schedule.forEach(res -> {
    //             ScheduleDTO schedule1 = new ScheduleDTO();
           
    //             schedule1.setUpdate_at(res.getUpdate_at());
    //             schedule1.setCreated_ad(res.getCreated_ad());
    //             schedule1.setCreated_by(res.getCreated_by());
    //             scheduleDTO.add(schedule);
    //         });
    //         return scheduleDTO;
    //     }

}
