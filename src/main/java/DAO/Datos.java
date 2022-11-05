/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Equipo;
import MODEL.Maestro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author root
 */
public class Datos {

    private Map<String, String> SQLConexion;

    public Datos() {

        SQLConexion = new TreeMap<>();

        SQLConexion.put("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //SQLConexion.put("url", "jdbc:sqlserver://test.cztl1tw9syzy.us-east-1.rds.amazonaws.com:1433;databaseName=BD_SERVICIOS_KARAN ");
        //SQLConexion.put("url", "jdbc:sqlserver://localhost:1433;databaseName=torneo");
        SQLConexion.put("url", "jdbc:sqlserver://54.144.48.101:1433;databaseName=BD_KARAN_TEST_BACKUP");
        SQLConexion.put("usuario", "sa");
        //SQLConexion.put("password", "bzfQ16MqYYzFu5UTUCW6");
        //SQLConexion.put("password", "51F310e0f0!");
        SQLConexion.put("password", "d^@&sg1V&nKEt6rt");

        //QUERY
        SQLConexion.put("Login", "SELECT PK_ID_USUARIO FROM TB_USUARIO WHERE FC_NOMBRE='%s' AND FC_PASSWORD='%s'");
        SQLConexion.put("ActualizaUltimoLogin", "UPDATE TB_USUARIO SET FD_ULTIMO_LOGIN=GETDATE() where PK_ID_USUARIO='%s'  ");

        //EQUIPO
        SQLConexion.put("SeleccionaEquipos", "SELECT PK_ID_EQUIPO, FC_NOMBRE, FC_ID_FIFA,FC_GRUPO FROM TB_EQUIPO");

        //Maestro
        SQLConexion.put("Maestro", "SELECT * FROM MAESTRO order by pk_id_maestro");
        SQLConexion.put("Respuestas", "SELECT * FROM TB_PARTIDO WHERE FI_ID_USUARIO='%s' order by pk_id_respuesta ");

        //AUXILIARES
        SQLConexion.put("SELECCIONADATOSEQUIPO", "SELECT FC_NOMBRE, FC_ID_FIFA,FC_GRUPO FROM TB_EQUIPO WHERE PK_ID_EQUIPO='%s'");
        SQLConexion.put("buscarespuestadeusuario", "SELECT TOP(1) PK_ID_RESPUESTA FROM TB_PARTIDO WHERE FI_ID_USUARIO='%s'");
        SQLConexion.put("ListaUsuarios", "select PK_ID_USUARIO AS USUARIO from TB_USUARIO");
        SQLConexion.put("ObtenNombreUsuario", "SELECT FC_NOMBRE FROM TB_USUARIO WHERE   PK_ID_USUARIO='%s'");

        //InsertaResultados
        SQLConexion.put("InsertaResultado", "INSERT INTO TB_PARTIDO VALUES('%s','%s','%s','%s','%s','%s',GETDATE())");

    }

    //--------------------------------------------------------Login------------------------------------------------------------------------------
    public int Login(String Usuario, String Password) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        int salida = 0;

        try {
            Class.forName(SQLConexion.get("driver"));
            con = DriverManager.getConnection(SQLConexion.get("url"),
                    SQLConexion.get("usuario"), SQLConexion.get("password"));

            stmt = con.createStatement();
            String sql = String.format(SQLConexion.get("Login"), Usuario, Password);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                salida = rs.getInt("PK_ID_USUARIO");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
            salida = 0;
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
            salida = 0;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                salida = 0;
            }
            try {
                stmt.close();
            } catch (SQLException e) {
                salida = 0;
            }
            try {
                con.close();
            } catch (SQLException e) {
                salida = 0;
                System.out.println(e);
            }
        }

        return salida;
    }

    public int ActualizaUltimoLogin(int idUsuario) {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName(SQLConexion.get("driver"));
            con = DriverManager.getConnection(SQLConexion.get("url"),
                    SQLConexion.get("usuario"), SQLConexion.get("password"));
            stmt = con.createStatement();
            String sql = String.format(SQLConexion.get("ActualizaUltimoLogin"), idUsuario);
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
            return 0;
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
            return 0;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        //valor retornado
        return 1;
    }

    public ArrayList<Equipo> Equipos() {
        ArrayList<Equipo> salida = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(SQLConexion.get("driver"));
            con = DriverManager.getConnection(SQLConexion.get("url"),
                    SQLConexion.get("usuario"), SQLConexion.get("password"));

            stmt = con.createStatement();
            String sql = String.format(SQLConexion.get("SeleccionaEquipos"));
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int idEquipo = rs.getInt("PK_ID_EQUIPO");
                String Nombre = rs.getString("FC_NOMBRE");
                String imagen = rs.getString("FC_ID_FIFA");
                String grupo = rs.getString("FC_FRUPO");

                Equipo c = new Equipo(idEquipo, Nombre, imagen, grupo);
                salida.add(c);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        return salida;
    }

    public ArrayList<Maestro> Maestro() {
        ArrayList<Maestro> salida = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(SQLConexion.get("driver"));
            con = DriverManager.getConnection(SQLConexion.get("url"),
                    SQLConexion.get("usuario"), SQLConexion.get("password"));

            stmt = con.createStatement();
            String sql = String.format(SQLConexion.get("Maestro"));
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int idEquipo = rs.getInt("PK_ID_MAESTRO");
                int equipo1 = rs.getInt("ID_EQUIPO_1");
                int equipo2 = rs.getInt("ID_EQUIPO_2");
                int Gana1 = rs.getInt("GANA_E1");
                int Gana2 = rs.getInt("GANA_E2");
                int Empate = rs.getInt("EMPATE");

                Maestro c = new Maestro(idEquipo, equipo1, equipo2, Gana1, Gana2, Empate);
                salida.add(c);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        return salida;
    }

    public ArrayList<String> EquiposxID(int idE) {
        ArrayList<String> salida = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(SQLConexion.get("driver"));
            con = DriverManager.getConnection(SQLConexion.get("url"),
                    SQLConexion.get("usuario"), SQLConexion.get("password"));

            stmt = con.createStatement();
            String sql = String.format(SQLConexion.get("SELECCIONADATOSEQUIPO"), idE);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String Nombre = rs.getString("FC_NOMBRE");
                String imagen = rs.getString("FC_ID_FIFA");
                String grupo = rs.getString("FC_GRUPO");

                salida.add(Nombre);
                salida.add(imagen);
                salida.add(grupo);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        return salida;
    }

    public int BuscaRespuesta(int idUsuario) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        int salida = 0;

        try {
            Class.forName(SQLConexion.get("driver"));
            con = DriverManager.getConnection(SQLConexion.get("url"),
                    SQLConexion.get("usuario"), SQLConexion.get("password"));

            stmt = con.createStatement();
            String sql = String.format(SQLConexion.get("buscarespuestadeusuario"), idUsuario);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                salida = rs.getInt("PK_ID_RESPUESTA");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
            salida = 0;
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
            salida = 0;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                salida = 0;
            }
            try {
                stmt.close();
            } catch (SQLException e) {
                salida = 0;
            }
            try {
                con.close();
            } catch (SQLException e) {
                salida = 0;
                System.out.println(e);
            }
        }

        return salida;
    }

    //insertaResultados
    public int InsertaResultado(int idE1, int idE2, int Local, int visita, int empate, int usuario) {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName(SQLConexion.get("driver"));
            con = DriverManager.getConnection(SQLConexion.get("url"),
                    SQLConexion.get("usuario"), SQLConexion.get("password"));

            stmt = con.createStatement();
            String sql = String.format(SQLConexion.get("InsertaResultado"), idE1, idE2, Local, visita, empate, usuario);
            stmt.executeUpdate(sql);
            //System.out.println("Se inserto Usuario");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
            return 0;
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
            return 0;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        //valor retornado
        return 1;
    }

    //VER RESPUESTAS
    public ArrayList<Maestro> Respuestas(int idusuario) {
        ArrayList<Maestro> salida = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(SQLConexion.get("driver"));
            con = DriverManager.getConnection(SQLConexion.get("url"),
                    SQLConexion.get("usuario"), SQLConexion.get("password"));

            stmt = con.createStatement();
            String sql = String.format(SQLConexion.get("Respuestas"), idusuario);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int idEquipo = rs.getInt("PK_ID_RESPUESTA");
                int equipo1 = rs.getInt("FI_EQUIPO_1");
                //int USER = rs.getInt("FI_ID_USUARIO");
                int equipo2 = rs.getInt("FI_EQUIPO_2");
                int Gana1 = rs.getInt("FI_GANA_E1");
                int Gana2 = rs.getInt("FI_GANA_E2");
                int Empate = rs.getInt("FI_EMPATE");
                //Date fa = rs.getDate("FD_FECHA_ALTA");

                Maestro c = new Maestro(idEquipo, equipo1, equipo2, Gana1, Gana2, Empate);
                salida.add(c);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        return salida;
    }

    public ArrayList<Integer> usuarios() {
        ArrayList<Integer> salida = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(SQLConexion.get("driver"));
            con = DriverManager.getConnection(SQLConexion.get("url"),
                    SQLConexion.get("usuario"), SQLConexion.get("password"));

            stmt = con.createStatement();
            String sql = String.format(SQLConexion.get("ListaUsuarios"));
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int usuario = rs.getInt("USUARIO");

                salida.add(usuario);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        return salida;
    }

    public String NombreUsuario(int idUsuario) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String salida = "";

        try {
            Class.forName(SQLConexion.get("driver"));
            con = DriverManager.getConnection(SQLConexion.get("url"),
                    SQLConexion.get("usuario"), SQLConexion.get("password"));

            stmt = con.createStatement();
            String sql = String.format(SQLConexion.get("ObtenNombreUsuario"), idUsuario);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                salida = rs.getString("FC_NOMBRE");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver");
            salida = "";
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
            salida = "";
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                salida = "";
            }
            try {
                stmt.close();
            } catch (SQLException e) {
                salida = "";
            }
            try {
                con.close();
            } catch (SQLException e) {
                salida = "";
                System.out.println(e);
            }
        }

        return salida;
    }
}
