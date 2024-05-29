package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
public class CategoriaDao implements IDao <Categoria, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM CATEGORIA WHERE 1=1";

    @Override
    public int add(Categoria bean) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int update(Categoria bean) {
        return 0;
    }

    public ArrayList<Categoria> findAll(Categoria bean) {
        ArrayList<Categoria> categorias = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if ((bean).getID_CATEGORIA() != null) {
                    sql += " AND ID_CATEGORIA='" + (bean).getID_CATEGORIA() + "'";
                }
                if ((bean).getNOMBRE() != null) {
                    sql += " AND NOMBRE='" + (bean).getNOMBRE() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setID_CATEGORIA(rs.getString("ID_CATEGORIA"));
                categoria.setNOMBRE(rs.getString("NOMBRE"));

                categorias.add(categoria);
            }

        } catch (Exception ex) {
            categorias.clear();
        } finally {
            motor.disconnect();
        }
        return categorias;
    }

}

