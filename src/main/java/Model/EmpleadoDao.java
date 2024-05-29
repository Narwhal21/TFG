package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpleadoDao implements IDao<Empleado, String> {
    private final String SQL_FIND_ALL = "SELECT * FROM EMPLEADO WHERE 1=1";
    private final String SQL_ADD = "INSERT INTO EMPLEADO (ID_EMPLEADO, ID_PRIVATEZONE, NOMBRE, APELLIDO) VALUES (?, ?, ?, ?)";
    private final String SQL_DELETE = "DELETE FROM EMPLEADO WHERE ID_EMPLEADO = ?";
    private final String SQL_UPDATE = "UPDATE EMPLEADO SET ID_PRIVATEZONE = ?, NOMBRE = ?, APELLIDO = ? WHERE ID_EMPLEADO = ?";

    @Override
    public int add(Empleado bean) {
        int result = 0;
        MotorSQL motor = new MotorSQL();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            motor.connect();
            conn = motor.getConnection();
            if (conn != null) {
                System.out.println("Conexión a la base de datos establecida.");
                System.out.println("Preparando consulta SQL: " + SQL_ADD);
                ps = conn.prepareStatement(SQL_ADD);
                System.out.println("Seteando parámetros: ID_EMPLEADO=" + bean.getID_EMPLEADO() +
                        ", ID_PRIVATEZONE=" + bean.getID_PRIVATEZONE() +
                        ", NOMBRE=" + bean.getNOMBRE() +
                        ", APELLIDO=" + bean.getAPELLIDO());
                ps.setString(1, bean.getID_EMPLEADO());
                ps.setString(2, bean.getID_PRIVATEZONE());
                ps.setString(3, bean.getNOMBRE());
                ps.setString(4, bean.getAPELLIDO());
                result = ps.executeUpdate();
                System.out.println("Empleado añadido con éxito: " + bean.toString());
            } else {
                System.err.println("Error: No se pudo obtener la conexión.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la sentencia SQL: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) motor.disconnect();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int delete(String id) {
        int resp = 0;
        MotorSQL motor = new MotorSQL();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            motor.connect();
            conn = motor.getConnection();
            if (conn != null) {
                System.out.println("Preparando consulta SQL: " + SQL_DELETE);
                ps = conn.prepareStatement(SQL_DELETE);
                ps.setString(1, id);
                resp = ps.executeUpdate();
                if (resp > 0) {
                    System.out.println("Empleado con ID " + id + " eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró ningún empleado con el ID " + id + ".");
                }
            } else {
                System.err.println("Error: No se pudo obtener la conexión.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la sentencia SQL: " + ex.getMessage());
            ex.printStackTrace();
            resp = -1;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) motor.disconnect();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resp;
    }

    @Override
    public int update(Empleado bean) {
        int result = 0;
        MotorSQL motor = new MotorSQL();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            motor.connect();
            conn = motor.getConnection();
            if (conn != null) {
                System.out.println("Preparando consulta SQL: " + SQL_UPDATE);
                ps = conn.prepareStatement(SQL_UPDATE);
                ps.setString(1, bean.getID_PRIVATEZONE());
                ps.setString(2, bean.getNOMBRE());
                ps.setString(3, bean.getAPELLIDO());
                ps.setString(4, bean.getID_EMPLEADO());
                result = ps.executeUpdate();
                System.out.println("Empleado actualizado con éxito: " + bean.toString());
            } else {
                System.err.println("Error: No se pudo obtener la conexión.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la sentencia SQL: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) motor.disconnect();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<Empleado> findAll(Empleado bean) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if ((bean).getID_EMPLEADO() != null) {
                    sql += " AND ID_EMPLEADO='" + (bean).getID_EMPLEADO() + "'";
                }
                if ((bean).getID_PRIVATEZONE() != null) {
                    sql += " AND ID_PRIVATEZONE='" + (bean).getID_PRIVATEZONE() + "'";
                }
                if ((bean).getNOMBRE() != null) {
                    sql += " AND NOMBRE='" + (bean).getNOMBRE() + "'";
                }
                if ((bean).getAPELLIDO() != null) {
                    sql += " AND APELLIDO='" + (bean).getAPELLIDO() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setID_EMPLEADO(rs.getString("ID_EMPLEADO"));
                empleado.setID_PRIVATEZONE(rs.getString("ID_PRIVATEZONE"));
                empleado.setNOMBRE(rs.getString("NOMBRE"));
                empleado.setAPELLIDO(rs.getString("APELLIDO"));

                empleados.add(empleado);
            }

        } catch (Exception ex) {
            empleados.clear();
        } finally {
            motor.disconnect();
        }
        return empleados;
    }
}
