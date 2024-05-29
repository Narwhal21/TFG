package Controller.Actions;

import Model.Cliente;
import Model.ClienteDao;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ClienteAction implements IAction {

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
                strReturn = "ERROR. Acción inválida";
        }
        return strReturn;
    }

    private String delete(HttpServletRequest request) {
        String idReq = request.getParameter("ID_CLIENTE");
        if (idReq != null && !idReq.isEmpty()) {
            try {
                ClienteDao clienteDao = new ClienteDao();
                int result = clienteDao.delete(idReq);
                if (result > 0) {
                    return "Cliente eliminado con éxito";
                } else {
                    return "No se encontró ningún cliente con el ID " + idReq;
                }
            } catch (Exception e) {
                return "Error al eliminar el cliente: " + e.getMessage();
            }
        } else {
            return "ID_CLIENTE no proporcionado";
        }
    }

    private String add(HttpServletRequest request) throws ServletException, IOException {
        Gson gson = new Gson();
        BufferedReader reader = request.getReader();
        StringBuilder jsonReceived = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonReceived.append(line);
        }

        System.out.println("JSON recibido: " + jsonReceived.toString());

        Cliente p = null;

        try {
            p = gson.fromJson(jsonReceived.toString(), Cliente.class);
            if (p == null) {
                System.err.println("Error: El objeto Cliente es nulo después de la deserialización.");
                return "Error: El objeto Cliente es nulo después de la deserialización.";
            }
        } catch (JsonSyntaxException e) {
            System.err.println("Error en la sintaxis del JSON: " + e.getMessage());
            return "Error en la sintaxis del JSON: " + e.getMessage();
        } catch (Exception e) {
            System.err.println("Error en la deserialización del JSON: " + e.getMessage());
            return "Error en la deserialización del JSON: " + e.getMessage();
        }

        ClienteDao clienteDao = new ClienteDao();
        int result = clienteDao.add(p);
        if (result > 0) {
            System.out.println("Cliente añadido con éxito: " + p);
            return "Cliente añadido con éxito";
        } else {
            System.out.println("Error: No se pudo añadir el cliente.");
            return "Error: No se pudo añadir el cliente.";
        }
    }

    private String findAll() {
        ClienteDao clienteDao = new ClienteDao();
        ArrayList<Cliente> clientes = clienteDao.findAll(null);
        return Cliente.toArrayJSon(clientes);
    }

    private String update(HttpServletRequest request) {
        String ID_CLIENTE = request.getParameter("ID_CLIENTE");
        String NOMBRE = request.getParameter("NOMBRE");
        String APELLIDO = request.getParameter("APELLIDO");
        String DIRECCION = request.getParameter("DIRECCION");
        String CORREO = request.getParameter("CORREO");

        JSONObject jsonResponse = new JSONObject();

        if (ID_CLIENTE != null && !ID_CLIENTE.isEmpty() &&
                NOMBRE != null && !NOMBRE.isEmpty() &&
                APELLIDO != null && !APELLIDO.isEmpty() &&
                DIRECCION != null && !DIRECCION.isEmpty() &&
                CORREO != null && !CORREO.isEmpty()) {
            try {
                Cliente cliente = new Cliente(ID_CLIENTE, NOMBRE, APELLIDO, DIRECCION, CORREO);
                ClienteDao clienteDao = new ClienteDao();
                int result = clienteDao.update(cliente);

                if (result > 0) {
                    jsonResponse.put("success", true);
                    jsonResponse.put("message", "Cliente actualizado con éxito");
                } else {
                    jsonResponse.put("success", false);
                    jsonResponse.put("message", "Error. No se pudo actualizar el cliente");
                }
            } catch (Exception e) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Error al actualizar el cliente: " + e.getMessage());
            }
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error. Todos los campos son obligatorios");
        }

        return jsonResponse.toString();
    }
}
