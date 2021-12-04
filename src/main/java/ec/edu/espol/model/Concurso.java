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
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public LocalDate getFechaInscripcion() {
        return this.fechaInscripcion;
    }

    public LocalDate getFechaCierreInscripcion() {
        return this.fechaCierreInscripcion;
    }

    public String getTematica() {
        return this.tematica;
    }

    public double getCostoInscripcion() {
        return this.costoInscripcion;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return this.inscripciones;
    }

    public ArrayList<Premio> getPremios() {
        return this.premios;
    }

    public ArrayList<Criterio> getCriterios() {
        return this.criterios;
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
    
    public static void nextConcurso(Scanner sc){
        System.out.println("Ingrese nombre del concurso:");
        String nombreConcurso = sc.next();
        System.out.println("-> Ingrese dia de inicio del concurso (dd):");
        int dia = sc.nextInt();
        while((dia<=0)||(dia>31)){
            System.out.println("-> Ingrese dia de inicio del concurso (dd):");
            dia = sc.nextInt();
        }
        System.out.println("-> Ingrese mes de inicio del concurso en numeros (mm):");
        int mes = sc.nextInt();
        while((dia >= 30)&&((mes == 2)||(mes == 02))){
            System.out.println("-> Ingrese mes de inicio del concurso en numeros(mm) :");
            mes = sc.nextInt();
        }
        System.out.println("-> Ingrese año de inicio del concurso (yyyy) :");
        int an = sc.nextInt();
        while(an < 2021){
            System.out.println("-> Ingrese año de inicio del concurso (yyyy) :");
            an = sc.nextInt();
        }
        LocalDate fecha = LocalDate.of(an,mes,dia);
        System.out.println("-> Ingrese dia de inicio de inscripcion al concurso (dd):");
        int diaIns = sc.nextInt();
        while((diaIns<=0)||(diaIns>31)){
            System.out.println("-> Ingrese dia de inicio de inscripcion al concurso (dd):");
            diaIns = sc.nextInt();
        }
        System.out.println("-> Ingrese mes de inicio de inscripcion al concurso en numeros (mm):");
        int mesIns = sc.nextInt();
        while((diaIns >= 30)&&((mesIns == 2)||(mesIns == 02))){
            System.out.println("-> Ingrese mes de inicio de inscripcion al concurso en numeros(mm) :");
            mes = sc.nextInt();
        }
        System.out.println("-> Ingrese año de inicio de inscripcion al concurso (yyyy) :");
        int anIns = sc.nextInt();
        while(an < 2021){
            System.out.println("-> Ingrese año de inicio de inscripcion al concurso (yyyy) :");
            anIns = sc.nextInt();
        }
        LocalDate fechaIns = LocalDate.of(anIns,mesIns,diaIns);
        System.out.println("-> Ingrese dia de cierre de inscripcion al concurso (dd):");
        int diaCie = sc.nextInt();
        while((diaCie<=0)||(diaCie>31)){
            System.out.println("-> Ingrese dia de cierre de inscripcion al concurso (dd):");
            diaCie = sc.nextInt();
        }
        System.out.println("-> Ingrese mes de cierre de inscripcion al concurso en numeros (mm):");
        int mesCie = sc.nextInt();
        while((diaCie >= 30)&&((mesCie == 2)||(mesCie == 02))){
            System.out.println("-> Ingrese mes de cierre de inscripcion al concurso en numeros(mm) :");
            mesCie = sc.nextInt();
        }
        System.out.println("-> Ingrese año de cierre de inscripcion al concurso (yyyy) :");
        int anCie = sc.nextInt();
        while(anCie < 2021){
            System.out.println("-> Ingrese año de cierre de inscripcion al concurso (yyyy) :");
            anCie = sc.nextInt();
        }
        LocalDate fechaCie = LocalDate.of(anCie,mesCie,diaCie);
        System.out.println("Ingrese tematica del concurso:");
        String tematica = sc.next();
        System.out.println("Ingrese costo de inscripcion:");
        double costo = sc.nextDouble();
        while(costo<0){
            System.out.println("Ingrese costo de inscripcion:");
            costo = sc.nextDouble();
        }
        Concurso concurso = new Concurso(nombreConcurso, fecha, fechaIns, fechaCie, tematica, costo);
        concurso.saveFile("concursos.txt");
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
    
    public static Concurso verificarNombre(String nombreConcurso){
        ArrayList<Concurso> concursos = readFromFile("concursos.txt");
        for(Concurso concurso: concursos){
            if(Objects.equals(concurso.nombre,nombreConcurso))
                return concurso;
        }
        return null;
    }
}
