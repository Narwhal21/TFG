package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Admin {

    public String ID_ADMIN;
    public String ID_PRIVATEZONE;
    public String NOMBRE;
    public String APELLIDO;

    public Admin(String id_admin, String id_privatezone, String nombre, String apellido) {
        this.ID_ADMIN = id_admin;
        this.ID_PRIVATEZONE = id_privatezone;
        this.NOMBRE = nombre;
        this.APELLIDO = apellido;
    }

    public Admin() {

    }

    public String getID_ADMIN() {
        return ID_ADMIN;
    }

    public void setID_ADMIN(String id_admin) {
        this.ID_ADMIN = id_admin;
    }

    public String getID_PRIVATEZONE() {
        return ID_PRIVATEZONE;
    }

    public void setID_PRIVATEZONE(String id_privatezone) {
        this.ID_PRIVATEZONE = id_privatezone;
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

    @Override
    public String toString() {
        return "Admin{" +
                "id_admin='" + ID_ADMIN + '\'' +
                ", id_privatezone='" + ID_PRIVATEZONE + '\'' +
                ", nombre='" + NOMBRE + '\'' +
                ", apellido='" + APELLIDO + '\'' +
                '}';
    }

    public static String toArrayJSon(ArrayList<Admin> admins) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(admins);
    }
}

