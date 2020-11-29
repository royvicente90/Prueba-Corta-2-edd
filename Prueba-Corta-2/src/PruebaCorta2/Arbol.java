/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebaCorta2;

/**
 *
 * @author andresespinoza
 */
public class Arbol {
    Nodo raiz = new Nodo();
    
    /*
     * Metodos para insertar, eliminar, buscar y reorganizar (balancear) el arbol AVL
     */
    
    public boolean balanceado(){
        return false;
    }
    
    public void agregarHijo(Nodo nuevoNodo){
        //Caso en el que el arbol es vacio
        if(this.raiz == null){
            this.raiz = nuevoNodo;
        }
        else{
            //Se va navegando para comenzar a 
            if(raiz.getValor() > nuevoNodo.getValor())
        }
    }
}
