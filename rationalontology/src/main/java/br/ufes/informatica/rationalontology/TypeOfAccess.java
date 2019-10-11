package br.ufes.informatica.rationalontology;

public class TypeOfAccess {

	public static final short CRIADOR = 1;
	public static final short SOLICITANTE = 2;
	public static final short COLABORADOR =3;
	public static final short NEGADO = 4;
	public static final short CANCELADO = 5;

	public static String toString(short accessType) {
		
		switch (accessType) {
		case 1: return "CRIADOR";
		case 2: return "SOLICITANTE";
		case 3: return "COLABORADOR";
		case 4: return "NEGADO";
		case 5: return "CANCELADO";

		default:
			return "TIPO NÃO IDENTIFICADO";
		}
	}
	
	public static String toString(int accessType) {
		
		switch (accessType) {
		case 1: return "CRIADOR";
		case 2: return "SOLICITANTE";
		case 3: return "COLABORADOR";
		case 4: return "NEGADO";
		case 5: return "CANCELADO";

		default:
			return "TIPO NÃO IDENTIFICADO";
		}
	}
	
}
