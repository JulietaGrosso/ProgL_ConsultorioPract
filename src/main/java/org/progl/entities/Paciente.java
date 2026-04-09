package org.progl.entities;

public class Paciente {
    private int nroPaciente;
    private String nombre;
    private int telefono;


  public Paciente(int nroPaciente, String nombre, int telefono){
    this.nroPaciente = nroPaciente;
    this.nombre = nombre;
    this.telefono = telefono;
  }

  public Paciente(){

  }

  public int getNroPaciente() {
    return this.nroPaciente;
  }

  public void setNroPaciente(int nroPaciente) {
    this.nroPaciente = nroPaciente;
  }


  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getTelefono() {
    return this.telefono;
  }

  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }
  


}
