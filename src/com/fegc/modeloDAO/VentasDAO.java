package com.fegc.modeloDAO;

import com.fegc.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fausto
 */
public class VentasDAO {

    private static final String SQL_SELECT_MONTO_VENTAS_BY_ID = "select monto from ventas where idVenta=?";
    private static final String SQL_SUMA_MONTOS_PRIMERA_QUINCENA = "SELECT SUM(monto) FROM ventas WHERE EXTRACT(MONTH FROM ventas.fechaVenta)=? AND EXTRACT(YEAR FROM ventas.fechaVenta)=? AND EXTRACT(day FROM ventas.fechaVenta)<\"15\" AND idEmpleadoVentas=?";
    private static final String SQL_SUMA_VENTAS_MENSUALES_EQUIPO = "SELECT SUM(monto) FROM pagos.ventas WHERE EXTRACT(MONTH FROM ventas.fechaVenta)=? AND EXTRACT(YEAR FROM ventas.fechaVenta)=? AND idEmpleadoVentas=?";
    private static final String SQL_SUMA_VENTAS_TRIMESTRALES_EQUIPO = "SELECT SUM(monto) FROM ventas WHERE fechaVenta BETWEEN CAST(? AS DATE) AND CAST(? AS DATE) AND idEmpleadoVentas=?";
    private static final String SQL_SUMA_VENTAS_MENSUALES_EMPLEADOS = "SELECT SUM(monto) FROM ventas WHERE EXTRACT(MONTH FROM ventas.fechaVenta)=? AND EXTRACT(YEAR FROM ventas.fechaVenta)=? AND idEmpleadoVentas=?";
    
    public double calcularMontoLiderazgoTrimestral(String mesInicial, String mesFinal, List<Integer> idEmpleados) {
        double monto = 0.0;        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            for (int i = 0; i < idEmpleados.size(); i++) {                
                preparedStatement = connection.prepareStatement(SQL_SUMA_VENTAS_TRIMESTRALES_EQUIPO);
                preparedStatement.setString(1, mesInicial);
                preparedStatement.setString(2, mesFinal);
                preparedStatement.setInt(3, idEmpleados.get(i));
                resultSet = preparedStatement.executeQuery();  
                if(resultSet.next()){
                    monto += resultSet.getDouble(1);
                }
            }            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return monto;
    }
    
    public double calcularMontoLiderazgo(String month, String year, List<Integer> idEmpleados) {
        double monto = 0.0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            for (int i = 0; i < idEmpleados.size(); i++) {                
                preparedStatement = connection.prepareStatement(SQL_SUMA_VENTAS_MENSUALES_EQUIPO);
                preparedStatement.setString(1, month);
                preparedStatement.setString(2, year);
                preparedStatement.setInt(3, idEmpleados.get(i));
                resultSet = preparedStatement.executeQuery();                
                while (resultSet.next()) {
                    monto += resultSet.getDouble(1);                    
                }
            }            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return monto;
    }

    public double calcularMontoQuincena(String month, String year, int idEmpleado) {
        double monto = 0.0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SUMA_MONTOS_PRIMERA_QUINCENA);
            preparedStatement.setString(1, month);
            preparedStatement.setString(2, year);
            preparedStatement.setInt(3, idEmpleado);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                monto = resultSet.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return monto;
    }

    public double listarById(int id) {
        double monto = 0.0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_MONTO_VENTAS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                monto = resultSet.getDouble(1);
            }
            System.out.println("El monto de la venta realizada es " + monto);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return monto;
    }
    
    public double calcularTotalVentasMensualEmpleado(String month, String year, int idEmpleado) {
        double monto = 0.0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SUMA_VENTAS_MENSUALES_EMPLEADOS);
            preparedStatement.setString(1, month);
            preparedStatement.setString(2, year);
            preparedStatement.setInt(3, idEmpleado);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                monto = resultSet.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return monto;
    }
}
