// ==========================================
    // MÉTODO PARA EL EJERCICIO 5
    // ==========================================
    
    // Funcionalidad recursiva para este ejercicio
    public void recorridoAmplitudRecursivo() {
        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }
        
        // Se utiliza la altura del árbol (utilizando el método del padre LinkedBST)
        int h = height(this.root.data); 
        
        System.out.print("Recorrido por Amplitud: ");
        for (int i = 0; i <= h; i++) {
            imprimirNivel((NodeAVL) this.root, i);
        }
        System.out.println();
    }

    // Método auxiliar pedido por este ejercicio para imprimir nodos de un nivel específico
    private void imprimirNivel(NodeAVL nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        if (nivel == 0) {
            System.out.print(nodo.data + " ");
        } else if (nivel > 0) {
            imprimirNivel((NodeAVL) nodo.left, nivel - 1);
            imprimirNivel((NodeAVL) nodo.right, nivel - 1);
        }
    }
