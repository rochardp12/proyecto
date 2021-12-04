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
public class Mascota {
    private int id;
    private String nombre;
    private String raza;
    private LocalDate fechaNacimiento;
    private String tipo;
    private int idDueno;
    private Dueno dueno;
    private ArrayList<Inscripcion> inscripciones;
    //constructor
    
    public Mascota(String nombre, String raza, LocalDate fechaNacimiento, String tipo, Dueno dueno){
        this.id = Util.nextID("mascotas.txt");
        this.nombre = nombre;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.idDueno = dueno.getId();
        this.dueno = dueno;
        this.inscripciones = new ArrayList<>();
    }
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
    //getters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }
    //comportamientos
    
        
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Mascota: ").append(this.id).append(" --> ");
        sb.append("Nombre: ").append(this.nombre);
        sb.append(". Raza: ").append(this.raza);
        sb.append(". Fecha de Nacimiento: ").append(this.fechaNacimiento);
        sb.append(". Tipo: ").append(this.tipo);
        sb.append(". Inscripciones: ").append(this.inscripciones);
        sb.append("--> ID Dueño: ").append(this.idDueno);
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
        Mascota masc = (Mascota)obj;
        if(!(Objects.equals(masc.dueno.getEmail(), this.dueno.getEmail())))
            return false;
        return ((Objects.equals(masc.getNombre(),this.getNombre()))&&(Objects.equals(masc.getRaza(), this.getRaza())));
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            pw.println(this.id + "|" + this.nombre + "|" + this.raza + "|" + this.fechaNacimiento + "|" + this.tipo + "|" + this.idDueno + "|" + this.dueno.getEmail());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void nextMascota(Scanner sc){
        System.out.println("-> Ingrese nombre:");
        String nombre = sc.next();
        System.out.println("-> Ingrese raza:");
        String raza = sc.next();
        System.out.println("-> Ingrese dia de nacimiento (dd) :");
        int dia = sc.nextInt();
        while((dia<=0)||(dia>31)){
            System.out.println("-> Ingrese dia de nacimiento (dd) :");
            dia = sc.nextInt();
        }
        System.out.println("-> Ingrese mes de nacimiento en numeros (mm) :");
        int mes = sc.nextInt();
        while((dia >= 30)&&((mes == 2)||(mes == 02))){
            System.out.println("-> Ingrese mes de nacimiento en numeros (mm) :");
            mes = sc.nextInt();
        }
        System.out.println("-> Ingrese año de nacimiento (yyyy) :");
        int an = sc.nextInt();
        while((an < 2000)||(an > 2021)){
            System.out.println("-> Ingrese año de nacimiento (yyyy) :");
            an = sc.nextInt();
        }
        LocalDate nacimiento = LocalDate.of(an,mes,dia);
        System.out.println("-> Ingrese tipo:");
        String tipo = sc.next();
        System.out.println("-> Ingrese e-mail del dueño:");
        String email = sc.next();
        while(Dueno.verificarEmail(email) == null){
            System.out.println("-> Ingrese e-mail del dueño:");
            email = sc.next();
        }
        Dueno dueno = Dueno.verificarEmail(email);
        Mascota masc = new Mascota(nombre, raza, nacimiento, tipo, dueno);
        masc.saveFile("mascotas.txt");
    }
    
    public static ArrayList<Mascota> readFromFile(String nomfile){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String email = null;
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                String[] fecha = arreglo[3].split("-");
                LocalDate nacimiento = LocalDate.of(Integer.parseInt(fecha[0]),Integer.parseInt(fecha[1]),Integer.parseInt(fecha[2]));
                Mascota mascota = new Mascota(arreglo[1], arreglo[2], nacimiento, arreglo[4], Dueno.verificarEmail(arreglo[6]));
                mascotas.add(mascota);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return mascotas;
        }
    
    public static Mascota verificarNombre(String nombreMascota){
        ArrayList<Mascota> mascotas = readFromFile("mascotas.txt");
        for(Mascota mascota: mascotas){
            if(Objects.equals(mascota.nombre, nombreMascota))
                return mascota;
        }
        return null;
    }
}
