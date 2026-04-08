package org.progl.entities;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Consultorio {
    private int nroConsultorio;
    private String medico;
    private List<Turno> turnos;


    public Consultorio(int nroConsultorio, String medico){
      this.nroConsultorio = nroConsultorio;
      this.medico = medico;
      this.turnos = new ArrayList<Turno>();
    }


  public int getNroConsultorio() {
    return this.nroConsultorio;
  }

  public void setNroConsultorio(int nroConsultorio) {
    this.nroConsultorio = nroConsultorio;
  }

  public String getMedico() {
    return this.medico;
  }

  public void setMedico(String medico) {
    this.medico = medico;
  }

  public List<Turno> getTurnos() {
    return this.turnos;
  }

  public void setTurnos(List<Turno> turnos) {
    this.turnos = turnos;
  }

  

  public void agregarTurno(String medico, Turno turnos){
    this.turnos.add(turnos);
  }


  @Override
  public String toString() {
    return "{" +
      " nroConsultorio='" + getNroConsultorio() + "'" +
      ", medico='" + getMedico() + "'" +
      ", turnos='" + getTurnos() + "'" +
      "}";
  }





}
