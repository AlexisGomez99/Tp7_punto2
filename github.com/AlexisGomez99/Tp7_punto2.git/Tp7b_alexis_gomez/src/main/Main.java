package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import facade.DBFacadeImplementacion;
import modelo.DBFacade;

public class Main {

	public static void main(String[] args) {	
		List<String[]> lista= new ArrayList<String[]>();
		List<Map<String, String>> listamaps= new ArrayList<Map<String, String>>();
		DBFacade  cliente= new DBFacadeImplementacion();
		cliente.open();
		cliente.agregarValor("1", "Uno");
		cliente.agregarValor("2", "Dos");
		cliente.agregarValor("3", "Tres");
		listamaps= cliente.queryResultAsAsociation("Select * from `listafacade` u");
		lista=cliente.queryResultAsArray("Select * from `listafacade` u");
		cliente.close();
		
		System.out.println(listamaps.toString());
		for(String[] l: lista) {
			for(int i=0; i<l.length;i++) {
				System.out.println(l[i]);
			}
		}
	}

}
