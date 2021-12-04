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
public class MiembroJurado {
    private int id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String perfilProfesional;
    private ArrayList<Evaluacion> evaluaciones;
    //constructor
    
    public MiembroJurado(String nombres, String apellidos, String telefono, String email, String perfilProfesional){
        this.id = Util.nextID("miembroJurados.txt");
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.perfilProfesional = perfilProfesional;
        this.evaluaciones = new ArrayList<>();
    }
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPerfilProfesional(String perfilProfesional) {
        this.perfilProfesional = perfilProfesional;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
    //getters

    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getPerfilProfesional() {
        return perfilProfesional;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    //comportamientos
    
    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        MiembroJurado jurado = (MiembroJurado)obj;
        return Objects.equals(this.email,jurado.email);
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            pw.println(this.id + "|" + this.nombres + "|" + this.apellidos + "|" + this.telefono + "|" + this.email + "|" + this.perfilProfesional + "|" + this.evaluaciones);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void nextMiembroJurado(Scanner sc){
        System.out.println("-> Ingrese nombres:");
        String nombres = sc.next();
        System.out.println("-> Ingrese apellidos:");
        String apellidos = sc.next();
        System.out.println("-> Ingrese telefono de contacto:");
        String telefono = sc.next();
        System.out.println("-> Ingrese e-mail:");
        String email = sc.next();
        System.out.println("-> Ingrese breve descripcion del perfil profesional:");
        String perfilProfesional = sc.next();
        MiembroJurado jurado = new MiembroJurado(nombres, apellidos, telefono, email, perfilProfesional);
        jurado.saveFile("miembroJurados.txt");
    }
    
    public static ArrayList<MiembroJurado> readFromFile(String nomfile){
        ArrayList<MiembroJurado> jurados = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                MiembroJurado jurado = new MiembroJurado(arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                jurados.add(jurado);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return jurados;
        }
    
    public static MiembroJurado verificarEmail(String email){
        ArrayList<MiembroJurado> jurados = readFromFile("miembroJurado.txt");
        for(MiembroJurado jurado: jurados){
            if(Objects.equals(jurado.email,email))
                return jurado;
        }
        return null;
    }
}
