package Controller.Actions;

import Model.Pago;
import Model.PagoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PagoAction implements IAction {

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
        PagoDao pagoDao = new PagoDao();
        ArrayList<Pago> pagos = pagoDao.findAll(null);
        return Pago.toArrayJSon(pagos);
    }
}
