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
    
    public String imprimirEnString(){
        Nodo navegador = this.raiz;
        boolean visitadoCompleto = false;
        Lista porVisitar = new Lista();
        int nivel = 0;
        String arbol = "";
        
        if(navegador != null){
            arbol += Integer.toString((navegador.getValor()))+" - [Sin padre]\n";
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
            arbol += Integer.toString(navegador.getValor()) + " - [Hijo de " + Integer.toString(navegador.getPadre().getValor()) +"]\n";
            
            if(navegador.getHijoIzquierdo() != null){
                porVisitar.agregarAlFinal(navegador.getHijoIzquierdo());
            }
            if(navegador.getHijoDerecho() != null){
                porVisitar.agregarAlFinal(navegador.getHijoDerecho());
            }
        } 
        return arbol;
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
//        System.out.println("Haciendole una rotacion izquierda al nodo ->" + nodoProblema.getValor());
        if(nodoProblema.getValor() != this.raiz.getValor()){
            
            if(nodoProblema.getPadre().getHijoDerecho().getValor() == nodoProblema.getValor()){

                nodoProblema.getPadre().setHijoDerecho(nodoProblema.getHijoIzquierdo());
                nodoProblema.getHijoIzquierdo().setPadre(nodoProblema.getPadre());

                //Volvemos a agregar el nodo
                if(nodoProblema.getHijoDerecho() != null){
                    Lista listaParaAgregar = this.getNodosHoja(nodoProblema.getHijoDerecho());
                    Nodo nav;
                    while(!listaParaAgregar.esVacia()){
                        nav = listaParaAgregar.devuelveDelPrincipio();
                        while(nav.getValor() != nodoProblema.getValor()){
                            this.agregarHijo(nav.getValor());
                            nav = nav.getPadre();
                        }
                    }
                }

                int valorProblema = nodoProblema.getValor();
                this.agregarHijo(valorProblema);

                //Rompemos todas las conexiones del nodo problema
                nodoProblema.setPadre(null);
                nodoProblema.setHijoDerecho(null);
                nodoProblema.setHijoDerecho(null);
                nodoProblema.setSiguiente(null);
//                System.out.println("Rotacion izquierda completa!");
            }
            else{
                //Sabemos que es hijo izquierdo, entonces podemos enlazar el padre por la izquierda
                nodoProblema.getPadre().setHijoIzquierdo(nodoProblema.getHijoIzquierdo());
                nodoProblema.getHijoIzquierdo().setPadre(nodoProblema.getPadre());

                //Volvemos a agregar el nodo
                if(nodoProblema.getHijoDerecho() != null){
                    Lista listaParaAgregar = this.getNodosHoja(nodoProblema.getHijoDerecho());
//                    System.out.println("Lista para agregar:");
//                    listaParaAgregar.imprimir();
                    Nodo nav = new Nodo();
                    while(!listaParaAgregar.esVacia()){
                        nav = listaParaAgregar.devuelveDelPrincipio();
//                        System.out.println("nav->" + nav.getValor());
                        while(nav.getValor() != nodoProblema.getValor()){
                            this.agregarHijo(nav.getValor());
                            nav = nav.getPadre();
//                            System.out.println("nav->" + nav.getValor());
                        }
                    }
                }

                int valorProblema = nodoProblema.getValor();
                this.agregarHijo(valorProblema);

                //Rompemos todas las conexiones del nodo problema
                nodoProblema.setPadre(null);
                nodoProblema.setHijoDerecho(null);
                nodoProblema.setHijoDerecho(null);
                nodoProblema.setSiguiente(null);

//                System.out.println("Rotacion izquierda completa!");
            }
        }
    }
    
    public void rotacionDerecha(Nodo nodoProblema){
        //Determinar si el nodo problema hijo esta por la izquierda o la derecha de su padre
//        System.out.println("Haciendole una rotacion derecha al nodo ->" + nodoProblema.getValor());
        if(nodoProblema.getValor() != this.raiz.getValor()){
            if(nodoProblema.getPadre().getHijoDerecho().getValor() == nodoProblema.getValor()){
//                System.out.println("Es hijo por la derecha!");
                nodoProblema.getPadre().setHijoDerecho(nodoProblema.getHijoDerecho());
                nodoProblema.getHijoDerecho().setPadre(nodoProblema.getPadre());

                //Volvemos a agregar el nodo
                if(nodoProblema.getHijoIzquierdo() != null){
                    Lista listaParaAgregar = this.getNodosHoja(nodoProblema.getHijoIzquierdo());
                    Nodo nav;
                    while(!listaParaAgregar.esVacia()){
                        nav = listaParaAgregar.devuelveDelPrincipio();
                        while(nav.getValor() != nodoProblema.getValor()){
                            this.agregarHijo(nav.getValor());
                            nav = nav.getPadre();
                        }
                    }
                }

                int valorProblema = nodoProblema.getValor();
                this.agregarHijo(valorProblema);

                //Rompemos todas las conexiones del nodo problema
                nodoProblema.setPadre(null);
                nodoProblema.setHijoDerecho(null);
                nodoProblema.setHijoDerecho(null);
                nodoProblema.setSiguiente(null);
//                System.out.println("Rotacion derecha completa!");
            }
            else{
//                System.out.println("Es hijo por la izquierda!");
                //Sabemos que es hijo izquierdo, entonces podemos enlazar el padre por la izquierda
                nodoProblema.getPadre().setHijoIzquierdo(nodoProblema.getHijoDerecho());
                nodoProblema.getHijoDerecho().setPadre(nodoProblema.getPadre());

                //Volvemos a agregar el nodo
                if(nodoProblema.getHijoIzquierdo() != null){
                    Lista listaParaAgregar = this.getNodosHoja(nodoProblema.getHijoIzquierdo());
                    Nodo nav;
                    while(!listaParaAgregar.esVacia()){
                        nav = listaParaAgregar.devuelveDelPrincipio();
                        while(nav.getValor() != nodoProblema.getValor()){
                            this.agregarHijo(nav.getValor());
                            nav = nav.getPadre();
                        }
                    }
                }

                int valorProblema = nodoProblema.getValor();
                this.agregarHijo(valorProblema);

                //Rompemos todas las conexiones del nodo problema
                nodoProblema.setPadre(null);
                nodoProblema.setHijoDerecho(null);
                nodoProblema.setHijoDerecho(null);
                nodoProblema.setSiguiente(null);

//                System.out.println("Rotacion derecha completa!");
            }
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
    
    public void balancear(){
        Lista nodosHoja = this.getNodosHoja(this.raiz);
        Lista porVisitar = new Lista();
        Nodo navegador = new Nodo();
        
        while(!nodosHoja.esVacia()){
//            nodosHoja.imprimir();
            //Guardamos hoja actual para devolver el nodo si hay un desbalance
            Nodo hojaActual = nodosHoja.devuelveDelPrincipio();
            navegador = hojaActual;
            while(navegador.getValor() != this.raiz.getValor()){
                
                int balance = this.getBalance(navegador);
                if(balance > 1){
//                    System.out.println("Nodo" + navegador.getValor() + " esta desbalanceado hacia la derecha!");
                    //Aqui normalmente devolveriamos a navegador a ser el nodo raiz;
                    this.rotacionDerecha(navegador);
                    nodosHoja = this.getNodosHoja(this.raiz);
                    navegador = this.raiz;
                }
                else if(balance < -1){
//                    System.out.println("Nodo" + navegador.getValor() + " esta desbalanceado hacia la izquierda!");
                    //Aqui normalmente devolveriamos a navegador a ser el nodo raiz;
                    this.rotacionIzquierda(navegador);
                    nodosHoja = this.getNodosHoja(this.raiz);
                    navegador = this.raiz;
                }
                else{
                    navegador = navegador.getPadre();
                }
                
            }
        }
        
        int balanceRaiz = this.getBalance(this.raiz);
        if(balanceRaiz > 1){
            //Balance de raiz por la derecha
            Nodo nodoAuxiliar = this.raiz;
            this.raiz.getHijoDerecho();
            Lista hojasRaiz = this.getNodosHoja(nodoAuxiliar.getHijoIzquierdo());
            while(!hojasRaiz.esVacia()){
                Nodo nav = hojasRaiz.devuelveDelPrincipio();
                while(nav.getValor() != nodoAuxiliar.getValor()){
                    this.agregarHijo(nav.getValor());
                    nav = nav.getPadre();
                }
            }
            this.balancear();
        }
        else if(balanceRaiz < -1){
            //Balance de raiz por la izquierda
            Nodo nodoAuxiliar = this.raiz;
            this.raiz = this.raiz.getHijoIzquierdo();
            Lista hojasRaiz = this.getNodosHoja(nodoAuxiliar.getHijoDerecho());
            while(!hojasRaiz.esVacia()){
               Nodo nav = hojasRaiz.devuelveDelPrincipio();
                while(nav.getValor() != nodoAuxiliar.getValor()){
                    this.agregarHijo(nav.getValor());
                    nav = nav.getPadre();
                } 
            }
            this.balancear();
        }
        else{
            System.out.println("--Balanceo finalizado exitosamente-- ");
        }
    }
    
    public void eliminarNodo(int valor){
        Nodo nodoAEliminar = this.nodoPorValor(valor);
        if(nodoAEliminar == null){
            System.out.println("El valor introducido no pertenece al nodo, agregelo si desea eliminarlo");
        }
        else{
            //Queremos conocer de que lado el nodo a eliminar es hijo del padre
            if(nodoAEliminar.getPadre().getHijoIzquierdo().getValor() == nodoAEliminar.getValor()){
                //El nodo a eliminar es el hijo izquierdo de su padre
                if(nodoAEliminar.getHijoIzquierdo() != null){
                    nodoAEliminar.getPadre().setHijoIzquierdo(nodoAEliminar.getHijoIzquierdo());
                    nodoAEliminar.getHijoIzquierdo().setPadre(nodoAEliminar.getPadre());
                    if(nodoAEliminar.getHijoDerecho() != null){
                        Lista nodosHoja = this.getNodosHoja(nodoAEliminar.getHijoDerecho());
                        while(!nodosHoja.esVacia()){
                            Nodo nav = nodosHoja.devuelveDelPrincipio();
                            while(nav.getValor() != nodoAEliminar.getValor()){
                                this.agregarHijo(nav.getValor());
                                nav = nav.getPadre();
                            }
                        }
                    }
                }
                else if(nodoAEliminar.getHijoDerecho() != null){
                    nodoAEliminar.getPadre().setHijoIzquierdo(nodoAEliminar.getHijoDerecho());
                    nodoAEliminar.setHijoDerecho(nodoAEliminar.getPadre());
                    if(nodoAEliminar.getHijoIzquierdo() != null){
                        Lista nodosHoja = this.getNodosHoja(nodoAEliminar.getHijoIzquierdo());
                        while(!nodosHoja.esVacia()){
                            Nodo nav = nodosHoja.devuelveDelPrincipio();
                            while(nav.getValor() != nodoAEliminar.getValor()){
                                this.agregarHijo(nav.getValor());
                                nav = nav.getPadre();
                            }
                        }
                    }
                    
                }
            }
            else{
                //El nodo a eliminar es el hijo derecho de su padre
                if(nodoAEliminar.getHijoIzquierdo() != null){
                    nodoAEliminar.getPadre().setHijoDerecho(nodoAEliminar.getHijoIzquierdo());
                    nodoAEliminar.getHijoIzquierdo().setPadre(nodoAEliminar.getPadre());
                    if(nodoAEliminar.getHijoDerecho() != null){
                        Lista nodosHoja = this.getNodosHoja(nodoAEliminar.getHijoDerecho());
                        while(!nodosHoja.esVacia()){
                            Nodo nav = nodosHoja.devuelveDelPrincipio();
                            while(nav.getValor() != nodoAEliminar.getValor()){
                                this.agregarHijo(nav.getValor());
                                nav = nav.getPadre();
                            }
                        }
                    }
                }
                else if(nodoAEliminar.getHijoDerecho() != null){
                    nodoAEliminar.getPadre().setHijoIzquierdo(nodoAEliminar.getHijoDerecho());
                    nodoAEliminar.setHijoDerecho(nodoAEliminar.getPadre());
                    if(nodoAEliminar.getHijoIzquierdo() != null){
                        Lista nodosHoja = this.getNodosHoja(nodoAEliminar.getHijoIzquierdo());
                        while(!nodosHoja.esVacia()){
                            Nodo nav = nodosHoja.devuelveDelPrincipio();
                            while(nav.getValor() != nodoAEliminar.getValor()){
                                this.agregarHijo(nav.getValor());
                                nav = nav.getPadre();
                            }
                        }
                    }
                    
                }
            }
            nodoAEliminar.setPadre(null);
            nodoAEliminar.setHijoIzquierdo(null);
            nodoAEliminar.setHijoDerecho(null);
        }
        System.out.println("Eliminacion del nodo "+nodoAEliminar.getValor() + " finalizada");
    }
    
}
