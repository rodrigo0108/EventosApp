package com.rramos.eventosapp.models;

/**
 * Created by RODRIGO on 27/11/2017.
 */

public class Evento {

    private int id;
    private String prioridad;
    private String fecha;
    private String mensaje;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Evento(int id, String prioridad, String fecha, String mensaje) {
        this.id = id;
        this.prioridad = prioridad;
        this.fecha = fecha;
        this.mensaje = mensaje;
    }

    public Evento() {
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", prioridad='" + prioridad + '\'' +
                ", fecha='" + fecha + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
