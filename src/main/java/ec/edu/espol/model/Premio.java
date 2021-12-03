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
    
    public Premio(int lugar, String descripcion, String nombreConcurso){
        this.id = Util.nextID("premios.txt");
        this.lugar = lugar;
        this.descripcion = descripcion;
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
        return id;
    }

    public int getLugar() {
        return lugar;
    }

    public String getDescripcion() {
        return descripcion;
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
    
    public static void nextPremio(int lugar, String descripcion, String nombreConcurso){ //no usaremos el scanner porque las descripciones se pedian primero y el nombre del concurso se pedia una sola vez al final, por lo tanto no era posible pedir informacion por scanner en este metodo
        Premio premio = new Premio(lugar, descripcion, nombreConcurso);
        premio.saveFile("premios.txt");
    }
    
    public static ArrayList<Premio> readFromFile(String nomfile){
        ArrayList<Premio> premios = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                Premio premio = new Premio(Integer.parseInt(arreglo[1]),arreglo[2],arreglo[4]);
                premios.add(premio);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return premios;
        }
}
