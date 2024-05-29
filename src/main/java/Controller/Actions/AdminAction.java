package Controller.Actions;

import Model.Admin;
import Model.AdminDao;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class AdminAction implements IAction {

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
        String idReq = request.getParameter("ID_ADMIN");
        if (idReq != null && !idReq.isEmpty()) {
            try {
                AdminDao adminDao = new AdminDao();
                int result = adminDao.delete(idReq);
                if (result > 0) {
                    return "Admin eliminado con éxito";
                } else {
                    return "No se encontró ningún admin con el ID " + idReq;
                }
            } catch (Exception e) {
                return "Error al eliminar el admin: " + e.getMessage();
            }
        } else {
            return "ID_ADMIN no proporcionado";
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

        Admin p = null;

        try {
            p = gson.fromJson(jsonReceived.toString(), Admin.class);
            if (p == null) {
                System.err.println("Error: El objeto Admin es nulo después de la deserialización.");
                return "Error: El objeto Admin es nulo después de la deserialización.";
            }
        } catch (JsonSyntaxException e) {
            System.err.println("Error en la sintaxis del JSON: " + e.getMessage());
            return "Error en la sintaxis del JSON: " + e.getMessage();
        } catch (Exception e) {
            System.err.println("Error en la deserialización del JSON: " + e.getMessage());
            return "Error en la deserialización del JSON: " + e.getMessage();
        }

        AdminDao adminDao = new AdminDao();
        int result = adminDao.add(p);
        if (result > 0) {
            System.out.println("Admin añadido con éxito: " + p);
            return "Admin añadido con éxito";
        } else {
            System.out.println("Error: No se pudo añadir el admin.");
            return "Error: No se pudo añadir el admin.";
        }
    }

    private String findAll() {
        AdminDao adminDao = new AdminDao();
        ArrayList<Admin> admins = adminDao.findAll(null);
        return Admin.toArrayJSon(admins);
    }

    private String update(HttpServletRequest request) {
        String ID_ADMIN = request.getParameter("ID_ADMIN");
        String ID_PRIVATEZONE = request.getParameter("ID_PRIVATEZONE");
        String NOMBRE = request.getParameter("NOMBRE");
        String APELLIDO = request.getParameter("APELLIDO");

        JSONObject jsonResponse = new JSONObject();

        if (ID_ADMIN != null && !ID_ADMIN.isEmpty() &&
                ID_PRIVATEZONE != null && !ID_PRIVATEZONE.isEmpty() &&
                NOMBRE != null && !NOMBRE.isEmpty() &&
                APELLIDO != null && !APELLIDO.isEmpty()) {
            try {
                Admin admin = new Admin(ID_ADMIN, ID_PRIVATEZONE, NOMBRE, APELLIDO);
                AdminDao adminDao = new AdminDao();
                int result = adminDao.update(admin);

                if (result > 0) {
                    jsonResponse.put("success", true);
                    jsonResponse.put("message", "Admin actualizado con éxito");
                } else {
                    jsonResponse.put("success", false);
                    jsonResponse.put("message", "Error. No se pudo actualizar el admin");
                }
            } catch (Exception e) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Error al actualizar el admin: " + e.getMessage());
            }
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error. Todos los campos son obligatorios");
        }

        return jsonResponse.toString();
    }
}
