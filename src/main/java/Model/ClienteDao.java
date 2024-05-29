package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDao implements IDao<Cliente, String> {
    private final String SQL_FIND_ALL = "SELECT * FROM CLIENTE WHERE 1=1";
    private final String SQL_ADD = "INSERT INTO CLIENTE (ID_CLIENTE, NOMBRE, APELLIDO, DIRECCION, CORREO) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_DELETE = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";
    private final String SQL_UPDATE = "UPDATE CLIENTE SET NOMBRE = ?, APELLIDO = ?, DIRECCION = ?, CORREO = ? WHERE ID_CLIENTE = ?";

    @Override
    public int add(Cliente bean) {
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
                System.out.println("Seteando parámetros: ID_CLIENTE=" + bean.getID_CLIENTE() +
                        ", NOMBRE=" + bean.getNOMBRE() +
                        ", APELLIDO=" + bean.getAPELLIDO() +
                        ", DIRECCION=" + bean.getDIRECCION() +
                        ", CORREO=" + bean.getCORREO());
                ps.setString(1, bean.getID_CLIENTE());
                ps.setString(2, bean.getNOMBRE());
                ps.setString(3, bean.getAPELLIDO());
                ps.setString(4, bean.getDIRECCION());
                ps.setString(5, bean.getCORREO());
                result = ps.executeUpdate();
                System.out.println("Cliente añadido con éxito: " + bean.toString());
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
                    System.out.println("Cliente con ID " + id + " eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró ningún cliente con el ID " + id + ".");
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
    public int update(Cliente bean) {
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
                ps.setString(1, bean.getNOMBRE());
                ps.setString(2, bean.getAPELLIDO());
                ps.setString(3, bean.getDIRECCION());
                ps.setString(4, bean.getCORREO());
                ps.setString(5, bean.getID_CLIENTE());
                result = ps.executeUpdate();
                System.out.println("Cliente actualizado con éxito: " + bean.toString());
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

    public ArrayList<Cliente> findAll(Cliente bean) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getID_CLIENTE() != null) {
                    sql += " AND ID_CLIENTE='" + bean.getID_CLIENTE() + "'";
                }
                if (bean.getNOMBRE() != null) {
                    sql += " AND NOMBRE='" + bean.getNOMBRE() + "'";
                }
                if (bean.getAPELLIDO() != null) {
                    sql += " AND APELLIDO='" + bean.getAPELLIDO() + "'";
                }
                if (bean.getDIRECCION() != null) {
                    sql += " AND DIRECCION='" + bean.getDIRECCION() + "'";
                }
                if (bean.getCORREO() != null) {
                    sql += " AND CORREO='" + bean.getCORREO() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setID_CLIENTE(rs.getString("ID_CLIENTE"));
                cliente.setNOMBRE(rs.getString("NOMBRE"));
                cliente.setAPELLIDO(rs.getString("APELLIDO"));
                cliente.setDIRECCION(rs.getString("DIRECCION"));
                cliente.setCORREO(rs.getString("CORREO"));

                clientes.add(cliente);
            }

        } catch (Exception ex) {
            clientes.clear();
        } finally {
            motor.disconnect();
        }
        return clientes;
    }
}
