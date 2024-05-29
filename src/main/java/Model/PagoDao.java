package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PagoDao implements IDao<Pago, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM PAGO WHERE 1=1";

    @Override
    public int add(Pago bean) {
        // Implementación de la lógica para agregar un pago
        return 0;
    }

    @Override
    public int delete(String id) {
        // Implementación de la lógica para eliminar un pago por ID
        return 0;
    }

    @Override
    public int update(Pago bean) {
        // Implementación de la lógica para actualizar un pago
        return 0;
    }

    public ArrayList<Pago> findAll(Pago bean) {
        ArrayList<Pago> pagos = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getID_PAGO() != null) {
                    sql += " AND ID_PAGO='" + bean.getID_PAGO() + "'";
                }
                if (bean.getID_CLIENTE() != null) {
                    sql += " AND ID_CLIENTE='" + bean.getID_CLIENTE() + "'";
                }
                if (bean.getTARJETA() != null) {
                    sql += " AND TARJETA='" + bean.getTARJETA() + "'";
                }
                if (bean.getPAYPAL() != null) {
                    sql += " AND PAYPAL='" + bean.getPAYPAL() + "'";
                }
                if (bean.getCONTRARREMBOLSO() != null) {
                    sql += " AND CONTRARREMBOLSO='" + bean.getCONTRARREMBOLSO() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Pago pago = new Pago();
                pago.setID_PAGO(rs.getString("ID_PAGO"));
                pago.setID_CLIENTE(rs.getString("ID_CLIENTE"));
                pago.setTARJETA(rs.getString("TARJETA"));
                pago.setPAYPAL(rs.getString("PAYPAL"));
                pago.setCONTRARREMBOLSO(rs.getString("CONTRARREMBOLSO"));
                pagos.add(pago);
            }

        } catch (Exception ex) {
            pagos.clear();
        } finally {
            motor.disconnect();
        }
        return pagos;
    }
}
