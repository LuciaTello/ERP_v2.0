/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cineslave.gui;

import com.cineslave.controlador.Conexion;
import java.awt.CardLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javax.swing.text.html.HTML.Tag.HEAD;

/**
 *
 * @author juanxxiii
 */
public class JFPrincipal extends javax.swing.JFrame {

    Conexion con;
    Connection conTotal;
    JPanelInicioSesion panelInicioSesion;
    JPanelCine panelCine;
    JPanelCliente panelCliente;
    JPanelProveedor panelProveedor;
    JPanelPedido panelPedidos;

    JPanelPelicula panelPelicula;
    JPanelSesion panelSesion;

    JPanelProducto panelProductos;


    public JFPrincipal() throws Exception {
        initComponents();
        con = new Conexion();
        
        try {
            conTotal = con.conectar();
            System.out.println("conectado");
        } catch (Exception ex) {
            Logger.getLogger(JFPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }        
        panelInicioSesion = new JPanelInicioSesion(this);
        panelCine = new JPanelCine(this, conTotal);
        panelCliente = new JPanelCliente(this, conTotal);
        panelProveedor = new JPanelProveedor(this, conTotal);
        panelPedidos = new JPanelPedido (this, conTotal);

        panelPelicula = new JPanelPelicula (this, conTotal);
        panelSesion = new JPanelSesion (this, conTotal);
        

        panelProductos = new JPanelProducto(this, conTotal);

        
        this.getContentPane().add(panelCine, "pCine");
        this.getContentPane().add(panelInicioSesion, "pInicio");
        this.getContentPane().add(panelCine, "pCine");
        this.getContentPane().add(panelCliente, "pCliente");
        this.getContentPane().add(panelProveedor, "pProveedor");
        //this.getContentPane().add(panelPedidos, "pPedidos");

        this.getContentPane().add(panelPelicula, "pPelicula");
        this.getContentPane().add(panelSesion, "pSesion");

        this.getContentPane().add(panelProductos, "pProductos");


        setBounds(100, 100, 800, 800);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jMenu1.setText("Cine");
        jMenu1.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenu1MenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Clientes");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu2MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Proveedores");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu3MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Empleados");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Productos");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu6.setText("Pedidos");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu6MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        jMenu7.setText("Reservas");
        jMenuBar1.add(jMenu7);

        jMenu8.setText("Películas");
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu8MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu8);

        jMenu9.setText("Sesiones");
        jMenu9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu9MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu9);

        jMenu10.setText("Entradas");
        jMenuBar1.add(jMenu10);

        jMenu11.setText("Usuarios");
        jMenuBar1.add(jMenu11);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        cambiaPanel("pCine");
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu1MenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenu1MenuKeyPressed
        cambiaPanel("pCine");
    }//GEN-LAST:event_jMenu1MenuKeyPressed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        cambiaPanel("pCine");
    }//GEN-LAST:event_jMenu1MousePressed

    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        cambiaPanel("pCliente");
    }//GEN-LAST:event_jMenu2MousePressed

    private void jMenu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MousePressed
        cambiaPanel("pProveedor");
    }//GEN-LAST:event_jMenu3MousePressed

    private void jMenu6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MousePressed
        cambiaPanel("pPedidos");
    }//GEN-LAST:event_jMenu6MousePressed


    private void jMenu8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MousePressed
        cambiaPanel("pPelicula");
    }//GEN-LAST:event_jMenu8MousePressed

    private void jMenu9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu9MousePressed
        cambiaPanel("pSesion");
    }//GEN-LAST:event_jMenu9MousePressed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        cambiaPanel("pProductos");
    }//GEN-LAST:event_jMenu5MouseClicked


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFPrincipal().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(JFPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    protected void cambiaPanel(String nombrePanel) {
        CardLayout c = (CardLayout) (getContentPane().getLayout());
        c.show(getContentPane(), nombrePanel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
