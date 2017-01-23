/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cineslave.controlador;

import com.cineslave.modelo.Cliente;
import com.cineslave.modelo.Compra;
import com.cineslave.modelo.Entrada;
import com.cineslave.modelo.Pelicula;
import com.cineslave.modelo.Sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erikthegod
 */
public class Gestor_Compras {

    private Conexion con = new Conexion();
    private Connection conexion;
    private Compra compr;
    private int idCompra;

    public Gestor_Compras() throws Exception {
        this.conexion = con.conectar();
    }

    public void generarCompra(String nombrePelicula, String horaSesion, String nombreCLi, int numFila, int numColum) throws SQLException {
        PreparedStatement ps;
        String sql;
        sql = "INSERT INTO Entrada (idButaca,numFila,numColumna) VALUES (?,?,?)";
        ps = conexion.prepareStatement(sql);
        //ps.setInt(2, entr.getButaca().getIdButaca());
        ps.setInt(3, numFila);
        ps.setInt(4, numColum);
        ps.executeUpdate();
        sql = "INSERT INTO Reserva VALUES (?)";
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, (recuperarIdCompra() + 1));
        ps.executeUpdate();
        if (recuperarIdCliente(nombreCLi) == -1) {
            sql = "INSERT INTO  Res_Entr_Cli VALUES (?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, recuperarIdEntrada());
            ps.setInt(3, (recuperarIdCompra()));
            ps.executeUpdate();
        } else {
            sql = "INSERT INTO  Res_Entr_Cli VALUES (?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, recuperarIdEntrada());
            ps.setInt(2, recuperarIdCliente(nombreCLi));
            ps.setInt(3, (recuperarIdCompra()));
            ps.executeUpdate();
        }
        sql = "INSERT INTO Entra_Peli_Ses VALUES (?,?,?)";
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, recuperarIdEntrada());
        ps.setInt(2, recuperarIdPeli(nombrePelicula));
        ps.setInt(3, recuperarIdSesion(horaSesion));
        ps.executeUpdate();
    }

    public Compra consultarCompra(String nombreCli) throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        String sql = "Select peliculas.nombre , sesion.hora , entrada.numColumna , entrada.numFila from peliculas , entrada ,sesion ,Res_Entr_Cli, Entra_Peli_Ses , cliente where cliente.nombre = '" + nombreCli + "' and cliente.idCliente= Res_Entr_Cli.idCliente and Res_Entr_Cli.idEntrada = Entra_Peli_Ses.idEntrada and Entra_Peli_Ses.idPelicula = peliculas.idPelicula and Entra_Peli_Ses.idSesion = Sesion.idSesion";
        ps = conexion.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            //1 idProveedor//2 cif//3 nombre//4 telefono//5 poblacion//6 cp
            compr = new Compra(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
        return compr;
    }

    public int recuperarIdPeli(String _nombre) throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        int idPeli = 0;
        String sql = "SELECT idPelicula FROM PELICULAS WHERE NOMBRE = ?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, _nombre);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            idPeli = rs.getInt(1);
        }
        return idPeli;
    }

    public int recuperarIdSesion(String horaSesion) throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        int idSesion = 0;
        String sql = "SELECT idSesion FROM PELICULAS WHERE HORA = ?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, horaSesion);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            idSesion = rs.getInt(1);
        }
        return idSesion;
    }

    public int recuperarIdCompra() throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        int idCompra = 0;
        String sql = "SELECT max(idReserva) FROM reservas";
        ps = conexion.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            idCompra = rs.getInt(1);
        }
        return idCompra;
    }

    public int recuperarIdEntrada() throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        int idEntrada = 0;
        String sql = "SELECT max(idEntrada) FROM entrada";
        ps = conexion.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            idEntrada = rs.getInt(1);
        }
        return idEntrada;
    }

    public int recuperarIdCliente(String nombreCli) throws SQLException {

        PreparedStatement ps;
        ResultSet rs = null;
        int idCliente = 0;
        if (nombreCli.equals("")) {
            idCliente = -1;
        }
        String sql = "SELECT idCliente FROM Cliente WHERE nombre = ?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, nombreCli);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            idCliente = rs.getInt(1);
        }
        return idCliente;
    }

}
