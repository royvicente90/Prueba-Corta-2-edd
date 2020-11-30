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
//        System.out.println("Agregando el nodo al final:" + nuevoNodo.getValor());
//        this.imprimir();
        if(this.primero == null){
            this.primero = nuevoNodo;
            this.ultimo = this.primero;
            System.out.println("Agregando el primer elemento");
        }
        else if(this.primero.getSiguiente() == null){
            this.primero.setSiguiente(nuevoNodo);
            this.ultimo = this.primero.getSiguiente();
            System.out.println("Agregando el segundo elemento");
        }
        else{
            this.ultimo.setSiguiente(nuevoNodo);
            this.ultimo = nuevoNodo;
            System.out.println("Agregando el >3ro elemento");
        }
        System.out.println("agregado al final:");
        this.imprimir();
    }
    
    public Nodo devuelveDelPrincipio(){
        Nodo nodoObjetivo = new Nodo(); 
        System.out.println("Sacando el primer elemento de esta lista:");
        this.imprimir();
        if(this.primero.getSiguiente() != null){
            System.out.println("Segundo elemento de la lista:" + this.primero.getSiguiente().getValor());
        }
        
        nodoObjetivo.setValor(this.primero.getValor());
        nodoObjetivo.setPadre(this.primero.getPadre());
        nodoObjetivo.setHijoDerecho(this.primero.getHijoDerecho());
        nodoObjetivo.setHijoIzquierdo(this.primero.getHijoIzquierdo());
        
        System.out.println("Despues de la asignacion primero vale:" + this.primero.getValor());
        if(this.primero.getSiguiente() != null){
            this.primero = this.primero.getSiguiente();      
        }
        else{
            this.primero = null;
        }
        System.out.println("Lista sin el primer elemento:");
        this.imprimir();
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
        while(navegador != null){
            lista += "[" + String.valueOf(navegador.getValor()) + "] ";
            navegador = navegador.getSiguiente();
        }
        System.out.println(lista);
    }
    
    public boolean esVacia(){
        return this.primero == null;
    }
    
    public void vaciar(){
        if(this.primero != null){
          this.primero.setSiguiente(null);
          this.primero = null;
        } 
        this.ultimo = null;      
    }
    
    
}
