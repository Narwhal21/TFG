package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Productos {
    public String ID_PRODUCTOS;
    public String ID_CATEGORIA;
    public String NOMBRE;
    public String PRECIO;
    public String PRODUCTOSIMG;
    public Productos p;

    public Productos(String id_productos, String id_categoria, String nombre, String precio, String productosimg) {
        this.ID_PRODUCTOS = id_productos;
        this.ID_CATEGORIA = id_categoria;
        this.NOMBRE = nombre;
        this.PRECIO = precio;
        this.PRODUCTOSIMG = productosimg;
    }

    public Productos() {

    }
    public String getID_PRODUCTOS(){

        return ID_PRODUCTOS;
    }

    public void setID_PRODUCTOS(String id_productos){

        ID_PRODUCTOS = id_productos;
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

        NOMBRE = nombre;
    }

    public String getPRECIO() {

        return PRECIO;
    }
    public void setPRECIO(String precio){

        PRECIO = precio;
    }
    public String getPRODUCTOSIMG() {

        return PRODUCTOSIMG;
    }

    public void setPRODUCTOSIMG(String productosimg) {

        PRODUCTOSIMG = productosimg;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "ID_PRODUCTOS=" + ID_PRODUCTOS +
                ", ID_CATEGORIA='" + ID_CATEGORIA + '\'' +
                ", NOMBRE='" + NOMBRE + '\'' +
                ", PRECIO=" + PRECIO +
                ", PRODUCTOSIMG=" + PRODUCTOSIMG +
                '}';
    }

    public static String toArrayJSon(ArrayList<Productos> productos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(productos);
    }


    public void add(Productos productos) {
    }

}

