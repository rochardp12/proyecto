/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.Dueno;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.model.Premio;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class MenuOpciones {
    
    private MenuOpciones(){}
    
    public static void menuOpciones(Scanner sc){
        int num = 0;
        while(num != 9){
            System.out.println("Menu de opciones:");
            System.out.println("");
            System.out.println("1. Dueño");
            System.out.println("2. Mascota");
            System.out.println("3. Concurso");
            System.out.println("4. Premio");
            System.out.println("5. Criterio");
            System.out.println("6. Inscripcion");
            System.out.println("7. MiembroJurado");
            System.out.println("8. Evaluacion");
            System.out.println("9. Salir");
            System.out.println("");
            System.out.println("Ingrese opcion:");
            num = sc.nextInt();
            switch(num){
                case 1: Dueno.nextDueno(sc);
                    break;
                case 2: Mascota.nextMascota(sc);
                    break;
                case 4: System.out.println("Ingrese cantidad de premios:");
                    int cantidad = sc.nextInt();
                    String[] descripciones = new String[cantidad];
                    for(int i=0; i<cantidad; i++){
                        System.out.println("Ingrese descripcion del premio " + (i+1) + ":");
                        String descripcion = sc.next();
                        descripciones[i] = descripcion;
                    }
                    System.out.println("Ingrese nombre del concurso:");
                    String concurso = sc.next();
                    for(int u=0; u<cantidad; u++){
                        Premio.nextPremio(u+1, descripciones[u], concurso); //debido a que el nombre del concurso no lo recibimos hasta el final, no es posible ir creando objeto por objeto mientras recibimos las descripciones
                    }
                    break;
                case 5: System.out.println("Ingrese cantidad de criterios:");
                    int cant = sc.nextInt();
                    String[] descs = new String[cant];
                    for(int i=0; i<cant; i++){
                        System.out.println("Ingrese descripcion del criterio " + (i+1) + ":");
                        String desc = sc.next();
                        descs[i] = desc;
                    }
                    System.out.println("Ingrese nombre del concurso:");
                    String conc = sc.next();
                    for(int u=0; u<cant; u++){
                        Criterio.nextCriterio(descs[u], conc); //debido a que el nombre del concurso no lo recibimos hasta el final, no es posible ir creando objeto por objeto mientras recibimos las descripciones
                    }
            }       
         
        }
    }
}
