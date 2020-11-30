/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebaCorta2;
    

public class Nodo {
    //El nodo para un arbol AVL, que forma parte de los arboles binarios balanceados, puede tener solo un padre y a lo sumo dos hijos
    
    Nodo padre;
    Nodo hijoIzquierdo;
    Nodo hijoDerecho;
    Nodo siguiente;
    int altura;
    int valor;
    boolean visitado;
    
    
    //Constructor del nodo vacio
    public Nodo(){
        this.padre = null;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.valor = 0;
        this.altura = 0;
        this.visitado = false;
        this.siguiente = null;
    }
    
    public Nodo(int nuevoValor){
        this.padre = null;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.valor = nuevoValor;
        this.altura = 0;
        this.siguiente = null;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public Nodo getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public boolean esHoja(){
        return this.hijoDerecho == null && this.hijoIzquierdo == null;
    }
    
    public boolean esRaiz(){
        return this.padre == null;
    } 
   
}