package examen;

import java.util.Scanner;

public class CovidOrt {
	private static Scanner leer = new Scanner(System.in);

	public static void main(String[] args) {
		
		int dni;
		int edad=0;
		int contPacientes=0;
		int contPositivos=0;
		int contFamiliares=0;
		int mayor=0;
		String fiebre;
		String tos;
		String garganta;
		boolean tiene;
		int totalflia=0;
	
			System.out.println("Por favor ingrese DNI o bien 0 para finalizar");
			dni= Integer.parseInt(leer.nextLine());
			
			while(!(dni==0)) {
				contPacientes++;
				edad=ingresoEdad("Por favor ingrese edad (mayor a cero)");
				fiebre=ingresoSintomas("Tiene fiebre?");
				tos=ingresoSintomas("Tiene tos?");
				garganta=tos=ingresoSintomas("Tiene dolor de garganta?");
				tiene=esPositivo(edad,fiebre,tos,garganta);
				contPositivos=(tiene)?contPositivos+1:contPositivos;
				if(tiene) {
					contFamiliares=ingresoFamiliares();
					mayor=esMayor(contFamiliares,mayor);
					totalflia+=contFamiliares;
				}	
				
				infoPaciente(dni,edad,tiene,contFamiliares);
				System.out.println("Por favor ingrese DNI o bien 0 para finalizar");
				dni= Integer.parseInt(leer.nextLine());
				
			}
			
			infoFinal(contPacientes,contPositivos,mayor,contFamiliares,totalflia);
			

	}
	

	private static void infoPaciente(int dni, int edad, boolean tiene, int contFamiliares) {
		String covid= (tiene)?" POSITIVO ":" NEGATIVO ";
		System.out.println("DNI: "+dni+"- Edad: "+edad+covid+ "Contactos: "+contFamiliares);
		
	}


	private static void infoFinal(int contPacientes, int contPositivos, int mayor, int contFamiliares,int totalflia) {
		double promedio = (contPacientes/contPositivos);
		int porcentaje=((100*contPositivos)/contPacientes);
		System.out.println("Total pacientes testeados: " + contPacientes);
		System.out.println("Porcentaje de resultados positivos: " + porcentaje);
		System.out.println("Promedio de contactos x positivos: " +promedio );
		System.out.println("El paciente con mas contactos: "+mayor);
		
	}


	private static int esMayor(int contFamiliares,int mayor) {
		int max = 0;
		max = (mayor < contFamiliares) ? contFamiliares : mayor;
		return max;
	}
	
	private static int ingresoFamiliares() {
		String nombre;
		String telefono;
		int cont=0;
		
		System.out.println("Por favor ingrese nombre del contacto");
		nombre= leer.nextLine();
		while(!(nombre.equals(""))){	
			cont++;
			System.out.println("Por favor ingrese telefono del contacto");
			telefono=leer.nextLine();	
			System.out.println("Por favor ingrese nombre del contacto");
			nombre= leer.nextLine();
		} 
		return cont;
	}

	private static boolean esPositivo(int edad, String fiebre, String tos, String garganta) {
		boolean positivo = (edad > 40) ? tieneMasCuarenta(fiebre, tos, garganta):esMenorCuarenta(fiebre, tos, garganta);
		return positivo;

	}

	private static boolean esMenorCuarenta(String fiebre, String tos, String garganta) {
		// TODO Auto-generated method stub
		return (fiebre.toUpperCase().equals("S")&&(tos.toUpperCase().equals("S")||garganta.toUpperCase().equals("S")));
	}
	private static boolean tieneMasCuarenta(String fiebre, String tos, String garganta) {
		// TODO Auto-generated method stub
		return (fiebre.toUpperCase().equals("S")||tos.toUpperCase().equals("S")||garganta.toUpperCase().equals("S"));
	}

	private static int ingresoEdad(String msj) {
		int age;
		do {
			System.out.println(msj);
			age = Integer.parseInt(leer.nextLine());
		} while (!(esEdadValida(age)));
		return age;
		
	}
	
	private static boolean esEdadValida(int age) {
		return age >0;
	}
	

	private static String ingresoSintomas(String msj) {

		System.out.println(msj);
		String ingreso = leer.nextLine();
		while (!(ingreso.toUpperCase().equals("S")|| ingreso.toUpperCase().equals("N"))) {
			System.out.println("ingreso invalido, S = presenta este sintoma  /// N =  no presenta este sintoma");
			ingreso = leer.nextLine();
		}
		return ingreso;
	
	}

	

}
