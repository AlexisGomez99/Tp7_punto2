package facade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import modelo.DBFacade;


public class DBFacadeImplementacion implements DBFacade{
	
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/epp_facade";
    private static final String USUARIO = "root";
    private static final String CLAVE="";
    private Connection conexion;
    
	@Override
	public void open() {
        
        try {
        	Class.forName(CONTROLADOR);
			conexion= DriverManager.getConnection(URL, USUARIO, CLAVE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
       
	}

	@Override
	public List<Map<String, String>> queryResultAsAsociation(String sql) {
		Map<String, String> maps= new HashMap<String,String>();
		List<Map<String, String>> listamaps= new ArrayList<Map<String, String>>();
		try {
		
			Statement sent;
			sent= conexion.createStatement();
			ResultSet resul= sent.executeQuery(sql);//"Select * from `listafacade` u"
			while(resul.next()) {
				maps.put(resul.getString("u.clave"), resul.getString("u.valor"));
			}
			listamaps.add(maps);
			resul.close();
			sent.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listamaps;
	}

	@Override
	public List<String[]> queryResultAsArray(String sql) {
		List<String[]> lista= new ArrayList<String[]>();
		try {
		
			Statement sent;
			sent= conexion.createStatement();
			ResultSet resul= sent.executeQuery(sql);//"Select * from `listafacade` u"
			int i=0;
			String[] strings = new String[3];
			while(resul.next()) {
				strings[i]=resul.getString("u.valor"); 
				i++;
			}
			lista.add(strings);
			resul.close();
			sent.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lista;
	}

	@Override
	public void close() {
		try {
			this.conexion.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void agregarValor(String valor, String clave) {
		
		try {
		
			Statement sent;
			sent= conexion.createStatement();
			sent.executeUpdate("INSERT INTO `listafacade`(`clave`, `valor`) VALUES ('"+clave+"','"+valor+"')");	
			sent.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
