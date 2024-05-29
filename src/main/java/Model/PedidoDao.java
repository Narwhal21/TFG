package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PedidoDao implements IDao<Pedido, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM PEDIDO WHERE 1=1";

    @Override
    public int add(Pedido bean) {
        // Implementación de la lógica para agregar un pedido
        return 0;
    }

    @Override
    public int delete(String id) {
        // Implementación de la lógica para eliminar un pedido por ID
        return 0;
    }

    @Override
    public int update(Pedido bean) {
        // Implementación de la lógica para actualizar un pedido
        return 0;
    }

    public ArrayList<Pedido> findAll(Pedido bean) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getID_PEDIDO() != null) {
                    sql += " AND ID_PEDIDO='" + bean.getID_PEDIDO() + "'";
                }
                if (bean.getID_EMPLEADO() != null) {
                    sql += " AND ID_EMPLEADO='" + bean.getID_EMPLEADO() + "'";
                }
                if (bean.getID_CLIENTE() != null) {
                    sql += " AND ID_CLIENTE='" + bean.getID_CLIENTE() + "'";
                }
                if (bean.getESTADO() != null) {
                    sql += " AND ESTADO='" + bean.getESTADO() + "'";
                }
                if (bean.getDIRECCION() != null) {
                    sql += " AND DIRECCION='" + bean.getDIRECCION() + "'";
                }
                if (bean.getFECHA_HORA() != null) {
                    sql += " AND FECHA_HORA='" + bean.getFECHA_HORA() + "'";
                }
                if (bean.getCANTIDAD() != 0) {
                    sql += " AND CANTIDAD=" + bean.getCANTIDAD();
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setID_PEDIDO(rs.getString("ID_PEDIDO"));
                pedido.setID_EMPLEADO(rs.getString("ID_EMPLEADO"));
                pedido.setID_CLIENTE(rs.getString("ID_CLIENTE"));
                pedido.setESTADO(rs.getString("ESTADO"));
                pedido.setDIRECCION(rs.getString("DIRECCION"));
                pedido.setFECHA_HORA(rs.getString("FECHA_HORA"));
                pedido.setCANTIDAD(rs.getInt("CANTIDAD"));
                pedidos.add(pedido);
            }

        } catch (Exception ex) {
            pedidos.clear();
        } finally {
            motor.disconnect();
        }
        return pedidos;
    }
}

