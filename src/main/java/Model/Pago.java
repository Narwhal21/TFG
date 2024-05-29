package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Pago {

    public String ID_PAGO;
    public String ID_CLIENTE;
    public String TARJETA;
    public String PAYPAL;
    public String CONTRARREMBOLSO;

    public Pago(String id_pago, String id_cliente, String tarjeta, String paypal, String contrarrembolso) {
        this.ID_PAGO = id_pago;
        this.ID_CLIENTE = id_cliente;
        this.TARJETA = tarjeta;
        this.PAYPAL = paypal;
        this.CONTRARREMBOLSO = contrarrembolso;
    }

    public Pago() {

    }

    public String getID_PAGO() {
        return ID_PAGO;
    }

    public void setID_PAGO(String id_pago) {
        this.ID_PAGO = id_pago;
    }

    public String getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(String id_cliente) {
        this.ID_CLIENTE = id_cliente;
    }

    public String getTARJETA() {
        return TARJETA;
    }

    public void setTARJETA(String tarjeta) {
        this.TARJETA = tarjeta;
    }

    public String getPAYPAL() {
        return PAYPAL;
    }

    public void setPAYPAL(String paypal) {
        this.PAYPAL = paypal;
    }

    public String getCONTRARREMBOLSO() {
        return CONTRARREMBOLSO;
    }

    public void setCONTRARREMBOLSO(String contrarrembolso) {
        this.CONTRARREMBOLSO = contrarrembolso;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id_pago='" + ID_PAGO + '\'' +
                ", id_cliente='" + ID_CLIENTE + '\'' +
                ", tarjeta='" + TARJETA + '\'' +
                ", paypal='" + PAYPAL + '\'' +
                ", contrarrembolso='" + CONTRARREMBOLSO + '\'' +
                '}';
    }

    public static String toArrayJSon(ArrayList<Pago> pagos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(pagos);
    }
}
