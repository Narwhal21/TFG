package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDao implements IDao<Admin, String> {
    private final String SQL_FIND_ALL = "SELECT * FROM ADMIN WHERE 1=1";
    private final String SQL_ADD = "INSERT INTO ADMIN (ID_ADMIN, ID_PRIVATEZONE, NOMBRE, APELLIDO) VALUES (?, ?, ?, ?)";
    private final String SQL_DELETE = "DELETE FROM ADMIN WHERE ID_ADMIN = ?";
    private final String SQL_UPDATE = "UPDATE ADMIN SET ID_PRIVATEZONE = ?, NOMBRE = ?, APELLIDO = ? WHERE ID_ADMIN = ?";

    @Override
    public int add(Admin bean) {
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
                System.out.println("Seteando parámetros: ID_ADMIN=" + bean.getID_ADMIN() +
                        ", ID_PRIVATEZONE=" + bean.getID_PRIVATEZONE() +
                        ", NOMBRE=" + bean.getNOMBRE() +
                        ", APELLIDO=" + bean.getAPELLIDO());
                ps.setString(1, bean.getID_ADMIN());
                ps.setString(2, bean.getID_PRIVATEZONE());
                ps.setString(3, bean.getNOMBRE());
                ps.setString(4, bean.getAPELLIDO());
                result = ps.executeUpdate();
                System.out.println("Admin añadido con éxito: " + bean.toString());
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
                    System.out.println("Admin con ID " + id + " eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró ningún admin con el ID " + id + ".");
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
    public int update(Admin bean) {
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
                ps.setString(4, bean.getID_ADMIN());
                result = ps.executeUpdate();
                System.out.println("Admin actualizado con éxito: " + bean.toString());
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

    public ArrayList<Admin> findAll(Admin bean) {
        ArrayList<Admin> admins = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getID_ADMIN() != null) {
                    sql += " AND ID_ADMIN='" + bean.getID_ADMIN() + "'";
                }
                if (bean.getID_PRIVATEZONE() != null) {
                    sql += " AND ID_PRIVATEZONE='" + bean.getID_PRIVATEZONE() + "'";
                }
                if (bean.getNOMBRE() != null) {
                    sql += " AND NOMBRE='" + bean.getNOMBRE() + "'";
                }
                if (bean.getAPELLIDO() != null) {
                    sql += " AND APELLIDO='" + bean.getAPELLIDO() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setID_ADMIN(rs.getString("ID_ADMIN"));
                admin.setID_PRIVATEZONE(rs.getString("ID_PRIVATEZONE"));
                admin.setNOMBRE(rs.getString("NOMBRE"));
                admin.setAPELLIDO(rs.getString("APELLIDO"));

                admins.add(admin);
            }

        } catch (Exception ex) {
            admins.clear();
        } finally {
            motor.disconnect();
        }
        return admins;
    }
}
