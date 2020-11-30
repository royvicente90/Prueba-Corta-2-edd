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
    
    public Arbol(){
        this.raiz = null;
    }

    
    public Nodo getRaiz(){
        return raiz;
    }
    
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public boolean balanceado() {
        return false;
    }
    
    public void agregarHijo(int nuevoValor){
        Nodo nuevoNodo = new Nodo(nuevoValor);
        //Caso en el que el arbol es vacio
        Nodo navegador = this.raiz;
        boolean fondoDelArbol = false;
        
        if(this.raiz == null){
            this.raiz = nuevoNodo;
//            System.out.println("Nuevo nodo agregado a la raiz: " + nuevoNodo.getValor());
        }
        else{
            while(!fondoDelArbol){
                if(navegador.getValor() > nuevoNodo.getValor()){
                    if(navegador.getHijoIzquierdo() == null){
                        navegador.setHijoIzquierdo(nuevoNodo);
                        nuevoNodo.setPadre(navegador);
                        fondoDelArbol = true;
//                        System.out.println("Nuevo nodo agregado a la izquierda de " + navegador.getValor());
                    }
                    else{
                        navegador = navegador.getHijoIzquierdo();
                    }
                }
                else{
                    if(navegador.getHijoDerecho() == null){
                        navegador.setHijoDerecho(nuevoNodo);
                        nuevoNodo.setPadre(navegador);
//                        System.out.println("Nuevo nodo agregado a la derecha de " + navegador.getValor());
                        fondoDelArbol = true;
                    }
                    else{
                        navegador = navegador.getHijoDerecho();
                    }
                    
                }
            }
        }
    }
    
    public void imprimir(){
        //Imprimiendo a traves de breadth first
        
        Nodo navegador = this.raiz;
        boolean visitadoCompleto = false;
        Lista porVisitar = new Lista();
        int nivel = 0;
        
        if(navegador != null){
            System.out.println(navegador.getValor() + " - [Sin padre]");
            if(navegador.getHijoIzquierdo() != null){
                porVisitar.agregarAlFinal(navegador.getHijoIzquierdo());
            }
            if(navegador.getHijoDerecho() != null){
                porVisitar.agregarAlFinal(navegador.getHijoDerecho());
            }
        }
        while(!porVisitar.esVacia()){
            
            navegador = porVisitar.devuelveDelPrincipio();
            System.out.println(navegador.getValor() + " - [Hijo de "+ navegador.getPadre().getValor() +"]");
            
            if(navegador.getHijoIzquierdo() != null){
                porVisitar.agregarAlFinal(navegador.getHijoIzquierdo());
            }
            if(navegador.getHijoDerecho() != null){
                porVisitar.agregarAlFinal(navegador.getHijoDerecho());
            }
        } 
    }
    
    public int getProfundidad(Nodo nodoRaiz){
        System.out.println("--Iniciando get profundidad--");
        int profundidad = 1;
        Lista porVisitar = new Lista();
        porVisitar.agregarAlFinal(nodoRaiz);
        Nodo navegador;
        String visitaAnterior = "";
        
        while(!porVisitar.esVacia()){
            navegador = porVisitar.devuelveDelPrincipio();
            System.out.println("En el nodo:" + navegador.getValor());
            if(navegador.getHijoIzquierdo() != null){
                System.out.println("Agregado por la izquierda: " + navegador.getHijoIzquierdo().getValor());
                porVisitar.agregarAlFinal(navegador.getHijoIzquierdo());
                
                if(visitaAnterior.equals("izquierda")){
                    profundidad++;
                }
                visitaAnterior = "izquierda";
            }
            else if(navegador.getHijoDerecho() != null){
               
                System.out.println("Agregado por la derecha: " + navegador.getHijoDerecho().getValor());
                porVisitar.agregarAlFinal(navegador.getHijoDerecho());
                if(visitaAnterior.equals("derecha")){
                    profundidad++;
                }
                visitaAnterior = "derecha"; 
            }
            else{
                //caso donde se llego a un nodo hoja, y se agrega el nodo actualmente para explorar la derecha
                porVisitar.agregarAlFinal(navegador);
            }
        }
        return profundidad;
    }
    
}
