/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cineslave.controlador;

import com.cineslave.modelo.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erikthegod
 */
public class Gestor_Compras {

    private Connection conexion;
    private Compra compr;

    public Gestor_Compras(Connection _con) throws Exception {
        this.conexion = _con;
    }

    public void generarCompra(String nombrePelicula, String horaSesion, String nombreCLi, int numFila, int numColum) throws SQLException {
        PreparedStatement ps;
        String sql;

        sql = "INSERT INTO Entrada (idEntrada) VALUES (?)";
        ps = conexion.prepareStatement(sql);
        int idEntrada = (recuperarIdEntrada() + 1);
        ps.setInt(1, idEntrada);
        ps.executeUpdate();
        sql = "INSERT INTO Reserva VALUES (?)";
        ps = conexion.prepareStatement(sql);
        int idCompra = (recuperarIdCompra() + 1);
        ps.setInt(1, idCompra);
        ps.executeUpdate();
        if (recuperarIdCliente(nombreCLi) == -1) {
            sql = "INSERT INTO  Res_Entr_Cli VALUES (?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, recuperarIdEntrada());
            ps.setInt(3, idCompra);
            ps.executeUpdate();
        } else {
            sql = "INSERT INTO  Res_Entr_Cli VALUES (?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, recuperarIdEntrada());
            ps.setInt(2, recuperarIdCliente(nombreCLi));
            ps.setInt(3, idCompra);
            ps.executeUpdate();
        }
        sql = "INSERT INTO Entra_Peli_Ses VALUES (?,?,?)";
        ps = conexion.prepareStatement(sql);
        int idPeli = recuperarIdPeli(nombrePelicula);
        int idSesion = recuperarIdSesion(horaSesion);
        ps.setInt(1, idEntrada);
        ps.setInt(2, idPeli);
        ps.setInt(3, idSesion);
        ps.executeUpdate();
        sql = "Update But_Ses_Sal set ocupado = 1 where idButaca=? and idSesion=? and idSala =?";
        ps = conexion.prepareStatement(sql);
        int idButaca= recuperarIdButaca(numFila, numColum);
        int idSala=recuperarIdSala(recuperarIdPeli(nombrePelicula), recuperarIdSesion(horaSesion));
        ps.setInt(1,idButaca);
        ps.setInt(2, idSesion);
        ps.setInt(3, idSala);
        ps.executeUpdate();
    }

    public Compra consultarCompra(String nombreCli) throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        String sql = "Select peliculas.nombre , sesion.hora , butaca.numFila,butaca.numColumna from peliculas , entrada ,sesion ,Res_Entr_Cli, Entra_Peli_Ses , But_Ses_Sal,Sal_Peli_Ses,butaca cliente where cliente.nombre = '" + nombreCli + "' and cliente.idCliente= Res_Entr_Cli.idCliente and Res_Entr_Cli.idEntrada = Entra_Peli_Ses.idEntrada and Entra_Peli_Ses.idPelicula = peliculas.idPelicula and Entra_Peli_Ses.idSesion = Sesion.idSesion and Sal_Peli_Ses.idSesion = Sesion.idSesion and Sal_Peli_Ses.idPelicula = peliculas.idPelicula and Sal_Peli_Ses.idSala = But_Ses_Sal.idSala and But_Ses_Sal.idSesion = Sesion.idSesion and But_Ses_Sal.idButaca= Butaca.idButaca";
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
        String sql = "SELECT idSesion FROM sesion WHERE HORA = ?";
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
        String sql = "SELECT max(idReserva) FROM reserva";
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

    public int recuperarIdSala(int idPelicula, int idSesion) throws SQLException {
        int idSala = 0;
        PreparedStatement ps;
        ResultSet rs = null;
        String sql = "SELECT idSala FROM Sal_Peli_Ses WHERE idPelicula = ? and idSesion = ?";
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, idPelicula);
        ps.setInt(2, idSesion);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            idSala = rs.getInt(1);
        }
        return idSala;
    }

    public int recuperarIdButaca(int numFila, int numColumna) throws SQLException {
        int idButaca = 0;
        PreparedStatement ps;
        ResultSet rs = null;
        String sql = "SELECT idButaca FROM Butaca WHERE numFila = ? and numColumna = ?";
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, numFila);
        ps.setInt(2, numColumna);
        rs = ps.executeQuery();
        while (rs.next() == true) {
            idButaca = rs.getInt(1);
        }
        return idButaca;
    }

}
