package persistencia

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

object ConectionBD {
  
    private val urlBD = "jdbc:mysql://localhost:3306/mydb"
    private val userName = "root"
    private val password = "javi123"
    Class.forName("com.mysql.jdbc.Driver");
    private val conexion:Connection = DriverManager.getConnection(urlBD, userName, password);
    
    def cerrar(){
        conexion.close();
    }

    def insert(sql:String){
        val st:Statement = conexion.createStatement();
        st.executeUpdate(sql);
    }

    def recover(sql:String):ResultSet = {
        val st:Statement = conexion.createStatement();
        val resultado:ResultSet = st.executeQuery(sql);
        resultado;
    }
}