/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Concurso {
    private int id;
    private String nombre;
    private LocalDate fecha;
    private LocalDate fechaInscripcion;
    private LocalDate fechaCierreInscripcion;
    private String tematica;
    private double costoInscripcion;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Premio> premios;
    private ArrayList<Criterio> criterios;
    //constructor
    
    public Concurso(String nombre, LocalDate fecha, LocalDate fechaInscripcion, LocalDate fechaCierreInscripcion, String tematica, double costoInscripcion){
        this.id = Util.nextID("concursos.txt");
        this.nombre = nombre;
        this.fecha = fecha;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.tematica = tematica;
        if(costoInscripcion >= 0)
            this.costoInscripcion = costoInscripcion;
        this.inscripciones = new ArrayList<>();
        this.premios = new ArrayList<>();
        this.criterios = new ArrayList<>();
    }
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public void setFechaCierreInscripcion(LocalDate fechaCierreInscripcion) {
        this.fechaCierreInscripcion = fechaCierreInscripcion;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public void setCostoInscripcion(double costoInscripcion) {
        if(costoInscripcion >= 0)
            this.costoInscripcion = costoInscripcion;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public void setPremios(ArrayList<Premio> premios) {
        this.premios = premios;
    }

    public void setCriterios(ArrayList<Criterio> criterios) {
        this.criterios = criterios;
    }
    //getters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public LocalDate getFechaCierreInscripcion() {
        return fechaCierreInscripcion;
    }

    public String getTematica() {
        return tematica;
    }

    public double getCostoInscripcion() {
        return costoInscripcion;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public ArrayList<Premio> getPremios() {
        return premios;
    }

    public ArrayList<Criterio> getCriterios() {
        return criterios;
    }
    //comportamientos
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Concurso: ").append(this.id).append(" --> ");
        sb.append("Nombre: ").append(this.nombre);
        sb.append(". Fecha: ").append(this.fecha);
        sb.append(". Tematica: ").append(this.tematica);
        sb.append(". Costo de inscripcion: ").append(this.costoInscripcion);
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        Concurso concurso = (Concurso)obj;
        return Objects.equals(this.tematica,concurso.tematica);
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            pw.println(this.id + "|" + this.nombre + "|" + this.fecha + "|" + this.tematica + "|" + this.fechaInscripcion + "|" + this.fechaCierreInscripcion + "|" + this.costoInscripcion);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public static ArrayList<Concurso> readFromFile(String nomfile){
        ArrayList<Concurso> concursos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                String[] fech = arreglo[2].split("-");
                LocalDate fecha1 = LocalDate.of(Integer.parseInt(fech[0]), Integer.parseInt(fech[1]), Integer.parseInt(fech[2]));
                String[] fechIns = arreglo[4].split("-");
                LocalDate fecha2 = LocalDate.of(Integer.parseInt(fechIns[0]),Integer.parseInt(fechIns[1]),Integer.parseInt(fechIns[2]));
                String[] fechCie = arreglo[5].split("-");
                LocalDate fecha3 = LocalDate.of(Integer.parseInt(fechCie[0]),Integer.parseInt(fechCie[1]),Integer.parseInt(fechCie[2]));
                Concurso concurso = new Concurso(arreglo[1], fecha1, fecha2, fecha3, arreglo[3], Double.parseDouble(arreglo[6]));
                concursos.add(concurso);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return concursos;
        }
}
