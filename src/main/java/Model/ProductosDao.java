package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosDao implements IDao<Productos, String> {
    private final String SQL_FIND_ALL = "SELECT * FROM PRODUCTOS WHERE 1=1";
    private final String SQL_ADD = "INSERT INTO PRODUCTOS (ID_PRODUCTOS, ID_CATEGORIA, NOMBRE, PRECIO, PRODUCTOSIMG) VALUES (?, ?, ?, ?,?)";
    private final String SQL_DELETE = "DELETE FROM PRODUCTOS WHERE ID_PRODUCTOS = ?";
    private final String SQL_UPDATE = "UPDATE PRODUCTOS SET ID_CATEGORIA = ?, NOMBRE = ?, PRECIO = ?, PRODUCTOSIMG = ? WHERE ID_PRODUCTOS = ?";

    @Override
    public int add(Productos bean) {
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
                System.out.println("Seteando parámetros: ID_PRODUCTOS=" + bean.getID_PRODUCTOS() +
                        ", ID_CATEGORIA=" + bean.getID_CATEGORIA() +
                        ", NOMBRE=" + bean.getNOMBRE() +
                        ", PRECIO=" + bean.getPRECIO() +
                        ", PRODUCTOSIMG=" + bean.getPRODUCTOSIMG());
                ps.setString(1, bean.getID_PRODUCTOS());
                ps.setString(2, bean.getID_CATEGORIA());
                ps.setString(3, bean.getNOMBRE());
                ps.setString(4, bean.getPRECIO());
                ps.setString(5, bean.getPRODUCTOSIMG());
                result = ps.executeUpdate();
                System.out.println("Producto añadido con éxito: " + bean.toString());
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
                    System.out.println("Producto con ID " + id + " eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró ningún producto con el ID " + id + ".");
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
    public int update(Productos bean) {
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
                ps.setString(1, bean.getID_CATEGORIA());
                ps.setString(2, bean.getNOMBRE());
                ps.setString(3, bean.getPRECIO());
                ps.setString(4, bean.getPRODUCTOSIMG());
                ps.setString(5, bean.getID_PRODUCTOS());
                result = ps.executeUpdate();
                System.out.println("Producto actualizado con éxito: " + bean.toString());
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
    public ArrayList<Productos> findAll(Productos bean) {
        ArrayList<Productos> productos = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if ((bean).getID_PRODUCTOS() != null) {
                    sql += " AND ID_PRODUCTOS='" + (bean).getID_PRODUCTOS() + "'";
                }
                if ((bean).getID_CATEGORIA() != null) {
                    sql += " AND ID_CATEGORIA='" + (bean).getID_CATEGORIA() + "'";
                }
                if ((bean).getNOMBRE() != null) {
                    sql += " AND NOMBRE='" + (bean).getNOMBRE() + "'";
                }
                if ((bean).getPRECIO() != null) {
                    sql += " AND PRECIO='" + (bean).getPRECIO() + "'";
                }
                if ((bean).getPRODUCTOSIMG() != null) {
                    sql += " AND PRECIO='" + (bean).getPRODUCTOSIMG() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Productos producto = new Productos();
                producto.setID_PRODUCTOS(rs.getString("ID_PRODUCTOS"));
                producto.setID_CATEGORIA(rs.getString("ID_CATEGORIA"));
                producto.setNOMBRE(rs.getString("NOMBRE"));
                producto.setPRECIO(rs.getString("PRECIO"));
                producto.setPRODUCTOSIMG(rs.getString("PRODUCTOSIMG"));

                productos.add(producto);
            }

        } catch (Exception ex) {
            productos.clear();
        } finally {
            motor.disconnect();
        }
        return productos;
    }

}