
package main;

import Controller.Controladores;
import Models.Modelos;
import Views.Vistas;


public class Main {

    
    public static void main(String[] args) {
       
        Modelos modelbloc = new Modelos();
        Vistas viewbloc = new Vistas();
        Controladores ontrollerblocnotas = new Controladores(modelbloc, viewbloc); 
        
    }
    
}
