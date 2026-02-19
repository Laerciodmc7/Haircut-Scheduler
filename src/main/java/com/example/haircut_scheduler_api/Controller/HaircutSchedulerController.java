package com.example.haircut_scheduler_api.Controller;

import com.example.haircut_scheduler_api.Infrastructure.Entity.HaircutScheduler;
import com.example.haircut_scheduler_api.Service.HaircutSchedulerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/scheduler")
public class HaircutSchedulerController {

    private final HaircutSchedulerService schedulerService;

    // Injeção de dependência
    public HaircutSchedulerController(HaircutSchedulerService schedulerService){
        this.schedulerService = schedulerService;
    }

    @PostMapping
    public ResponseEntity<HaircutScheduler> saveAppointment(@RequestBody HaircutScheduler appointment){
        return ResponseEntity.accepted().body(schedulerService.saveAppointment(appointment));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAppointment(@RequestParam String clientName,
                                                  @RequestParam LocalDateTime appointmentDateTime){
        schedulerService.deleteAppointment(clientName, appointmentDateTime);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<HaircutScheduler>> getAppointment(@RequestParam LocalDate date){
        return ResponseEntity.ok(schedulerService.getAppointment(date));
    }

    @PutMapping
    public ResponseEntity<HaircutScheduler> updateAppointment(@RequestBody HaircutScheduler appointment,
                                                              @RequestParam String clientName,
                                                              @RequestParam LocalDateTime appointmentDateTime){

        return ResponseEntity.accepted().body(schedulerService.updateAppointment(appointment, clientName, appointmentDateTime));
    }
}
