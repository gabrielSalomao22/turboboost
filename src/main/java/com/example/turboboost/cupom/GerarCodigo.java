package com.example.turboboost.cupom;

import java.util.Random;

public class GerarCodigo {

	
	public static String gerarCodigo() {
		Random rand = new Random();
		int maximo = 10;
		
		String codigo = "";
		
		codigo += rand.nextInt(maximo);
		codigo += rand.nextInt(maximo);
		codigo += rand.nextInt(maximo);
		codigo += rand.nextInt(maximo);
		
		return codigo;
	}
}
