package FilterSettingsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/settings")
public class FilterSettingsServlet extends HttpServlet {

    public static final String VUE_FILTER_SETTINGS= "/WEB-INF/filtersettings.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("seuil_temperature", 52);
        req.setAttribute("seuil_pression", 6);
        req.setAttribute("seuil_humidite", 79);
        req.setAttribute("seuil_vitesse_vent", 92);

        req.getRequestDispatcher(VUE_FILTER_SETTINGS).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }
}
