package Controller.Actions;

import Model.Pedido;
import Model.PedidoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PedidoAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {

        String strReturn = "";
        switch (action) {
            case "FIND_ALL":
                strReturn = findAll();
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        PedidoDao pedidoDao = new PedidoDao();
        ArrayList<Pedido> pedidos = pedidoDao.findAll(null);
        return Pedido.toArrayJSon(pedidos);
    }
}
