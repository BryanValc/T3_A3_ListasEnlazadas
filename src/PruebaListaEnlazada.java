import java.util.LinkedList;



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
		if (nodoInicio==null) {
			this.nodoInicio=this.nodoFin=nuevoNodo;
		}else {
			nuevoNodo.setNodoSiguiente(nodoInicio);
			this.nodoInicio = nuevoNodo;
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
		
		//LinkedList<Integer> le = new LinkedList<Integer>();

		ListaEnlazada miListaEnlazada = new ListaEnlazada();
		
		//miListaEnlazada.agregarElementoAlInicio(7);
		//miListaEnlazada.agregarElementoAlInicio(6);
		//miListaEnlazada.agregarElementoAlInicio(5);

		//miListaEnlazada.agregarElementoAlInicio(3);
		//miListaEnlazada.agregarElementoAlInicio(4);
		miListaEnlazada.agregarElementoAlInicio(3);
		miListaEnlazada.mostrarElementos();
		int num = miListaEnlazada.eliminarDatoEspecifico(3);
		miListaEnlazada.mostrarElementos();
		
		System.out.println(num==-1?"Lista Vacia":num==-99999?"No se encontro el dato":num+" se eliminó correctamente");
		num = miListaEnlazada.eliminarDatoEspecifico(3);
		System.out.println(num==-1?"Lista Vacia":num==-99999?"No se encontro el dato":num+" se eliminó correctamente");
	}

}