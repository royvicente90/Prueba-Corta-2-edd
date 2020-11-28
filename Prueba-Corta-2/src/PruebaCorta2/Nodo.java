/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebaCorta2;
    

public class Nodo {
    
    //El nodo para un arbol AVL, que forma parte de los arboles binarios balanceados, puede tener solo un padre y a lo sumo dos hijos
    
    Nodo padre = new Nodo();
    Nodo primer_hijo = new Nodo();
    Nodo segundo_hijo = new Nodo();
    Object valor;
    
    //Constructor del nodo vacio
    public void Nodo(){
        this.padre = null;
        this.primer_hijo = null;
        this.segundo_hijo = null;
        this.valor = null;
    }
    
    
    
   
}
