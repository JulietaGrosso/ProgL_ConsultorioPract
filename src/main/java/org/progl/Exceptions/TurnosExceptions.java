package org.progl.Exceptions;

public class TurnosExceptions extends RuntimeException{


    public static final int ERROR_CONSULTORIO_INEXISTENTE = 1;

    private static final int ERROR_PACIENTE_INEXISTENTE = 2;

    private static final int ERROR_TURNO_DUPLICADO = 3;

    private int id; 

    public TurnosExceptions(int id){
        this.id = id;
    }

    @Override
    public String getMessage(){
        String mensaje = " "; 
        switch(id){
            case ERROR_CONSULTORIO_INEXISTENTE: mensaje= "Consultorio inexistente";
            break;
            case ERROR_PACIENTE_INEXISTENTE: mensaje= "Paciente inexistente";
            break;
            case ERROR_TURNO_DUPLICADO: mensaje= "Turno duplicado";
        }
        return mensaje;
    }

    public TurnosExceptions(String message){
        super(message);
    } 

}
