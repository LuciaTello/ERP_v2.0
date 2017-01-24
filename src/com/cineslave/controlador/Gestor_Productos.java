/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cineslave.controlador;

import com.cineslave.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanxxiii
 * 
 * Alta producto X X
    Baja producto X X
    Modificación producto X X
    Consulta producto
 */
public class Gestor_Productos {
    Conexion con = new Conexion();
    Connection conexion;

    public Gestor_Productos(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void crearProducto(Producto _nuevoProducto) throws SQLException {
        PreparedStatement ps;
        String sql = "INSERT INTO producto (descripcion, precio) VALUES (?,?)";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, _nuevoProducto.getDescripcion());
        ps.setInt(2, _nuevoProducto.getPrecio());
        ps.executeUpdate();
        
    }
    
    public ArrayList consultarProductos() throws SQLException{
        ArrayList alproductos = new ArrayList();
        PreparedStatement ps;
        ResultSet rs = null;
        String sql = "SELECT descripcion FROM producto";
        ps = conexion.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            alproductos.add(rs.getString("descripcion"));
        }
        return alproductos;
    }
/*
    
    */
    public void borrarProducto(String _nombre) throws SQLException {
        PreparedStatement ps;
        int modificaciones = 0;
        String sql = "DELETE FROM PELICULAS WHERE NOMBRE = ?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, _nombre);
        modificaciones = ps.executeUpdate();
        System.out.println("Proveedores borrados: " + modificaciones);
    }

    /**
     *
     * @param _cif cif del proveedor para acceder a dicho proveedor
     * @param _proveedor objeto para devolver valores
     * @throws SQLException
     *//*
    public void modificarProducto(Producto _producto) throws SQLException {
        PreparedStatement ps;
        int modificaciones = 0;
        String sql = "UPDATE PELICULAS SET nombre=?, duracion=?, edad=? WHERE NOMBRE = ?";
        ps = conexion.prepareStatement(sql);
        ps.setString(4, _producto.getNombre());
        ps.setString(1, _producto.getNombre());
        ps.setInt(2, _producto.getDuracion());
        ps.setInt(3, _producto.getEdad());
        modificaciones = ps.executeUpdate();
        System.out.println("Proveedores modificacdos: " + modificaciones);
    }

    public Producto consultarProducto(String _nombre) throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        Producto _nuevoProveedor = null;
        String sql = "SELECT * FROM PELICULAS WHERE NOMBRE = ?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, _nombre);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            //1 id//2 cif//3 nombre//4 telefono//5 poblacion// 6 cp
            _nuevoProveedor = new Pelicula(rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
        return _nuevoProveedor;
    }
    
    */
}
