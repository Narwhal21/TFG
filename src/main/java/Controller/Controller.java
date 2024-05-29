package Controller;

import Controller.Actions.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private static final Map<String, IAction> actions = new HashMap<>();

    static {
        actions.put("CATEGORIA", new CategoriaAction());
        actions.put("PRODUCTOS", new ProductosAction());
        actions.put("PRIVATEZONE", new PrivateZoneAction());
        actions.put("EMPLEADO", new EmpleadoAction());
        actions.put("CLIENTE", new ClienteAction());
        actions.put("PEDIDO", new PedidoAction());
        actions.put("PAGO", new PagoAction());
        actions.put("ADMIN", new AdminAction());
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "false");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String strAction = request.getParameter("ACTION");

        if (strAction != null && !strAction.isEmpty()) {
            String[] arrayAction = strAction.split("\\."); // [0] ENTITY <-> [1] ACTION
            if (arrayAction.length == 2) {
                String entity = arrayAction[0].toUpperCase();
                String action = arrayAction[1].toUpperCase();

                IAction actionExecutor = actions.get(entity);
                if (actionExecutor != null) {
                    try {
                        out.print(actionExecutor.execute(request, response, action));
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        out.print("Error processing request: " + e.getMessage());
                    }
                } else {
                    throw new ServletException("Acción " + entity + " no válida");
                }
            } else {
                throw new ServletException("Formato de acción no válido");
            }
        } else {
            throw new ServletException("Acción no especificada");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public static String getBody(HttpServletRequest request) {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            return "";
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    // handle exception
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    private class Client {
        public String Usuario;
    }

}
