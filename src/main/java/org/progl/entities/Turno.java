package org.progl.entities;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class Turno {
  private int id;
  private Date dia;
  private Time hora;
  private int paciente;
  private int consultorio;



  public Turno(int id, Date dia, Time hora, int paciente, int consultorio){
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


  public int getPaciente() {
    return this.paciente;
  }

  public void setPaciente(int paciente) {
    this.paciente = paciente;
  }

  public int getConsultorio() {
    return this.consultorio;
  }

  public void setConsultorio(int consultorio) {
    this.consultorio = consultorio;
  }




}
