package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PrivateZoneDao implements IDao<PrivateZone, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM PRIVATEZONE WHERE 1=1";

    @Override
    public int add(PrivateZone bean) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int update(PrivateZone bean) {
        return 0;
    }

    public ArrayList<PrivateZone> findAll(PrivateZone bean) {
        ArrayList<PrivateZone> privateZones = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getID_PRIVATEZONE() != null) {
                    sql += " AND ID_PRIVATEZONE='" + bean.getID_PRIVATEZONE() + "'";
                }
                if (bean.getADMINISTRARPEDIDOS() != null) {
                    sql += " AND ADMINISTRARPEDIDOS='" + bean.getADMINISTRARPEDIDOS() + "'";
                }
                if (bean.getADMINISTRARRESERVA() != null) {
                    sql += " AND ADMINISTRARRESERVA='" + bean.getADMINISTRARRESERVA() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                PrivateZone privateZone = new PrivateZone();
                privateZone.setID_PRIVATEZONE(rs.getString("ID_PRIVATEZONE"));
                privateZone.setADMINISTRARPEDIDOS(rs.getString("ADMINISTRARPEDIDOS"));
                privateZone.setADMINISTRARRESERVA(rs.getString("ADMINISTRARRESERVA"));
                privateZones.add(privateZone);
            }

        } catch (Exception ex) {
            privateZones.clear();
        } finally {
            motor.disconnect();
        }
        return privateZones;
    }
}
