package Controller.Actions;

import Model.Productos;
import Model.ProductosDao;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


public class ProductosAction implements IAction {
    private Productos productos;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        String strReturn = "";
        switch (action) {
            case "FIND_ALL":
                strReturn = findAll();
                break;
            case "DELETE":
                strReturn = delete(request);
                break;
            case "ADD":
                strReturn = add(request);
                break;
            case "UPDATE":
                strReturn = update(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String delete(HttpServletRequest request) {
        String idReq = request.getParameter("ID_PRODUCTOS");
        if (idReq != null && !idReq.isEmpty()) {
            try {
                ProductosDao productosDao = new ProductosDao();
                int result = productosDao.delete(idReq);
                if (result > 0) {
                    return "Producto eliminado con éxito";
                } else {
                    return "No se encontró ningún producto con el ID " + idReq;
                }
            } catch (Exception e) {
                return "Error al eliminar el producto: " + e.getMessage();
            }
        } else {
            return "ID_PRODUCTOS no proporcionado";
        }
    }

    private String add(HttpServletRequest request) throws ServletException, IOException {
        Gson gson = new Gson();
        BufferedReader reader = request.getReader();
        StringBuilder jsonReceived = new StringBuilder();
        String line;

        // Leer el JSON recibido y construir el String
        while ((line = reader.readLine()) != null) {
            jsonReceived.append(line);
        }

        // Imprimir el JSON recibido para depuración
        System.out.println("JSON recibido: " + jsonReceived.toString());

        Productos p = null;

        try {
            // Deserializar el JSON en un objeto Productos
            p = gson.fromJson(jsonReceived.toString(), Productos.class);
            if (p == null) {
                System.err.println("Error: El objeto Productos es nulo después de la deserialización.");
                return "Error: El objeto Productos es nulo después de la deserialización.";
            }
        } catch (JsonSyntaxException e) {
            System.err.println("Error en la sintaxis del JSON: " + e.getMessage());
            return "Error en la sintaxis del JSON: " + e.getMessage();
        } catch (Exception e) {
            System.err.println("Error en la deserialización del JSON: " + e.getMessage());
            return "Error en la deserialización del JSON: " + e.getMessage();
        }

        // Verificaciones adicionales aquí, si es necesario
        ProductosDao productosDao = new ProductosDao();
        int result = productosDao.add(p);
        if (result > 0) {
            System.out.println("Producto añadido con éxito: " + p);
            return "Producto añadido con éxito";
        } else {
            System.out.println("Error: No se pudo añadir el producto.");
            return "Error: No se pudo añadir el producto.";
        }
    }


    private String findAll() {
        ProductosDao productosDao = new ProductosDao();
        return productos.toArrayJSon(productosDao.findAll(null));
    }


    private String update(HttpServletRequest request) {
        String ID_PRODUCTOS = request.getParameter("ID_PRODUCTOS");
        String ID_CATEGORIA = request.getParameter("ID_CATEGORIA");
        String NOMBRE = request.getParameter("NOMBRE");
        String PRECIO = request.getParameter("PRECIO");
        String PRODUCTOSIMG = request.getParameter("PRODUCTOSIMG");

        JSONObject jsonResponse = new JSONObject();

        if (ID_PRODUCTOS != null && !ID_PRODUCTOS.isEmpty() &&
                ID_CATEGORIA != null && !ID_CATEGORIA.isEmpty() &&
                NOMBRE != null && !NOMBRE.isEmpty() &&
                PRECIO != null && !PRECIO.isEmpty()) {
            try {
                String precio = String.valueOf(Double.parseDouble(PRECIO));
                Productos productos = new Productos(ID_PRODUCTOS, ID_CATEGORIA, NOMBRE, precio, PRODUCTOSIMG);
                ProductosDao productosDao = new ProductosDao();
                int result = productosDao.update(productos);

                if (result > 0) {
                    jsonResponse.put("success", true);
                    jsonResponse.put("message", "Producto actualizado con éxito");
                } else {
                    jsonResponse.put("success", false);
                    jsonResponse.put("message", "Error. No se pudo actualizar el producto");
                }
            } catch (NumberFormatException e) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Error. El formato del precio no es válido: " + e.getMessage());
            } catch (Exception e) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Error al actualizar el producto: " + e.getMessage());
            }
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error. Todos los campos son obligatorios");
        }

        return jsonResponse.toString();
    }
}

