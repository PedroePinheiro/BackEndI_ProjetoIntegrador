package com.example.consultorio.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private Paciente paciente;
    private Dentista dentista;
    private LocalDateTime horarioConsulta;

    public Consulta(Paciente paciente, Dentista dentista, LocalDateTime horarioConsulta) {
        this.paciente = paciente;
        this.dentista = dentista;
        this.horarioConsulta = horarioConsulta;
    }

    public Consulta(int id, Paciente paciente, Dentista dentista, LocalDateTime horarioConsulta) {
        this.id = id;
        this.paciente = paciente;
        this.dentista = dentista;
        this.horarioConsulta = horarioConsulta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public LocalDateTime getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(LocalDateTime horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", dentista=" + dentista +
                ", horarioConsulta=" + horarioConsulta +
                '}';
    }
}
