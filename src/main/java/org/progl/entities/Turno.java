package org.progl.entities;

import java.sql.Time;
import java.util.Date;

public class Turno {
  private int id;
  private Date dia;
  private Time hora;
  private Paciente paciente;
  private Consultorio consultorio;



  public Turno(int id, Date dia, Time hora, Paciente paciente, Consultorio consultorio){
    this.id = id;
    this.dia = dia;
    this.hora = hora;
    this.paciente = paciente;
    this.consultorio = consultorio;
  }

  public Turno(){

  }

  public int getId(){
    return this.id;
  }

  public void setId(int id){
    this.id = id;
  }
  
  public Date getDia() {
    return this.dia;
  }

  public void setDia(Date dia) {
    this.dia = dia;
  }

  public Time getHora() {
    return this.hora;
  }

  public void setHora(Time hora) {
    this.hora = hora;
  }


  public Paciente getPaciente() {
    return this.paciente;
  }

  public void setPaciente(Paciente paciente) {
    this.paciente = paciente;
  }

  public Consultorio getConsultorio() {
    return this.consultorio;
  }

  public void setConsultorio(Consultorio consultorio) {
    this.consultorio = consultorio;
  }




}
