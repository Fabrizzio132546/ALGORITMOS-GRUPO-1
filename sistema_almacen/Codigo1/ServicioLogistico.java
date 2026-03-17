public class ServicioLogistico {
    private InventarioListaDoble lista;

    public ServicioLogistico() {
        this.lista = new InventarioListaDoble();
    }

    public void registrarEntrada(Producto p) {
        lista.agregarAlFinal(p);
        System.out.println("Ssistema, Mercancia registrada bajo SKU " + p.getSku());
    }

    public void generarReporteValorizacion() {
        if (lista.estaVacio()) {
            System.out.println("Inventario en cero");
            return;
        }
        
        Nodo aux = lista.getCabeza();
        double inversionTotal = 0;
        System.out.println("\n REPORTE DE VALORIZACION DE ALMACEN ");
        while (aux != null) {
            System.out.println(aux.producto);
            inversionTotal += (aux.producto.getPrecio() * aux.producto.getCantidad());
            aux = aux.siguiente;
        }
        System.out.println("valor del inventario S/" + inversionTotal);
    }
}
