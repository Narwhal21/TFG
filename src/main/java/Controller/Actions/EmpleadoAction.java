package Controller.Actions;

import Model.Empleado;
import Model.EmpleadoDao;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class EmpleadoAction implements IAction {

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
        String idReq = request.getParameter("ID_EMPLEADO");
        if (idReq != null && !idReq.isEmpty()) {
            try {
                EmpleadoDao empleadoDao = new EmpleadoDao();
                int result = empleadoDao.delete(idReq);
                if (result > 0) {
                    return "Empleado eliminado con éxito";
                } else {
                    return "No se encontró ningún empleado con el ID " + idReq;
                }
            } catch (Exception e) {
                return "Error al eliminar el empleado: " + e.getMessage();
            }
        } else {
            return "ID_EMPLEADO no proporcionado";
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

        Empleado p = null;

        try {
            p = gson.fromJson(jsonReceived.toString(), Empleado.class);
            if (p == null) {
                System.err.println("Error: El objeto Empleado es nulo después de la deserialización.");
                return "Error: El objeto Empleado es nulo después de la deserialización.";
            }
        } catch (JsonSyntaxException e) {
            System.err.println("Error en la sintaxis del JSON: " + e.getMessage());
            return "Error en la sintaxis del JSON: " + e.getMessage();
        } catch (Exception e) {
            System.err.println("Error en la deserialización del JSON: " + e.getMessage());
            return "Error en la deserialización del JSON: " + e.getMessage();
        }

        EmpleadoDao empleadoDao = new EmpleadoDao();
        int result = empleadoDao.add(p);
        if (result > 0) {
            System.out.println("Empleado añadido con éxito: " + p);
            return "Empleado añadido con éxito";
        } else {
            System.out.println("Error: No se pudo añadir el empleado.");
            return "Error: No se pudo añadir el empleado.";
        }
    }

    private String findAll() {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        ArrayList<Empleado> empleados = empleadoDao.findAll(null);
        return Empleado.toArrayJSon(empleados);
    }

    private String update(HttpServletRequest request) {
        String ID_EMPLEADO = request.getParameter("ID_EMPLEADO");
        String ID_PRIVATEZONE = request.getParameter("ID_PRIVATEZONE");
        String NOMBRE = request.getParameter("NOMBRE");
        String APELLIDO = request.getParameter("APELLIDO");

        JSONObject jsonResponse = new JSONObject();

        if (ID_EMPLEADO != null && !ID_EMPLEADO.isEmpty() &&
                ID_PRIVATEZONE != null && !ID_PRIVATEZONE.isEmpty() &&
                NOMBRE != null && !NOMBRE.isEmpty() &&
                APELLIDO != null && !APELLIDO.isEmpty()) {
            try {
                Empleado empleado = new Empleado(ID_EMPLEADO, ID_PRIVATEZONE, NOMBRE, APELLIDO);
                EmpleadoDao empleadoDao = new EmpleadoDao();
                int result = empleadoDao.update(empleado);

                if (result > 0) {
                    jsonResponse.put("success", true);
                    jsonResponse.put("message", "Empleado actualizado con éxito");
                } else {
                    jsonResponse.put("success", false);
                    jsonResponse.put("message", "Error. No se pudo actualizar el empleado");
                }
            } catch (Exception e) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Error al actualizar el empleado: " + e.getMessage());
            }
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error. Todos los campos son obligatorios");
        }

        return jsonResponse.toString();
    }
}
