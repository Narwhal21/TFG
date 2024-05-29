package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Pedido {

    public String ID_PEDIDO;
    public String ID_EMPLEADO;
    public String ID_CLIENTE;
    public String ESTADO;
    public String DIRECCION;
    public String FECHA_HORA;
    public int CANTIDAD;

    public Pedido(String id_pedido, String id_empleado, String id_cliente, String estado, String direccion, String fecha_hora, int cantidad) {
        this.ID_PEDIDO = id_pedido;
        this.ID_EMPLEADO = id_empleado;
        this.ID_CLIENTE = id_cliente;
        this.ESTADO = estado;
        this.DIRECCION = direccion;
        this.FECHA_HORA = fecha_hora;
        this.CANTIDAD = cantidad;
    }

    public Pedido() {

    }

    public String getID_PEDIDO() {
        return ID_PEDIDO;
    }

    public void setID_PEDIDO(String id_pedido) {
        this.ID_PEDIDO = id_pedido;
    }

    public String getID_EMPLEADO() {
        return ID_EMPLEADO;
    }

    public void setID_EMPLEADO(String id_empleado) {
        this.ID_EMPLEADO = id_empleado;
    }

    public String getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(String id_cliente) {
        this.ID_CLIENTE = id_cliente;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String estado) {
        this.ESTADO = estado;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String direccion) {
        this.DIRECCION = direccion;
    }

    public String getFECHA_HORA() {
        return FECHA_HORA;
    }

    public void setFECHA_HORA(String fecha_hora) {
        this.FECHA_HORA = fecha_hora;
    }

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(int cantidad) {
        this.CANTIDAD = cantidad;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id_pedido='" + ID_PEDIDO + '\'' +
                ", id_empleado='" + ID_EMPLEADO + '\'' +
                ", id_cliente='" + ID_CLIENTE + '\'' +
                ", estado='" + ESTADO + '\'' +
                ", direccion='" + DIRECCION + '\'' +
                ", fecha_hora='" + FECHA_HORA + '\'' +
                ", cantidad=" + CANTIDAD +
                '}';
    }

    public static String toArrayJSon(ArrayList<Pedido> pedidos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(pedidos);
    }
}
