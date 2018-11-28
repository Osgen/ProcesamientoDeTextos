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
public class Letra {
    
    public char caracter;
    public int cantidad;
    
    public Letra(char caracter){
        this.caracter=caracter;
        this.cantidad=1;
    }
    
    public void aumentar(){
        cantidad++;
    }
    
    @Override
    public String toString(){
      return caracter+": "+cantidad;  
    }
}
