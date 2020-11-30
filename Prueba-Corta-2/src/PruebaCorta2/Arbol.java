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
                else if(navegador.getValor() < nuevoNodo.getValor()){
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
                else{
                    fondoDelArbol = true;
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
        int profundidadMayor = 0;
        Nodo navegador;
        Lista nodosHoja = new Lista();
        int contador;
        
        nodosHoja = this.getNodosHoja(nodoRaiz);
        
        //Ahora recorremos los padres de las hojas hasta encontrar la raiz 
        while(!nodosHoja.esVacia()){
            //Agarrar cada uno de los nodos hoja
            navegador = nodosHoja.devuelveDelPrincipio();
            contador = 0;
            if(navegador != null){
                while(navegador.getValor() != nodoRaiz.getValor()){
                    navegador = navegador.getPadre();
                    contador++;
                }
            }
            contador++;
            
            if(contador>profundidadMayor){
                profundidadMayor = contador;
            }
            
        }
        return profundidadMayor;
    }
    
    public Lista getNodosHoja(Nodo nodoRaiz){
        Lista nodosHoja = new Lista();
        Lista porVisitar = new Lista();
        int nodosHijos;
        Nodo navegador;
        //Agregar el primer elemento
        porVisitar.agregarAlFinal(nodoRaiz);
        
        while(!porVisitar.esVacia()){
            navegador = porVisitar.devuelveDelPrincipio();
            nodosHijos = 0;
            if(navegador.getHijoIzquierdo() != null){
                porVisitar.agregarAlFinal(navegador.getHijoIzquierdo());
                nodosHijos++;
            }
            if(navegador.getHijoDerecho() != null){
                porVisitar.agregarAlFinal(navegador.getHijoDerecho());
                nodosHijos++;
            }
            if(nodosHijos == 0){
                //Agregar una hoja a la lista de hojas
                nodosHoja.agregarAlFinal(navegador);
            }
            
        }
        return nodosHoja;
    }
    
    public int getBalance(Nodo nodoRaiz){
        int balanceDerecho = 0;
        int balanceIzquierdo = 0;
        if(nodoRaiz.getHijoIzquierdo() != null){
            Nodo nodoIzquierdo = nodoRaiz.getHijoIzquierdo();
            balanceIzquierdo = this.getProfundidad(nodoIzquierdo);
        }
        if(nodoRaiz.getHijoDerecho() != null){
           Nodo nodoDerecho = nodoRaiz.getHijoDerecho();
           balanceDerecho = this.getProfundidad(nodoDerecho); 
        }
        return balanceDerecho - balanceIzquierdo;
    }
    
    public void balancearArbol(){
        //
    }
    
}
