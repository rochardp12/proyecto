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
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Evaluacion {
    private int id;
    private int idInscripcion;
    private Inscripcion inscripcion;
    private int idMiembroJurado;
    private MiembroJurado miembroJurado;
    private double nota;
    private int idCriterio;
    private Criterio criterio;
    
    public Evaluacion(MiembroJurado jurado, Inscripcion inscripcion, Criterio criterio, double nota){
        this.id = Util.nextID("evaluaciones.txt");
        this.idInscripcion = inscripcion.getId();
        this.inscripcion = inscripcion;
        this.idMiembroJurado = jurado.getId();
        this.miembroJurado = jurado;
        this.nota = nota;
        this.idCriterio = criterio.getId();
        this.criterio = criterio;
        }
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public void setIdMiembroJurado(int idMiembroJurado) {
        this.idMiembroJurado = idMiembroJurado;
    }

    public void setMiembroJurado(MiembroJurado miembroJurado) {
        this.miembroJurado = miembroJurado;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }
    //getters

    public int getId() {
        return this.id;
    }

    public int getIdInscripcion() {
        return this.idInscripcion;
    }

    public Inscripcion getInscripcion() {
        return this.inscripcion;
    }

    public int getIdMiembroJurado() {
        return this.idMiembroJurado;
    }

    public MiembroJurado getMiembroJurado() {
        return this.miembroJurado;
    }

    public double getNota() {
        return this.nota;
    }

    public int getIdCriterio() {
        return this.idCriterio;
    }

    public Criterio getCriterio() {
        return this.criterio;
    }
    //comportamientos
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Evaluacion: ").append(this.id).append(" --> ");
        sb.append("ID Inscripcion: ").append(this.idInscripcion);
        sb.append(". ID Miembro del Jurado: ").append(this.idMiembroJurado);
        sb.append(". ID Criterio: ").append(this.idCriterio);
        sb.append("--> Nota: ").append(this.nota);
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
        Evaluacion evaluacion = (Evaluacion)obj;
        if(evaluacion.nota != this.nota)
            return false;
        return (((evaluacion.idInscripcion == this.idInscripcion)&&(evaluacion.idMiembroJurado == this.idMiembroJurado))&&(evaluacion.idCriterio == this.idCriterio));
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            pw.println(this.id + "|" + this.idInscripcion + "|" + this.idMiembroJurado + "|" + this.idCriterio + "|" + this.nota);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void nextEvaluacion(Scanner sc){
        System.out.println("-> Ingrese e-mail del jurado:");
        String email = sc.next();
        while(MiembroJurado.verificarEmail(email) == null){
            System.out.println("-> Ingrese correctamente el e-mail del jurado:");
            email = sc.next();    
        }
        MiembroJurado jurado = MiembroJurado.verificarEmail(email);
        System.out.println("-> Ingrese ID de inscripcion:");
        int idIns = sc.nextInt();
        while(Inscripcion.verificarID(idIns) == null){
            System.out.println("-> Ingrese correctamente el ID de inscripcion:");
            idIns = sc.nextInt(); 
        }
        Inscripcion inscripcion = Inscripcion.verificarID(idIns);
        System.out.println("-> Ingrese ID del criterio:");
        int idCri = sc.nextInt();
        while(Criterio.verificarID(idCri) == null){
            System.out.println("-> Ingrese correctamente el ID del criterio:");
            idCri = sc.nextInt();
        }
        Criterio criterio = Criterio.verificarID(idCri);
        System.out.println("-> Ingrese nota de evaluacion:");
        double nota = sc.nextDouble();
        while(nota<0){
            System.out.println("-> Ingrese nota de evaluacion:");
            nota = sc.nextDouble();
        }
        Evaluacion evaluacion = new Evaluacion(jurado, inscripcion, criterio, nota);
        evaluacion.saveFile("evaluaciones.txt");
    }
    
    public static ArrayList<Evaluacion> readFromFile(String nomfile){
        ArrayList<Evaluacion> evaluaciones = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                Evaluacion evaluacion = new Evaluacion(MiembroJurado.verificarID(Integer.parseInt(arreglo[2])),Inscripcion.verificarID(Integer.parseInt(arreglo[1])),Criterio.verificarID(Integer.parseInt(arreglo[3])),Double.parseDouble(arreglo[4]));
                evaluaciones.add(evaluacion);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return evaluaciones;
        }
}

