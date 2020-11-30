/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebaCorta2;

/**
 *
 * @author royvi
 */
public class Main {
    public static void main(String [] args) {
        System.out.println("--Inicio del programa--\n\n");
        Arbol nuevoArbol = new Arbol();
        nuevoArbol.agregarHijo(15);
        nuevoArbol.agregarHijo(10);
        nuevoArbol.agregarHijo(8);
        nuevoArbol.agregarHijo(20);
        nuevoArbol.agregarHijo(12);
        nuevoArbol.agregarHijo(5);
        nuevoArbol.agregarHijo(1);
        nuevoArbol.agregarHijo(25);
        nuevoArbol.agregarHijo(30);
//        nuevoArbol.imprimir();
        Nodo nodoRaiz = nuevoArbol.getRaiz();
//        int profundidad = nuevoArbol.getProfundidad(nodoRaiz);
//        System.out.println("Profundidad: " +profundidad);
        int balance = nuevoArbol.getBalance(nodoRaiz);
        
        
//        System.out.println("--Determinando el balance--");
//        System.out.println("Balance de la raiz: "+balance);

//        Lista nodosHoja = nuevoArbol.getNodosHoja(nodoRaiz);
//        nodosHoja.imprimir();
        nuevoArbol.imprimir();
        System.out.println("^--Arbol original--\n\n");
        Nodo nodoProblema = nuevoArbol.nodoPorValor(20);
        if(nodoProblema != null){
            nuevoArbol.rotacionDerecha(nodoProblema);
        }
        else{
            System.out.println("Nodo no econtrado");
        }
        nuevoArbol.imprimir();
        
        
    }
    
}
