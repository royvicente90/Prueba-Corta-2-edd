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
    
    public void rotacionIzquierda(Nodo nodoProblema){
        //Determinar si el nodo problema hijo esta por la izquierda o la derecha de su padre
        if(nodoProblema.getPadre().getHijoDerecho().getValor() == nodoProblema.getValor()){
            
            nodoProblema.getPadre().setHijoDerecho(nodoProblema.getHijoIzquierdo());
            nodoProblema.getHijoIzquierdo().setPadre(nodoProblema.getPadre());
            
            //Volvemos a agregar el nodo
            int valorProblema = nodoProblema.getValor();
            this.agregarHijo(valorProblema);
            
            //Rompemos todas las conexiones del nodo problema
            nodoProblema.setPadre(null);
            nodoProblema.setHijoDerecho(null);
            nodoProblema.setHijoDerecho(null);
            nodoProblema.setSiguiente(null);
            System.out.println("Rotacion izquierda completa!");
        }
        else{
            //Sabemos que es hijo izquierdo, entonces podemos enlazar el padre por la izquierda
            nodoProblema.getPadre().setHijoIzquierdo(nodoProblema.getHijoIzquierdo());
            nodoProblema.getHijoIzquierdo().setPadre(nodoProblema.getPadre());
            
            //Volvemos a agregar el nodo
            int valorProblema = nodoProblema.getValor();
            this.agregarHijo(valorProblema);
            
            //Rompemos todas las conexiones del nodo problema
            nodoProblema.setPadre(null);
            nodoProblema.setHijoDerecho(null);
            nodoProblema.setHijoDerecho(null);
            nodoProblema.setSiguiente(null);
            
            System.out.println("Rotacion izquierda completa!");
        }
    }
    
    public void rotacionDerecha(Nodo nodoProblema){
        //Determinar si el nodo problema hijo esta por la izquierda o la derecha de su padre
        System.out.println("Nodo poblema: " + nodoProblema.getValor() + " | nodo padre: "+ nodoProblema.getPadre().getValor() + " | hijo derecho: " + nodoProblema.getPadre().getHijoDerecho().getValor());
        if(nodoProblema.getPadre().getHijoDerecho().getValor() == nodoProblema.getValor()){
            System.out.println("Es hijo por la derecha!");
            nodoProblema.getPadre().setHijoDerecho(nodoProblema.getHijoDerecho());
            nodoProblema.getHijoDerecho().setPadre(nodoProblema.getPadre());
            
            //Volvemos a agregar el nodo
            this.agregarHijo(nodoProblema.getValor());
            
            //Rompemos todas las conexiones del nodo problema
            nodoProblema.setPadre(null);
            nodoProblema.setHijoDerecho(null);
            nodoProblema.setHijoDerecho(null);
            nodoProblema.setSiguiente(null);
            System.out.println("Rotacion derecha completa!");
        }
        else{
            System.out.println("Es hijo por la izquierda!");
            //Sabemos que es hijo izquierdo, entonces podemos enlazar el padre por la izquierda
            nodoProblema.getPadre().setHijoIzquierdo(nodoProblema.getHijoDerecho());
            nodoProblema.getHijoDerecho().setPadre(nodoProblema.getPadre());
            
            //Volvemos a agregar el nodo
            this.agregarHijo(nodoProblema.getValor());
            
            //Rompemos todas las conexiones del nodo problema
            nodoProblema.setPadre(null);
            nodoProblema.setHijoDerecho(null);
            nodoProblema.setHijoDerecho(null);
            nodoProblema.setSiguiente(null);
            
            System.out.println("Rotacion derecha completa!");
        }
    }
    
    public Nodo nodoPorValor(int valor){
        Lista porVisitar = new Lista();
        porVisitar.agregarAlFinal(this.raiz);
        boolean finalizado = false;
        Nodo navegador = new Nodo();
        Nodo nodoObjetivo = null;
        
        while(!finalizado){
            if(porVisitar.esVacia()){
                finalizado = true;
            }
            else{
                navegador = porVisitar.devuelveDelPrincipio();
                if (navegador.getValor() == valor){
                    nodoObjetivo = navegador;
                    finalizado = true;
                }
                else{
                    if(navegador.getHijoIzquierdo() != null){
                        porVisitar.agregarAlFinal(navegador.getHijoIzquierdo());
                    }
                    if(navegador.getHijoDerecho() != null){
                        porVisitar.agregarAlFinal(navegador.getHijoDerecho());
                    }
                }
            }
        }
        return nodoObjetivo;
    }
    
}
