package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Schedule;
import com.example.demo.entity.dto.ScheduleDTO;
import com.example.demo.repository.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @GetMapping("/schedule")
    public List<Schedule> getschedule() {
        return scheduleRepository.findAll();
    }

    @GetMapping("/schedule/{id}")
    public Optional<Schedule> getscheduleById(@PathVariable Long id) {
        return scheduleRepository.findById(id);
    }
    @PostMapping("/schedule")
    public Schedule createNewKelompok(@RequestBody Schedule payload) {
        return scheduleRepository.save(payload);
    }
    // @PutMapping("/schedule/{id}")
    // public Optional<Schedule> updateSchedule(
    //         @PathVariable Long id,
    //         @RequestBody Schedule schedule) {
    //     Optional<Schedule>scheduleById = scheduleRepository.findById(id);

    //     scheduleById.ifPresent(res -> {
    //         res.setIdHealth(schedule.getIdHealth());
    //         res.setNama(schedule.getNama());
    //         scheduleRepository.save(res);
    //     });
    //     return scheduleById;
    // }
    // @PutMapping("/user/{id}") 
    // public Optional<Schedule> updateSchedule(
    //     @PathVariable Long id, 
    //     @RequestBody Schedule  schedule) {
    //         Optional<Schedule> scheduleById = scheduleRepository.findById(id);
        
    //     scheduleById.ifPresent(res -> {
    //         res.setStart(schedule.getStart());
    //         res.setNama(schedule.getNama());
    //         res.setNama(citizen.getNama());
    //         res.setGender(citizen.getGender());
    //         res.setTgl_lahir(citizen.getTgl_lahir());
    //         res.setImage(citizen.getImage());
    //         res.setUsername(citizen.getUsername());
    //         res.setPassword(citizen.getPassword());
    //         res.setUpdatedAt(citizen.getUpdatedAt());
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
    //             schedule1.setIdHealth(res.getIdHealth());
    //             schedule1.setNama(res.getNama());
    //             schedule1.setQuantity(res.getQuantity());
    //             schedule1.setUpdate_at(res.getUpdate_at());
    //             schedule1.setCreated_ad(res.getCreated_ad());
    //             schedule1.setCreated_by(res.getCreated_by());
    //             scheduleDTO.add(schedule);
    //         });
    //         return scheduleDTO;
    //     }

}
