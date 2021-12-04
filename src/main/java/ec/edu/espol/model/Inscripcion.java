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
    
    public Inscripcion(Mascota mascota, Concurso concurso, double valor, LocalDate fechaInscripcion){
        this.id = Util.nextID("inscripciones.txt");
        this.idMascota = mascota.getId();
        this.mascota = mascota;
        this.idConcurso = concurso.getId();
        this.concurso = concurso;
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
        return this.id;
    }

    public int getIdMascota() {
        return this.idMascota;
    }

    public Mascota getMascota() {
        return this.mascota;
    }

    public int getIdConcurso() {
        return this.idConcurso;
    }

    public Concurso getConcurso() {
        return this.concurso;
    }

    public double getValor() {
        return this.valor;
    }

    public LocalDate getFechaInscripcion() {
        return this.fechaInscripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return this.evaluaciones;
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
        Inscripcion ins = (Inscripcion)obj;
        return ((this.idMascota == ins.idMascota)&&(this.idConcurso == ins.idMascota));
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            pw.println(this.id + "|" + this.idMascota + "|" + this.mascota.getNombre() + "|" + this.idConcurso + "|" + this.concurso.getNombre() + "|" + this.valor + "|" + this.fechaInscripcion + "|" + this.evaluaciones);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void nextInscripcion(Scanner sc){
        System.out.println("-> Ingrese nombre de la mascota:");
        String nombreMascota = sc.next();
        while(Mascota.verificarNombre(nombreMascota) == null){
            System.out.println("-> Ingrese nombre de la mascota:");
            nombreMascota = sc.next();
        }
        Mascota mascota = Mascota.verificarNombre(nombreMascota);
        System.out.println("-> Ingrese nombre del concurso:");
        String nombreConcurso = sc.next();
        while(Concurso.verificarNombre(nombreConcurso) == null){
            System.out.println("-> Ingrese nombre del concurso:");
            nombreConcurso = sc.next();
        }
        Concurso concurso = Concurso.verificarNombre(nombreConcurso);
        System.out.println("-> Ingrese valor a pagar:");
        double valor = sc.nextDouble();
        while(valor < 0){
            System.out.println("-> Ingrese valor a pagar:");
            valor = sc.nextDouble();   
        }
        System.out.println("-> Ingrese dia de inscripcion (dd):");
        int dia = sc.nextInt();
        while((dia<=0)||(dia>31)){
            System.out.println("-> Ingrese dia de inscripcion (dd):");
            dia = sc.nextInt();
        }
        System.out.println("-> Ingrese mes de inscripcion en numeros (mm):");
        int mes = sc.nextInt();
        while((dia >= 30)&&((mes == 2)||(mes == 02))){
            System.out.println("-> Ingrese mes de inscripcion en numeros(mm) :");
            mes = sc.nextInt();
        }
        System.out.println("-> Ingrese año de inscripcion (yyyy) :");
        int an = sc.nextInt();
        while(an < 2021){
            System.out.println("-> Ingrese año de inscripcion (yyyy) :");
            an = sc.nextInt();
        }
        LocalDate fechaInscripcion = LocalDate.of(an,mes,dia);
        Inscripcion inscripcion = new Inscripcion(mascota, concurso, valor, fechaInscripcion);
        inscripcion.saveFile("inscripciones.txt");
    }
    
    public static ArrayList<Inscripcion> readFromFile(String nomfile){
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                String[] fecha = arreglo[6].split("-");
                LocalDate fechaInscripcion = LocalDate.of(Integer.parseInt(fecha[0]),Integer.parseInt(fecha[1]),Integer.parseInt(fecha[2]));
                Inscripcion inscripcion = new Inscripcion(Mascota.verificarNombre(arreglo[2]), Concurso.verificarNombre(arreglo[4]), Double.parseDouble(arreglo[5]), fechaInscripcion);
                inscripciones.add(inscripcion);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return inscripciones;
    }
    
    public static Inscripcion verificarID(int id){
        ArrayList<Inscripcion> inscripciones = readFromFile("inscripciones.txt");
        for(Inscripcion inscripcion: inscripciones){
            if(inscripcion.id == id)
                return inscripcion;
        }
        return null;
    }
    

}
