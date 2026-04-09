package org.progl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.progl.daos.TurnoImpl;
import org.progl.entities.Consultorio;
import org.progl.entities.Paciente;
import org.progl.entities.Turno;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

       /*  Paciente paciente = new Paciente(1, "María", 34823333);

        Consultorio consultorio = new Consultorio(1, "Dr. Juan Pérez");

        Date fecha = new Date();
       

        Turno turno = new Turno(1, fecha, LocalTime.of(13, 30, 00), 1, 1);

        consultorio.agregarTurno("1", turno);*/

        
        TurnoImpl turnos = new TurnoImpl();
        turnos.getAll().forEach(System.out::println);

    }
}
