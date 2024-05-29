package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Cliente {

    public String ID_CLIENTE;
    public String NOMBRE;
    public String APELLIDO;
    public String DIRECCION;
    public String CORREO;

    public Cliente(String id_cliente, String nombre, String apellido, String direccion, String correo) {
        this.ID_CLIENTE = id_cliente;
        this.NOMBRE = nombre;
        this.APELLIDO = apellido;
        this.DIRECCION = direccion;
        this.CORREO = correo;
    }

    public Cliente() {

    }

    public String getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(String id_cliente) {
        this.ID_CLIENTE = id_cliente;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String nombre) {
        this.NOMBRE = nombre;
    }

    public String getAPELLIDO() {
        return APELLIDO;
    }

    public void setAPELLIDO(String apellido) {
        this.APELLIDO = apellido;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String direccion) {
        this.DIRECCION = direccion;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String correo) {
        this.CORREO = correo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id_cliente='" + ID_CLIENTE + '\'' +
                ", nombre='" + NOMBRE + '\'' +
                ", apellido='" + APELLIDO + '\'' +
                ", direccion='" + DIRECCION + '\'' +
                ", correo='" + CORREO + '\'' +
                '}';
    }

    public static String toArrayJSon(ArrayList<Cliente> clientes) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(clientes);
    }
}

