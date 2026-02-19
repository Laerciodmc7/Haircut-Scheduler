package com.example.haircut_scheduler_api.Infrastructure.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "haircut_scheduler")
@Entity
public class HaircutScheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String service;          // Tipo de serviço
    private String clientName;       // Nome do cliente
    @Column(unique = true)
    private String clientEmail;      // email do cliente

    private LocalDateTime appointmentDateTime; // Hora para qual foi agendado o serviço
    private LocalDateTime createdAt = LocalDateTime.now(); // Hora que o serviço foi agendado
}
