package proyectofinal;

import java.util.ArrayList;

import javax.print.DocFlavor.CHAR_ARRAY;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class Programa {

	public static ArrayList<String> pila;
	public static ArrayList<String> prefija;
	public static ArrayList<String> numeros;
	public static char[] expresion;

	public static void main(String[] args) {
		pila = new ArrayList<String>();
		prefija = new ArrayList<String>();
		//String prueba = "5-(6*7)";
		//String prueba ="5-6*7";
		String prueba = "3+4*(3+7)";
		expresion = invierteCadena(prueba).toCharArray();
		int cont = 0;
		if (prueba.contains("(") && prueba.contains(")")) {
			do {
				if (esNumero(expresion[cont])) {
					prefija.add(String.valueOf(expresion[cont]));
					if (!pilaVacia() && sinParentesis(pila)) {
						prefija.add(pila.get(0));
						pila.clear();
					}
				} else {
					pila.add(String.valueOf(expresion[cont]));
				}
				if (completoParentesis(pila)) {
					for (int i = pila.size() - 1; i >= 0; i--) {
						if (pila.get(i).equals("(") || pila.get(i).equals(")")) {

						} else
							prefija.add(pila.get(i));

					}
					pila.clear();
				}
				if (!pilaVacia() && sinParentesis(pila)) {

				}
				cont++;

			} while (cont != expresion.length);
		} else {
			for (int i = 0; i < expresion.length; i++) {
				if (esNumero(expresion[i])) {
					prefija.add(String.valueOf(expresion[i]));
				} else
					pila.add(String.valueOf(expresion[i]));
			}
			for (int i = pila.size() - 1; i >= 0; i--) {
				prefija.add(pila.get(i));
			}
		}
		//System.out.println(invertirArreglo(prefija));
		// Resolver la ecuacion resultante
		ArrayList<String> resultado = invertirArreglo(prefija);
		System.out.println(resultado);
		
		if(!prueba.contains("(")) {
			buscaResultadoSinparentesis(resultado);
		}else {
			buscaResultado(resultado);
		}
	}

	private static void buscaResultadoSinparentesis(ArrayList<String> resultado) {
		pila.clear();
		numeros= new ArrayList<String>();
		
		for(int i =0;i<resultado.size();i++){
			if (Character.isDigit(resultado.get(i).charAt(0))) {
				numeros.add(resultado.get(i));
			}else {
				pila.add(resultado.get(i));
			}
		}
		//realizar operaciones
		int dig1,dig2,aux;
		//System.out.println(numeros);
		dig1=Integer.valueOf(numeros.get(0));
		numeros.remove(0);
		dig2=Integer.valueOf(numeros.get(0));
		numeros.remove(0);
		//System.out.println(numeros);
		for (int i =pila.size()-1 ; i >=0 ; i--) {
			aux=operar(dig1,dig2,pila.get(i));
			dig1=aux;
			if (numeros.size()!=0) {
				dig2=Integer.valueOf(numeros.get(0));
				numeros.remove(0);
			}
		}
		System.out.println("El resultado es : "+ dig1);
		
	}

	private static void buscaResultado(ArrayList<String> resultado) {
		
		pila.clear();
		numeros= new ArrayList<String>();
		
		for(int i = resultado.size()-1;i>=0;i--){
			if (Character.isDigit(resultado.get(i).charAt(0))) {
				numeros.add(resultado.get(i));
			}else {
				pila.add(resultado.get(i));
			}
		}
		//realizar operaciones
		int dig1,dig2,aux;
		//System.out.println(numeros);
		dig1=Integer.valueOf(numeros.get(0));
		numeros.remove(0);
		dig2=Integer.valueOf(numeros.get(0));
		numeros.remove(0);
		//System.out.println(numeros);
		for (int i =0; i <pila.size(); i++) {
			aux=operar(dig1,dig2,pila.get(i));
			dig2=aux;
			if (numeros.size()!=0) {
				dig1=Integer.valueOf(numeros.get(0));
				numeros.remove(0);
			}
		}
		System.out.println("El resultado es : "+ dig2);
	}

	private static int operar(int dig1, int dig2, String operador) {
		
		switch (operador) {
		case "+":
			return dig1+dig2;
		case "-":
			return dig1-dig2;
		case "*":
			return dig1*dig2;
		case "/":
			return dig1/dig2;

		default:
			break;
		}
		return 0;
		
	}

	private static boolean sinParentesis(ArrayList<String> pila2) {
		if (pila.contains("(") || pila.contains(")"))
			return false;
		else
			return true;
	}

	private static boolean completoParentesis(ArrayList<String> pila2) {
		if (pila.contains("(") && pila.contains(")")) {
			return true;
		}
		return false;
	}

	// invertimos la cadena y eliminamos los parentecis
	public static String invierteCadena(String cadena) {
		String cadenaInveritda = "";
		for (int x = cadena.length() - 1; x >= 0; x--)
			cadenaInveritda = cadenaInveritda + cadena.charAt(x);
		return cadenaInveritda;

	}

	public static ArrayList<String> invertirArreglo(ArrayList<String> arreglo) {
		ArrayList<String> invertido = new ArrayList<String>();
		for (int x = arreglo.size() - 1; x >= 0; x--)
			invertido.add(arreglo.get(x));
		return invertido;
	}

	public static boolean pilaVacia() {
		return pila.size() == 0;
	}

	// verificacion de un numero apartir de el codigo ascii
	public static boolean esNumero(char elemento) {

		if ((int) elemento == 48)
			return true;
		if ((int) elemento == 49)
			return true;
		if ((int) elemento == 50)
			return true;
		if ((int) elemento == 51)
			return true;
		if ((int) elemento == 52)
			return true;
		if ((int) elemento == 53)
			return true;
		if ((int) elemento == 54)
			return true;
		if ((int) elemento == 55)
			return true;
		if ((int) elemento == 56)
			return true;
		if ((int) elemento == 57)
			return true;

		return false;
	}
}
