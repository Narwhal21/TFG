package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;


public class Categoria {

    public String ID_CATEGORIA;

    public String NOMBRE;

    public Categoria(String id_categoria, String nombre) {
        this.ID_CATEGORIA = id_categoria;
        this.NOMBRE = nombre;
    }

    public Categoria(){

    }

    public String getID_CATEGORIA() {
        return ID_CATEGORIA;
    }

    public void setID_CATEGORIA(String id_categoria) {
        this.ID_CATEGORIA = id_categoria;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String nombre) {
        this.NOMBRE = nombre;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id_categoria=" + ID_CATEGORIA +
                ", nombre='" + NOMBRE + '\'' +
                '}';
    }



    public static String toArrayJSon(ArrayList<Categoria> categoria) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(categoria);
    }




}
