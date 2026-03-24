package lab02.ejercicio9;

public class TestEstacion {
	public static void  main(String[] args) {
		
		PowerStation<Smartphone> zonaCelulares = new PowerStation<Smartphone>();
		
		Smartphone s1= new Smartphone("iPphone 15",120.0);
		Smartphone s2= new Smartphone("Galaxy S24",100.0);
		
		zonaCelulares.conectar(s1);
		zonaCelulares.conectar(s2);
		
		System.out.println(zonaCelulares.buscarDispositivo(s2));
		
		PowerStation<Laptop> zonaLaptops = new PowerStation<Laptop>();
		
		Laptop p1 = new Laptop("Asus",150.0);
		Laptop p2 = new Laptop("Lenovo",180.0);
		Laptop p3 = new Laptop("Acer",120.0);
		Laptop p4 = new Laptop("HP",130.0);
		
		zonaLaptops.conectar(p1);
		zonaLaptops.conectar(p2);
		zonaLaptops.conectar(p4);
		
		System.out.println(zonaLaptops.buscarDispositivo(p2));
		zonaLaptops.mostrarReporte();
		
		System.out.println("Consumo Total: "+zonaCelulares.calcularConsumoTotal()+"W");	
	}
}
