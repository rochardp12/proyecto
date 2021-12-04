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
public class Dueno {
    private int id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private ArrayList<Mascota> mascotas;
    //constructor
    
    public Dueno(int id, String nombres, String apellidos, String direccion, String telefono, String email){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.mascotas = new ArrayList<>();
    }
    //setters

    public void setId(int id) {
        if(verificarID(id) == null)
            this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        if(verificarEmail(email) == null)
            this.email = email;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    //getters

    public int getId() {
        return this.id;
    }

    public String getNombres() {
        return this.nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public ArrayList<Mascota> getMascotas() {
        return this.mascotas;
    }
    //comportamientos 
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Dueño: ").append(this.id).append(" --> ");
        sb.append("Nombres: ").append(this.nombres);
        sb.append(". Apellidos: ").append(this.apellidos);
        sb.append(". Direccion: ").append(this.direccion);
        sb.append(". Telefono: ").append(this.telefono);
        sb.append(". Email: ").append(this.email);
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
        while(!(verificarEmail(email) == null)){
            System.out.println("-> Ingrese e-mail:");
            email = sc.next();
        }
        Dueno dueno = new Dueno(Util.nextID("dueños.txt"),nombres, apellidos, direccion, telefono, email);
        dueno.saveFile("dueños.txt");
    }
    
    public static ArrayList<Dueno> readFromFile(String nomfile){
        ArrayList<Dueno> duenos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                Dueno dueno = new Dueno(Integer.parseInt(arreglo[0]), arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                duenos.add(dueno);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return duenos;
        }
    
    public static Dueno verificarEmail(String email){
        ArrayList<Dueno> duenos = readFromFile("dueños.txt");
        for(Dueno dueno: duenos){
            if(Objects.equals(dueno.email,email))
                return dueno;
        }
        return null;
    }
    
    public static Dueno verificarID(int id){
        ArrayList<Dueno> duenos = readFromFile("dueños.txt");
        for(Dueno dueno: duenos){
            if(dueno.id == id)
                return dueno;
        }
        return null;
    }
}
