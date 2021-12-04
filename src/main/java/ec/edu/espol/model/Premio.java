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
public class Premio {
    private int id;
    private int lugar;
    private String descripcion;
    private int idConcurso;
    private Concurso concurso;
    //constructor
    
    public Premio(int lugar, String descripcion, Concurso concurso){
        this.id = Util.nextID("premios.txt");
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.idConcurso = concurso.getId();
        this.concurso = concurso;
    }
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }
    
    //getters

    public int getId() {
        return this.id;
    }

    public int getLugar() {
        return this.lugar;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public int getIdConcurso() {
        return this.idConcurso;
    }

    public Concurso getConcurso() {
        return this.concurso;
    }
    //comportamientos
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Premio: ").append(this.id).append(" --> ");
        sb.append("Lugar: ").append(this.lugar);
        sb.append(". Descripcion: ").append(this.descripcion);
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
        Premio premio = (Premio)obj;
        return Objects.equals(this.descripcion,premio.descripcion);
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            pw.println(this.id + "|" + this.lugar + "|" + this.descripcion + "|" + this.idConcurso + "|" + this.concurso.getNombre());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void nextPremio(Scanner sc){
        System.out.println("Ingrese cantidad de premios:");
        int cantidad = sc.nextInt();
        while(cantidad<=0){
            System.out.println("Ingrese cantidad de premios:");
            cantidad = sc.nextInt();
        }
        String[] descripciones = new String[cantidad];
        for(int i=0; i<cantidad; i++){
            System.out.println("Ingrese descripcion del premio " + (i+1) + ":");
            String descripcion = sc.next();
            descripciones[i] = descripcion;
        }
        System.out.println("Ingrese nombre del concurso:");
        String nombreConcurso = sc.next();
        while(Concurso.verificarNombre(nombreConcurso) == null){
            System.out.println("Ingrese nombre del concurso:");
            nombreConcurso = sc.next(); 
        }
        Concurso concurso = Concurso.verificarNombre(nombreConcurso);
        for(int u=0; u<cantidad; u++){
            Premio premio = new Premio(u+1, descripciones[u], concurso);
            premio.saveFile("premios.txt");
        }
    }
    
    public static ArrayList<Premio> readFromFile(String nomfile){
        ArrayList<Premio> premios = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                Premio premio = new Premio(Integer.parseInt(arreglo[1]),arreglo[2],Concurso.verificarNombre(arreglo[4]));
                premios.add(premio);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return premios;
        }
}
