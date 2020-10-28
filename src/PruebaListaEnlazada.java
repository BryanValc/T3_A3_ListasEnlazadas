import java.util.LinkedList;
import java.util.Scanner;

interface Validacion{
	Scanner input = new Scanner(System.in);
	
	public static int validacionNatural() {
		int ret = 0;
		boolean err = false;
		do {
			try {
				ret = input.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("entrada no valida, intente de nuevo:");
				input.nextLine();
				err=true;
			}
			if (ret>0) {
				err=false;
			}else {
				System.out.println("solo números naturales");
				err=true;
			}
		}while(err);
		return ret;
	}
}

class Nodo{
	//prvate Alumno dato
	private int dato;
	private Nodo nodoSiguiente;
	
	public Nodo() {
	}
	public Nodo(int dato) {
		this.dato = dato;
	}
	
	public int getDato() {
		return dato;
	}
	public void setDato(int dato) {
		this.dato = dato;
	}
	public Nodo getNodoSiguiente() {
		return nodoSiguiente;
	}
	public void setNodoSiguiente(Nodo nodoSiguiente) {
		this.nodoSiguiente = nodoSiguiente;
	}
	
	@Override
	public String toString() {
		return "Nodo [dato=" + dato + ", nodoSiguiente=" + nodoSiguiente + "]";
	}
}

class ListaEnlazada{
	Nodo nodoInicio;
	Nodo nodoFin;
	
	public ListaEnlazada(){
		nodoInicio = nodoFin = null;
	}	
	
	public boolean listaVacia() {
		return (nodoInicio == null)&&(nodoFin == null);
	}
	
	public void agregarElementoAlInicio(int dato){
		Nodo nuevoNodo=new Nodo(dato);
		if (this.listaVacia()) {
			this.nodoInicio=this.nodoFin=nuevoNodo;
		}else {
			nuevoNodo.setNodoSiguiente(nodoInicio);
			this.nodoInicio = nuevoNodo;
		}
	}
	public void agregarElementoAlFinal(int dato){
		Nodo nuevoNodo=new Nodo(dato);
		if (this.listaVacia()) {
			this.agregarElementoAlInicio(dato);
		}else {
			Nodo nodoActual;
			nodoActual = nodoInicio;
			while(nodoActual.getNodoSiguiente()!=null){
				nodoActual=nodoActual.getNodoSiguiente();
			}
			nodoActual.setNodoSiguiente(nuevoNodo);
		}
	}
	public void agregarElementoPosicionEspecifica(int dato, int posicion){
		Nodo nuevoNodo=new Nodo(dato);
		int cc=0;
		
		if (this.listaVacia()||(posicion==1)) {
			System.out.println(this.listaVacia()?"La lista estaba vacia, se puso como único elemento":"la posicion era 1, por tanto se puso en el inicio");
			this.agregarElementoAlInicio(dato);
		}else {
			Nodo nodoAnterior, nodoSiguiente;
			nodoAnterior = nodoInicio;
			nodoSiguiente = nodoInicio.getNodoSiguiente();
			try {
				while(cc++<(posicion-2)){
					nodoAnterior = nodoAnterior.getNodoSiguiente();
					nodoSiguiente = nodoSiguiente.getNodoSiguiente();
				}
				nuevoNodo.setNodoSiguiente(nodoSiguiente);
				nodoAnterior.setNodoSiguiente(nuevoNodo);
			}catch(NullPointerException e){
				System.out.println("el indice esta fuera de los limites, sera colocado al final");
				this.agregarElementoAlFinal(dato);
			}
			
			
			
		}
	}
	
	public int eliminarDatoInicio() {
		if (this.listaVacia()) {
			return -1;
		}else try{
			Nodo nodoActual=nodoInicio;
			int ret = nodoActual.getDato();
			nodoInicio=nodoActual.getNodoSiguiente();
			return ret;
		}catch (Exception e) {
			return -1;
		}
	}
	public int eliminarDatoFinal() {
		if (this.listaVacia()) {
			return -1;
		}else try{
			Nodo nodoAnterior, nodoSiguiente;
			nodoAnterior = nodoInicio;
			nodoSiguiente = nodoInicio.getNodoSiguiente();
			if (nodoSiguiente==null) {
				int ret = nodoInicio.getDato();
				nodoInicio=nodoFin=null;
				return ret;
			}else {
				while(nodoSiguiente.getNodoSiguiente()!=null) {
					nodoAnterior = nodoAnterior.getNodoSiguiente();
					nodoSiguiente = nodoSiguiente.getNodoSiguiente();
				}
				int ret = nodoSiguiente.getDato();
				nodoAnterior.setNodoSiguiente(null);
				return ret;
			}
		}catch (Exception e) {
			return -1;
		}
	}
	public int eliminarDatoEspecifico(int dato) {
		if (nodoInicio==null) {
			return -1;
		}else if(nodoInicio==nodoFin&&nodoInicio.getDato()==dato){
			System.out.println("encontrado en el primer NODO");
			int n = nodoInicio.getDato();
			nodoInicio=nodoFin=null;
			return n;
		}else {
			Nodo nodoAnterior, nodoSiguiente;
			nodoAnterior = nodoInicio;
			nodoSiguiente = nodoInicio.getNodoSiguiente();
			
			if (nodoAnterior!=null&&nodoAnterior.getDato()==dato) {
				int n = nodoAnterior.getDato();
				nodoInicio=nodoAnterior.getNodoSiguiente();
				return n;
				
			}else {
				while(nodoSiguiente!=null && nodoSiguiente.getDato()!=dato){
					nodoAnterior = nodoAnterior.getNodoSiguiente();
					nodoSiguiente = nodoSiguiente.getNodoSiguiente();
				}
				
				if (nodoSiguiente!=null && nodoSiguiente.getDato()==dato) {
					int n = nodoSiguiente.getDato();
					nodoSiguiente = nodoSiguiente.getNodoSiguiente();
					nodoAnterior.setNodoSiguiente(nodoSiguiente);
					
					return n;
				}else {
					return -99999;
				}
			}
		}
	}
	
	public void mostrarCantidadElementos() {
		Nodo nodoActual = nodoInicio;
		int cc=0;
		while(nodoActual!=null){
			cc+=1;
			nodoActual=nodoActual.getNodoSiguiente();
		}
		System.out.println("Cantidad de elementos: "+cc);
	}
	public void mostrarElementos() {
		Nodo nodoActual = nodoInicio;
		while(nodoActual!=null){
			System.out.print("["+nodoActual.getDato()+"]-->");
			nodoActual=nodoActual.getNodoSiguiente();
		}
		System.out.println();
	}
	
	
}

public class PruebaListaEnlazada {

	public static void main(String[] args) {

		ListaEnlazada miListaEnlazada = new ListaEnlazada();
		
		
		byte opc=0;
		int dato,num;
		
		boolean salir=false;
		boolean salir1=false;
		
		do {
			System.out.println("1)Crear Lista\n2)Verificar Lista vacia\n3)Insertar elemento\n4)Eliminar elemento\n5)Mostrar cantidad de elementos\n6)Salir");
			opc = (byte) Validacion.validacionNatural();
			switch (opc) {
			case 1:
				miListaEnlazada = new ListaEnlazada();
				System.out.println("Lista creada exitosamente");
				break;
			case 2:
				System.out.println(miListaEnlazada.listaVacia()?"La lista esta vacia":"La lista NO esta vacia");break;
			case 3:
				do {
					salir=false;
					System.out.println("1)Inicio\n2)Final\n3)En posicion especifica\n4)Salir");
					opc = (byte) Validacion.validacionNatural();
					
					switch (opc) {
					case 1:
						System.out.println("Elemento(entero):");
						dato = Validacion.validacionNatural();
						miListaEnlazada.agregarElementoAlInicio(dato);
						break;
					case 2:
						System.out.println("Elemento(entero):");
						dato = Validacion.validacionNatural();
						miListaEnlazada.agregarElementoAlFinal(dato);
						break;
					case 3:
						System.out.println("Elemento(entero):");
						dato = Validacion.validacionNatural();
						System.out.println("Posicion:");
						int posicion = Validacion.validacionNatural();
						miListaEnlazada.agregarElementoPosicionEspecifica(dato, posicion);
						break;
					case 4:
						salir=true;
						break;
					default:
						System.out.println("Opcion no valida");
						break;
					}
					
				} while (!salir);
				miListaEnlazada.mostrarElementos();
				break;
			case 4:
				do {
					salir=false;
					System.out.println("1)Inicio\n2)Final\n3)Elemento especifico\n4)Salir");
					opc = (byte) Validacion.validacionNatural();
					switch (opc) {
					case 1:
						num=miListaEnlazada.eliminarDatoInicio();
						System.out.println(num==-1?"Lista Vacia":num==-99999?"No se encontro el dato":num+" se eliminó correctamente");
						break;
					case 2:
						num=miListaEnlazada.eliminarDatoFinal();
						System.out.println(num==-1?"Lista Vacia":num==-99999?"No se encontro el dato":num+" se eliminó correctamente");
						break;
					case 3:
						System.out.println("Elemento a eliminar:");
						dato = Validacion.validacionNatural();
						num=miListaEnlazada.eliminarDatoEspecifico(dato);
						System.out.println(num==-1?"Lista Vacia":num==-99999?"No se encontro el dato":num+" se eliminó correctamente");
						break;
					case 4:
						salir=true;
						break;
					default:
						System.out.println("Opcion no valida");
						break;
					}
				} while (!salir);
				miListaEnlazada.mostrarElementos();
				break;
			case 5:
				miListaEnlazada.mostrarCantidadElementos();break;
			case 6:
				salir1=true;break;
			default:
				System.out.println("Opcion no valida");break;
			}
		} while (!salir1);
		System.out.println();
		System.out.println("Fin de ejecucion");
		
	}

}