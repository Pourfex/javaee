package HomeService;

import MiddleWares.DatabaseMiddleWare;
import Model.Capteur2;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/alertes")
public class AlertesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("capteursdata : "+req.getRequestURI());
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DatabaseMiddleWare databaseMiddleWare = new DatabaseMiddleWare();

        List<Capteur2> capteur2ss = databaseMiddleWare.getAllCapteur2(0);
        System.out.println("Length : " + capteur2ss.size());
        String json = new Gson().toJson(capteur2ss);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        jsonObject.addProperty("data", json);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //process(req,resp);
    }
}
