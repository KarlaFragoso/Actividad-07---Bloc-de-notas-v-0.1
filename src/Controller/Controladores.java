/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Models.Modelos;
import Views.Vistas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Controladores {
    
    Modelos modeloBloc; // Crea un objeto para modelos
    Vistas vistaBloc; // Crea un objeto para vistas
    
    ActionListener actionlistener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == vistaBloc.jm_leer) { 
                jb_archivo_action_performed();
            }
            else if (e.getSource() == vistaBloc.jm_guardar) { 
                jb_guardar_action_performed();
            }
        }
    };
    
    
    public Controladores(Modelos modelBloc, Vistas viewBloc) {
        this.modeloBloc = modelBloc;
        this.vistaBloc = viewBloc;
        
       this.vistaBloc.jm_leer.addActionListener(actionlistener);
        this.vistaBloc.jm_guardar.addActionListener(actionlistener);
        initComponents();
    }
    
    //Método para el boton archivo
     
    public void jb_archivo_action_performed() {
        this.readFile(modeloBloc.getPath()); 
    }
    
    // Método para el boton guardar
 
    public void jb_guardar_action_performed() {
        modeloBloc.setMessage(vistaBloc.jt_area.getText()); 
        this.writeFile(modeloBloc.getPath(), modeloBloc.getMessage()); 
    }
    
    

    public String readFile (String path) {
        try {
            String row; 
            try (FileReader archivo = new FileReader(path)) { 
                BufferedReader bufferedreader = new BufferedReader(archivo);
                while ((row = bufferedreader.readLine()) != null ) {
                    vistaBloc.jt_area.setText(row);
                }
            }
        }
        catch (FileNotFoundException err) { // Muestra errores 
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { // Muestra error de no poder ver el archivo inticado
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    };
    
   
    public void writeFile (String path, String message) {
        try {
            File archivo = new File(path); // abre el archivo y si no existe crea uno nuevo
            FileWriter filewriter = new FileWriter(archivo, false);
            
            try (PrintWriter printwriter = new PrintWriter(filewriter) ) {
                printwriter.println(message);
                printwriter.close();
            }
        }
        //Verifica si no hay errores
        catch (FileNotFoundException err) { 
            System.err.println("Archivo no encontrado: " + err.getMessage());//imprime si no se encontro el archivo
        }
        catch (IOException err) { 
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
    }
    
    
    public void initComponents() {
        vistaBloc.setVisible(true);
    }
    
}