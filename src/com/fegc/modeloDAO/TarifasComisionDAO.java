package com.fegc.modeloDAO;

import com.fegc.config.Conexion;
import com.fegc.modelo.Jerarquia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fausto
 */
public class TarifasComisionDAO {
    
    private static final String SQL_SELECT_PORCENTAJE_BY_NIVEL_COMISION = "select porcentajeComision from tarifasComision where nivelComision=?";
    
    public double listarID(int id) {
        double porcentajeComision = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PORCENTAJE_BY_NIVEL_COMISION);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();            
            while (resultSet.next()) {                
                porcentajeComision = resultSet.getDouble(1);                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return porcentajeComision;
    }
}
