/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cineslave.controlador;

import com.cineslave.modelo.Cliente;
import com.cineslave.modelo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author juanxxiii
 */
public class Gestor_Empleado {

    Connection conexion;

    public Gestor_Empleado(Connection _con) throws Exception {
        this.conexion = _con;
    }

    public boolean altaEmpleado(Empleado _empleado) throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        boolean altaGenerada = true;
        if (comprobarDni(_empleado.getDni())) {
            String sql = "INSERT INTO EMPLEADO (dni,nombre,apellidos,telefono,fechaInicio,cargo)VALUES (?,?,?,?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, _empleado.getDni());
            ps.setString(2, _empleado.getNombre());
            ps.setString(3, _empleado.getApellidos());
            ps.setInt(4, _empleado.getTelefono());
            ps.setString(5, _empleado.getFechaInicio());
            ps.setString(6, _empleado.getCargo());
            ps.executeUpdate();
        } else{
            altaGenerada = false;
        }
        return altaGenerada;
    }

    private boolean comprobarDni(String _dni) throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        boolean dniRepetido = true;
        String sql = "SELECT DNI FROM EMPLEADO";
        ps = conexion.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            if(rs.getString(1).equals(_dni)){
                dniRepetido = false;
                System.out.println("DNI repetido");
            }
        }
        return dniRepetido;
    }

    public Empleado consultaEmpleado(String _dni) throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        Empleado nuevoEmpleado = null;
        String sql = "SELECT * FROM EMPLEADO WHERE DNI =" + _dni + "";
        ps = conexion.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            //dni,nombre,apellidos,telefono,fechaInicio,cargo
            nuevoEmpleado = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7));
        }
        return nuevoEmpleado;
    }

    public void borrarEmpleado(String _dni) throws SQLException {
        PreparedStatement ps;
        int modificaciones = 0;
        String sql = "DELETE FROM EMPLEADO WHERE DNI = ?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, _dni);
        modificaciones = ps.executeUpdate();
        System.out.println("Empleados borrados: " + modificaciones);
    }

    public int modificarEmpleado(Empleado _empleado) throws SQLException {
        PreparedStatement ps;
        int modificaciones = 0;
        String sql = "UPDATE EMPLEADO SET DNI=?"
                + " ,NOMBRE=?"
                + " ,APELLIDO=?"
                + " ,TELEFONO=?"
                + " ,FECHAINICIO=?"
                + " ,CARGO=?"
                + " WHERE DNI =?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, _empleado.getDni());
        ps.setString(2, _empleado.getNombre());
        ps.setString(3, _empleado.getApellidos());
        ps.setInt(4, _empleado.getTelefono());
        ps.setString(5, _empleado.getFechaInicio());
        ps.setString(6, _empleado.getCargo());
        ps.setString(7, _empleado.getDni());
        modificaciones = ps.executeUpdate();
        System.out.println("Empleados modificados= " + modificaciones);
        return modificaciones;
    }
}
