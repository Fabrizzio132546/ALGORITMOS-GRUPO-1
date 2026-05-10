package Actividad4;

public class ItemDuplicated extends Exception {
    // constructor que recibe un mensaje personalizado
    public ItemDuplicated(String msg) {
        super(msg);
    }
    
    // constructor por defecto
    public ItemDuplicated() {
        super();
    }
}

