package com.fegc.modeloDAO;

import com.fegc.config.Conexion;
import com.fegc.modelo.Jerarquia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fausto
 */
public class JerarquiaDAO {

    private static final String SQL_SELECT_ALL_JERARQUIA = "select * from jerarquia";
    private static final String SQL_SELECT_ALL_JERARQUIA_BY_ID = "select * from jerarquia where idEmpleado=?";
    private static final String SQL_CONTAR_HIJOS_NIVEL3 = "select idEmpleado from jerarquia where idPadreEmpleado in (?)";
    private static final String SQL_CONTAR_HIJOS_NIVEL4 = "select idEmpleado from jerarquia where idPadreEmpleado in (select idEmpleado from jerarquia where idPadreEmpleado in (?))";
    private static final String SQL_CONTAR_HIJOS_NIVEL5 = "select idEmpleado from jerarquia where idPadreEmpleado in (select idEmpleado from jerarquia where idPadreEmpleado in (select idEmpleado from jerarquia where idPadreEmpleado in (?)))";

    public List<Jerarquia> contarHijosLideresNivel3(int idEmpleado) {
        List<Jerarquia> lista = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CONTAR_HIJOS_NIVEL3);
            preparedStatement.setInt(1, idEmpleado);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Jerarquia jerarquia = new Jerarquia();
                jerarquia.setIdEmpleado(resultSet.getInt(1));                
                lista.add(jerarquia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return lista;
    }
    
    public List<Jerarquia> contarHijosLideresNivel4(int idEmpleado) {
        List<Jerarquia> lista = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CONTAR_HIJOS_NIVEL4);
            preparedStatement.setInt(1, idEmpleado);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Jerarquia jerarquia = new Jerarquia();
                jerarquia.setIdEmpleado(resultSet.getInt(1));                
                lista.add(jerarquia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return lista;
    }
    
    public List<Jerarquia> contarHijosLideresNivel5(int idEmpleado) {
        List<Jerarquia> lista = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CONTAR_HIJOS_NIVEL5);
            preparedStatement.setInt(1, idEmpleado);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Jerarquia jerarquia = new Jerarquia();
                jerarquia.setIdEmpleado(resultSet.getInt(1));                
                lista.add(jerarquia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return lista;
    }

    public List<Jerarquia> listar() {
        List<Jerarquia> lista = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_JERARQUIA);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Jerarquia jerarquia = new Jerarquia();
                jerarquia.setIdEmpleado(resultSet.getInt(1));
                jerarquia.setIdPadreEmpleado(resultSet.getInt(2));
                lista.add(jerarquia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return lista;
    }

    public int listarID(int id) {
        int idPadre = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_JERARQUIA_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Jerarquia jerarquia = new Jerarquia();
                jerarquia.setIdEmpleado(resultSet.getInt(1));
                jerarquia.setIdPadreEmpleado(resultSet.getInt(2));
                idPadre = jerarquia.getIdPadreEmpleado();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return idPadre;
    }

}
