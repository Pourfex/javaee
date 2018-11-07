package CapteurService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MiddleWares.DatabaseMiddleWare;
import Model.CapteurdataEntity;
import Utils.ConstanteUtils;

@WebServlet("/capteur")

public class CapteurServlet extends HttpServlet {

    public static final String VUE_CAPTEUR = "/WEB-INF/capteur.jsp";
    public static final String VUE_NO_CAPTEUR = "/WEB-INF/nocapteurspecified.jsp";
    public static final String TIMESTAMPS = "timestamps";
    public static final String VALUES = "values";
    public static final String BEGINDATE = "begindate";
    public static final String TYPE = "type";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession(false) == null || req.getSession(false).getAttribute("username") == null) {
            /* Affichage de la page de connexion */
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);

            return;
        }
        if (req.getParameter("id") == null) {
            req.getRequestDispatcher(VUE_NO_CAPTEUR).forward(req, resp);
            return;
        }
        DatabaseMiddleWare dbm = new DatabaseMiddleWare();
        int id = Integer.parseInt(req.getParameter("id"));

        long offset = 2 * ConstanteUtils.HOUR_MS;

        if (req.getParameter("span") != null) {
            switch (req.getParameter("span")) {
                case "day":
                    offset = ConstanteUtils.DAY_MS;
                    break;
                case "week":
                    offset = ConstanteUtils.WEEK_MS;
                    break;
                case "month":
                    offset = ConstanteUtils.MONTH_MS;
                    break;
            }
        }

        List<CapteurdataEntity> data = dbm.getCapteurDataFromDate(id,
                new Timestamp(System.currentTimeMillis() - offset));
        List<Double> values = data.stream().map(v -> v.getValue()).collect(Collectors.toList());
        List<String> timestamps = data.stream().map(v -> v.getTimestamp().toString()).collect(Collectors.toList());
        req.setAttribute(VALUES, values);
        req.setAttribute(TIMESTAMPS, timestamps);

        String type;

        if (data.size() > 0) {
            switch (data.get(0).getType()) {
                case 1:
                    type = "Température";
                    break;
                case 2:
                    type = "Pression";
                    break;
                case 3:
                    type = "Humidité";
                    break;
                default:
                    type = "Vitesse Vent";
                    break;
            }
        } else {
            type = "";
        }

        req.setAttribute(BEGINDATE, new Timestamp(System.currentTimeMillis() - offset).toString());

        req.setAttribute(TYPE, type);

        req.getRequestDispatcher(VUE_CAPTEUR).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

}