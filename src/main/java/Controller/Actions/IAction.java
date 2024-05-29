package Controller.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IAction {
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response, String action) throws ServletException, IOException;
}