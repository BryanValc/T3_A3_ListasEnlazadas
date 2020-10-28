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
			System.out.println("la lista ya estaba vacia");
			return -1;
		}else {
			Nodo nodoActual=nodoInicio;
			int ret = nodoActual.getDato();
			nodoInicio=nodoActual.getNodoSiguiente();
			return ret;
		}
	}
	public int eliminarDatoFinal() {
		if (this.listaVacia()) {
			System.out.println("la lista ya estaba vacia");
			return -1;
		}else {
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
		
		/*miListaEnlazada.agregarElementoAlInicio(7);
		miListaEnlazada.agregarElementoAlInicio(6);
		miListaEnlazada.agregarElementoAlInicio(5);*/

		/*miListaEnlazada.agregarElementoAlInicio(3);
		miListaEnlazada.agregarElementoAlInicio(4);
		miListaEnlazada.agregarElementoAlInicio(3);
		miListaEnlazada.agregarElementoAlInicio(3);
		miListaEnlazada.agregarElementoAlInicio(4);
		miListaEnlazada.agregarElementoAlInicio(3);
		miListaEnlazada.mostrarElementos();
		int num = miListaEnlazada.eliminarDatoEspecifico(3);
		miListaEnlazada.mostrarElementos();
		
		System.out.println(num==-1?"Lista Vacia":num==-99999?"No se encontro el dato":num+" se eliminó correctamente");
		num = miListaEnlazada.eliminarDatoEspecifico(3);
		System.out.println(num==-1?"Lista Vacia":num==-99999?"No se encontro el dato":num+" se eliminó correctamente");
		miListaEnlazada.agregarElementoAlFinal(100);
		System.out.println("========================");
		miListaEnlazada.mostrarElementos();
		miListaEnlazada.agregarElementoPosicionEspecifica(33,4);
		miListaEnlazada.mostrarElementos();
		System.out.println(miListaEnlazada.eliminarDatoInicio());
		miListaEnlazada.mostrarElementos();*/
		
		
		miListaEnlazada.agregarElementoAlInicio(3);
		System.out.println(miListaEnlazada.eliminarDatoFinal());
		miListaEnlazada.mostrarElementos();
		
		/*if (miListaEnlazada.listaVacia()) {
			System.out.println("LISTA VACIA");
		}*/
	}

}