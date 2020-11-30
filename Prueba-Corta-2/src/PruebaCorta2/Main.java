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
        nuevoArbol.imprimir();
        Nodo nodoRaiz = nuevoArbol.getRaiz();
        int profundidad = nuevoArbol.getProfundidad(nodoRaiz);
        System.out.println("Profundidad: " +profundidad);
        
    }
    
}
