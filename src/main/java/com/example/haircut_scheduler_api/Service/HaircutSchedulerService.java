package com.example.haircut_scheduler_api.Service;

import com.example.haircut_scheduler_api.Infrastructure.Entity.HaircutScheduler;
import com.example.haircut_scheduler_api.Infrastructure.Repository.HaircutSchedulerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class HaircutSchedulerService {

    private static final LocalTime morningStart = LocalTime.of(8, 0);      // horário que o expediente da manhã começa
    private static final LocalTime morningEnd = LocalTime.of(12, 0);       // horário que o expediente da manhã termina
    private static final LocalTime afternoonStart = LocalTime.of(14, 0);   // horário que o expediente da tarde começa
    private static final LocalTime afternoonEnd = LocalTime.of(18, 0);     // horário que o expediente da tarde termina

    private final HaircutSchedulerRepository appointmentRepository;

    // Construtor para injeção de dependencia
    public HaircutSchedulerService(HaircutSchedulerRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    // Verifica se o horário esta dentro expediente e retorna e retorna true caso esteja
    public boolean isInWorkingHours(LocalTime appointmentDateTime) {
        List<TimeRange> workingHours = List.of(
                new TimeRange(morningStart, morningEnd),
                new TimeRange(afternoonStart, afternoonEnd)
        );

        return workingHours.stream().anyMatch(
                period -> period.contains(appointmentDateTime));
    }

    public HaircutScheduler saveAppointment(HaircutScheduler appointment) {
        LocalDateTime appointmentDateTime = appointment.getAppointmentDateTime();
        LocalDateTime endOfService = appointment.getAppointmentDateTime().plusHours(1);

        HaircutScheduler conflictingAppointment =
                appointmentRepository.findByServiceAndAppointmentDateTimeBetween(
                        appointment.getService(),
                        appointment.getAppointmentDateTime(),
                        endOfService);

        if (!Objects.isNull(conflictingAppointment) || !isInWorkingHours(appointmentDateTime.toLocalTime())) {
            throw new RuntimeException("\n Time slot is already booked.\n");
        }

        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(String clientName, LocalDateTime appointmentDateTime) {
        appointmentRepository.deleteByClientNameAndAppointmentDateTime(clientName, appointmentDateTime);
    }

    public List<HaircutScheduler> getAppointment(LocalDate date) {
        return appointmentRepository.findByAppointmentDateTimeBetween(morningStart.atDate(date), afternoonEnd.atDate(date));
    }

    public HaircutScheduler updateAppointment(HaircutScheduler appointment, String clientName, LocalDateTime appointmentDateTime) {
        HaircutScheduler updatedAppointment = appointmentRepository.findByClientNameAndAppointmentDateTime(clientName, appointmentDateTime);

        if (Objects.isNull(updatedAppointment)) {
            throw new RuntimeException("\nAppointment not found\n");
        }
        appointment.setId(updatedAppointment.getId());
        return appointmentRepository.save(appointment);
    }
}
