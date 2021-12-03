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
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Inscripcion {
    private int id;
    private int idMascota;
    private Mascota mascota;
    private int idConcurso;
    private Concurso concurso;
    private double valor;
    private LocalDate fechaInscripcion;
    private ArrayList<Evaluacion> evaluaciones;
    //constructor
    
    public Inscripcion(String nombreMascota, String nombreConcurso, double valor, LocalDate fechaInscripcion){
        this.id = Util.nextID("inscripciones.txt");
        ArrayList<Mascota> mascotas = Mascota.readFromFile("mascotas.txt");
        for(Mascota masc: mascotas){
            if(Objects.equals(masc.getNombre(),nombreMascota)){
                this.idMascota = masc.getId();
                this.mascota = masc;
            } 
        }
        ArrayList<Concurso> concursos = Concurso.readFromFile("concursos.txt");
        for(Concurso conc: concursos){
            if(Objects.equals(conc.getNombre(),nombreConcurso)){
                this.idConcurso = conc.getId();
                this.concurso = conc;
            }
        }
        this.valor = valor;
        this.fechaInscripcion = fechaInscripcion;
        this.evaluaciones = new ArrayList<>();
    }
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
    //getters

    public int getId() {
        return id;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    //comportamientos
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Inscripcion: ").append(this.id).append(" --> ");
        sb.append("ID Mascota: ").append(this.idMascota);
        sb.append(". ID Concurso: ").append(this.idConcurso);
        sb.append(". Valor: ").append(this.valor);
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
        Dueno dueno = (Dueno)obj;
        return Objects.equals(this.email,dueno.email);
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            pw.println(this.id + "|" + this.nombres + "|" + this.apellidos + "|" + this.direccion + "|" + this.telefono + "|" + this.email);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void nextDueno(Scanner sc){
        System.out.println("-> Ingrese nombres:");
        String nombres = sc.next();
        System.out.println("-> Ingrese apellidos:");
        String apellidos = sc.next();
        System.out.println("-> Ingrese direccion de vivienda:");
        String direccion = sc.next();
        System.out.println("-> Ingrese telefono de contacto:");
        String telefono = sc.next();
        System.out.println("-> Ingrese e-mail:");
        String email = sc.next();
        Dueno dueno = new Dueno(nombres, apellidos, direccion, telefono, email);
        dueno.saveFile("dueños.txt");
    }
    
    public static ArrayList<Dueno> readFromFile(String nomfile){
        ArrayList<Dueno> duenos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                Dueno dueno = new Dueno(arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                duenos.add(dueno);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return duenos;
        }
}
