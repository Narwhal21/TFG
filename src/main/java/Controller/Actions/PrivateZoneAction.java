package Controller.Actions;

import Model.PrivateZone;
import Model.PrivateZoneDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PrivateZoneAction implements IAction {

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
        PrivateZoneDao privateZoneDao = new PrivateZoneDao();
        ArrayList<PrivateZone> privateZones = privateZoneDao.findAll(null);
        return PrivateZone.toArrayJSon(privateZones);
    }

}
