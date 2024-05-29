package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Empleado {

    public String ID_EMPLEADO;
    public String ID_PRIVATEZONE;
    public String NOMBRE;
    public String APELLIDO;

    public Empleado(String id_empleado, String id_privatezone, String nombre, String apellido) {
        this.ID_EMPLEADO = id_empleado;
        this.ID_PRIVATEZONE = id_privatezone;
        this.NOMBRE = nombre;
        this.APELLIDO = apellido;
    }

    public Empleado() {

    }

    public String getID_EMPLEADO() {
        return ID_EMPLEADO;
    }

    public void setID_EMPLEADO(String id_empleado) {
        this.ID_EMPLEADO = id_empleado;
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
        return "Empleado{" +
                "id_empleado='" + ID_EMPLEADO + '\'' +
                ", id_privatezone='" + ID_PRIVATEZONE + '\'' +
                ", nombre='" + NOMBRE + '\'' +
                ", apellido='" + APELLIDO + '\'' +
                '}';
    }

    public static String toArrayJSon(ArrayList<Empleado> empleados) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(empleados);
    }
}

