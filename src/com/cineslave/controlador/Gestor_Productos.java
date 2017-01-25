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

    public int consultarPrecio(String _descripcion) throws SQLException{
        int precio = 0;        
        PreparedStatement ps;
        ResultSet rs = null;
        String sql = "SELECT precio FROM producto WHERE descripcion= '"+ _descripcion+"'";
        ps = conexion.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            precio = rs.getInt("precio");
        }
        return precio;   
    }
    
    public void borrarProducto(String _descripcion) throws SQLException {
        PreparedStatement ps;
        int modificaciones = 0;
        String sql = "DELETE FROM producto WHERE descripcion = ?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, _descripcion);
        modificaciones = ps.executeUpdate();
        System.out.println("Producto borrado: " + modificaciones);
    }

    /**
     *
     * @param _cif cif del proveedor para acceder a dicho proveedor
     * @param _proveedor objeto para devolver valores
     * @throws SQLException
     */
    public void modificarProducto(Producto _producto, String _descripcion) throws SQLException {
        PreparedStatement ps;
        int modificaciones = 0;
        String sql = "UPDATE producto SET descripcion=?, precio=? WHERE descripcion = ?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, _producto.getDescripcion());
        ps.setInt(2, _producto.getPrecio());
        ps.setString(3, _descripcion);
        modificaciones = ps.executeUpdate();
        System.out.println("Productos modificacdos: " + modificaciones);
    }
}
