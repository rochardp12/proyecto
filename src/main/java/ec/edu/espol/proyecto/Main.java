/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyecto;

import ec.edu.espol.util.MenuOpciones;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        sc.useLocale(Locale.US);
        
        System.out.println("+-----------------------+");
        System.out.println("| CONCURSOS DE MASCOTAS |");
        System.out.println("+-----------------------+");
        
        MenuOpciones.menuOpciones(sc);
        sc.close();
    }
    
}
