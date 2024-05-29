package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class PrivateZone {

    public String ID_PRIVATEZONE;
    public String ADMINISTRARPEDIDOS;
    public String ADMINISTRARRESERVA;

    public PrivateZone(String id_privatezone, String administrarPedidos, String administrarReserva) {
        this.ID_PRIVATEZONE = id_privatezone;
        this.ADMINISTRARPEDIDOS = administrarPedidos;
        this.ADMINISTRARRESERVA = administrarReserva;
    }

    public PrivateZone() {

    }

    public String getID_PRIVATEZONE() {
        return ID_PRIVATEZONE;
    }

    public void setID_PRIVATEZONE(String id_privatezone) {
        this.ID_PRIVATEZONE = id_privatezone;
    }

    public String getADMINISTRARPEDIDOS() {
        return ADMINISTRARPEDIDOS;
    }

    public void setADMINISTRARPEDIDOS(String administrarPedidos) {
        this.ADMINISTRARPEDIDOS = administrarPedidos;
    }

    public String getADMINISTRARRESERVA() {
        return ADMINISTRARRESERVA;
    }

    public void setADMINISTRARRESERVA(String administrarReserva) {
        this.ADMINISTRARRESERVA = administrarReserva;
    }

    @Override
    public String toString() {
        return "PrivateZone{" +
                "id_privatezone='" + ID_PRIVATEZONE + '\'' +
                ", administrarPedidos='" + ADMINISTRARPEDIDOS + '\'' +
                ", administrarReserva='" + ADMINISTRARRESERVA + '\'' +
                '}';
    }

    public static String toArrayJSon(ArrayList<PrivateZone> privateZones) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(privateZones);
    }
}
