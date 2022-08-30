
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

        
public class Administrar {
    Conexion conectar = new Conexion();
    
    //Variables de tipo SQL
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public int agregar_a(Producto p){
        String query = "INSERT INTO producto(nombre,codigo,precio) VALUES(?,?,?)";
        try {
            //primero se realiza la conexion 
            conn = conectar.conex();
            //segundo: se prepara los espacios para guardar informacion 
            ps = conn.prepareStatement(query);
            //Capturada la informacion con SET
            ps.setString(1, p.getNom());
            ps.setString(2, p.getCod());
            ps.setString(3, p.getPre());
            ps.executeUpdate();
            //Metodo que me permite enviar toda la informacion real en mi base de datos
            
        } catch (Exception e) {
            System.out.println("Error al guardar en base de datos" + e.getMessage());
        }
        return 1;
    }
    
    public void delete(String id){
        String query = "DELETE FROM producto WHERE id= ?";
        try{
            conn = conectar.conex();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
            
        } catch (Exception e){
            System.out.println("Error al intentar eliminar el regustro"+e.getMessage());
        }
    }
    
    public List Listar(){
        List<Producto> dato = new ArrayList<>();
        
        String query = "SELECT * FROM producto";
        
        try {
            conn = conectar.conex();//Conexion 
            ps = conn.prepareStatement(query);//Ejecucion de query
            //Listamos la consulta y capturamos los datos
            rs = ps.executeQuery();//lo que se capture dentro de las tablas se va a guardar dentro de la instruccion rs
            
            while (rs.next()) {
                Producto p = new Producto();
                //modifico las variables de la clase producto con los datos que me da
                //cada fila de la tabla de MYSQL
                p.setId(rs.getString(1));
                p.setNom(rs.getString(2));
                p.setCod(rs.getString(3));
                p.setPre(rs.getString(4));
                
                //Agregarla a dato
                dato.add(p);
            }
            
            
            
        } catch (Exception e) {
            System.out.println("Error al listar la informacion de la base de datos" + e.getMessage());
        }
        
        return dato;
    }
    
    public int Actualizar(Producto p){
        String query = "UPDATE producto SET nombre=?,codigo=?,precio=? WHERE id=?";
        
        try {
            conn = conectar.conex();
            ps = conn.prepareStatement(query);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getCod());
            ps.setString(3, p.getPre());
            ps.setString(4, p.getId());
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Error al intentar modificar" + e.getMessage());
        }
        
        return 1;
    }
    
}
