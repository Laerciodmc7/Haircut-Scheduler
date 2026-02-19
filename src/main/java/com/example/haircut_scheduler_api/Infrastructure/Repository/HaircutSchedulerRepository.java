package com.example.haircut_scheduler_api.Infrastructure.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.haircut_scheduler_api.Infrastructure.Entity.HaircutScheduler;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface HaircutSchedulerRepository extends JpaRepository<HaircutScheduler, Integer> {

    HaircutScheduler findByServiceAndAppointmentDateTimeBetween(String service, LocalDateTime appointmentDateTime,
                                                             LocalDateTime endOfService);

    List<HaircutScheduler> findByAppointmentDateTimeBetween(LocalDateTime Start, LocalDateTime End);

    HaircutScheduler findByClientNameAndAppointmentDateTime(String clientName, LocalDateTime appointmentDateTime);

    @Transactional
    void deleteByClientNameAndAppointmentDateTime(String clientName, LocalDateTime appointmentDateTime);
}
