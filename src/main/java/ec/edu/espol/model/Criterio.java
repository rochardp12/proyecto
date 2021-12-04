/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import static ec.edu.espol.model.Inscripcion.readFromFile;
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
public class Criterio {
    private int id;
    private String descripcion;
    private ArrayList<Evaluacion> evaluaciones;
    private int idConcurso;
    private Concurso concurso;
    //constructor
    
    public Criterio(String descripcion, String nombreConcurso){
        this.id = Util.nextID("criterios.txt");
        this.descripcion = descripcion;
        this.evaluaciones = new ArrayList<>();
        ArrayList<Concurso> concursos = Concurso.readFromFile("concursos.txt");
        for(Concurso conc: concursos){
            if(Objects.equals(conc.getNombre(), nombreConcurso)){
                this.idConcurso = conc.getId();
                this.concurso = conc;
            }
        }
    }
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }
    //getters

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public Concurso getConcurso() {
        return concurso;
    }
    //comportamientos
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Criterio: ").append(this.id).append(" --> ");
        sb.append("Descripcion: ").append(this.descripcion);
        sb.append("--> ID Concurso: ").append(this.idConcurso);
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
        Criterio crit = (Criterio)obj;
        return Objects.equals(this.descripcion,crit.descripcion);
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            pw.println(this.id + "|" + this.descripcion + "|" + this.evaluaciones + "|" + this.idConcurso + "|" + this.concurso.getNombre() );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void nextCriterio(Scanner sc){
        System.out.println("Ingrese cantidad de criterios:");
        int cantidad = sc.nextInt();
        while(cantidad<=0){
            System.out.println("Ingrese cantidad de criterios:");
            cantidad = sc.nextInt();
        }
        String[] descripciones = new String[cantidad];
        for(int i=0; i<cantidad; i++){
            System.out.println("Ingrese descripcion del criterio " + (i+1) + ":");
            String descripcion = sc.next();
            descripciones[i] = descripcion;
        }
        System.out.println("Ingrese nombre del concurso:");
        String concurso = sc.next();
        for(int u=0; u<cantidad; u++){
            Criterio criterio = new Criterio(descripciones[u], concurso);
            criterio.saveFile("criterios.txt");
        }
    }
    
    public static ArrayList<Criterio> readFromFile(String nomfile){
        ArrayList<Criterio> criterios = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                Criterio criterio = new Criterio(arreglo[1], arreglo[4]);
                criterios.add(criterio);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return criterios;
        }
    
    public static Criterio verificarID(int id){
        ArrayList<Criterio> criterios = readFromFile("criterios.txt");
        for(Criterio criterio: criterios){
            if(criterio.id == id)
                return criterio;
        }
        return null;
    }
    }
