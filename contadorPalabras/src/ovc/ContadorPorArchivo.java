/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovc;

/**
 *
 * @author 5e
 */
public class ContadorPorArchivo {
    
    public String nombre;
    public int pRepetidas;
    public int pNoRepetidas;
    
    public ContadorPorArchivo(String n,int pnr, int pr){
        this.nombre=n;
        this.pNoRepetidas=pnr;
        this.pRepetidas=pr;
    }
    
  
    @Override
    public String toString() { 
        return "Nombre: "+nombre+" Palabras repetidas: "+pRepetidas+" Palabras no repetidas: "+pNoRepetidas; 
    } 
}
