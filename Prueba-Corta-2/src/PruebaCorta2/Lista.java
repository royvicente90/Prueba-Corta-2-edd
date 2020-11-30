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
public class Lista {
    Nodo primero;
    Nodo ultimo;
    
    
    public Lista(){
        this.primero = null;
        this.ultimo = null;
    }
    
    public void agregarAlInicio(Nodo nuevoNodo){
        if(this.primero == null){
            this.primero = nuevoNodo;
        }
        else{
            nuevoNodo.setSiguiente(this.primero);
            this.primero = nuevoNodo;
        }
    }
    
    public void agregarAlFinal(Nodo nuevoNodo){
        this.imprimir();
        if(this.primero == null){
            this.primero = nuevoNodo;
        }
        else if(this.primero.getSiguiente() == null){
            this.primero.setSiguiente(nuevoNodo);
            this.ultimo = nuevoNodo;
        }
        else{
            this.ultimo.setSiguiente(nuevoNodo);
            this.ultimo = nuevoNodo;
        }
    }
    
    public Nodo devuelveDelPrincipio(){
        Nodo nodoObjetivo = this.primero;
        this.primero = this.primero.getSiguiente();
        return nodoObjetivo;
    }
    
    public Nodo devuelveDelFinal(){
        Nodo nodoObjetivo = this.ultimo;
        Nodo navegador = this.primero;
        while(navegador.getSiguiente() != this.ultimo){
            navegador = navegador.getSiguiente();
        }
        navegador.setSiguiente(null);
        this.ultimo = navegador;
        return nodoObjetivo;
    }

    public Nodo getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }
    
    public void imprimir(){
        Nodo navegador = this.primero;
        String lista = "";
        while(navegador != ultimo){
            lista += "[" + String.valueOf(navegador.getValor()) + "] ";
            System.out.println("Nodo ->" + navegador.getValor());
            navegador = navegador.getSiguiente();
        }
        System.out.println(lista);
    }
    
    public boolean esVacia(){
        return this.primero == null;
    }
    
    
}
