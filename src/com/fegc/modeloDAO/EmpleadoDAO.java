package com.fegc.modeloDAO;

import com.fegc.config.Conexion;
import com.fegc.modelo.Empleado;
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
public class EmpleadoDAO {
    private static final String SQL_SELECT_ALL_EMPLEADOS = "select * from empleado";
    
    public List<Empleado> listar() {
        List<Empleado> listaProductos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_EMPLEADOS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(resultSet.getInt(1));
                empleado.setNombres(resultSet.getString(2));                
                listaProductos.add(empleado);
                System.out.println(empleado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }
        return listaProductos;
    }
}
