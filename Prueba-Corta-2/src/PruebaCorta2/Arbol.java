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
        int profundidadMayor = 0;
        int contador = 0;
        Lista porVisitar = new Lista();
        porVisitar.agregarAlFinal(nodoRaiz);
        Nodo navegador;
        Lista nodosHoja = new Lista();
        
//        while(!porVisitar.esVacia()){
//            navegador = porVisitar.devuelveDelPrincipio();
//            System.out.println("En el nodo:" + navegador.getValor());
//            if(navegador.getHijoIzquierdo() != null){
//                if(!navegador.isIzquierdaVisitada()){
//                   System.out.println("Agregado por la izquierda: " + navegador.getHijoIzquierdo().getValor());
//                   porVisitar.agregarAlFinal(navegador.getHijoIzquierdo());
//                   navegador.setIzquierdaVisitada(true);
//                }
//                if(visitaAnterior.equals("izquierda")){
//                    profundidad++;
//                }
//                visitaAnterior = "izquierda";
//            }
//            else if(navegador.getHijoDerecho() != null && !navegador.isDerechaVisitada()){
//               
//                System.out.println("Agregado por la derecha: " + navegador.getHijoDerecho().getValor());
//                porVisitar.agregarAlFinal(navegador.getHijoDerecho());
//                navegador.setDerechaVisitada(true);
//                if(visitaAnterior.equals("derecha")){
//                    profundidad++;
//                }
//                visitaAnterior = "derecha"; 
//            }
//            else{
//                //caso donde se llego a un nodo hoja, y se agrega el nodo actualmente para explorar la derecha
//                porVisitar.agregarAlFinal(navegador.getPadre());
//            }
//        }
        int nodosHijos = 0;
        //Ciclo para encontrar todos los nodos hoja del sistema
        while(!porVisitar.esVacia()){
            navegador = porVisitar.devuelveDelPrincipio();
//            System.out.println(navegador.getValor() + " - [Hijo de "+ navegador.getPadre().getValor() +"]");
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
                nodosHoja.agregarAlFinal(navegador);
            }
        }
        System.out.println("Nodos hoja para explorar profundidad:");
        nodosHoja.imprimir();
        
        //Ahora recorremos los padres de las hojas hasta encontrar la raiz 
        while(!nodosHoja.esVacia()){
            //Agarrar cada uno de los nodos hoja
            navegador = nodosHoja.devuelveDelPrincipio();
            System.out.println("navegador asignado a: " + navegador.getValor());
            contador = 0;
            
            while(navegador != nodoRaiz){
                navegador = navegador.getPadre();
                System.out.println("    Ascendiendo a: " + navegador.getValor());
                contador++;
            }
            
            if(contador>profundidadMayor){
                profundidadMayor = contador;
            }
            
        }
        return profundidadMayor;
    }
    
}
