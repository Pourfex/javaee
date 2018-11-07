package SearchService;

import MiddleWares.DatabaseMiddleWare;
import Model.Capteur2;
import Model.CapteurdataEntity;
import Utils.ConstanteUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();


        System.out.println("Inside search");

        if(req.getParameter("ids").equals("all") || req.getParameter("ids")==null)
            ids.add("all");
        else {
            ids = (ArrayList<String>) Arrays.asList(req.getParameter("ids").split("\\s*,\\s*"));
        }

        if(req.getParameter("tags").equals("all") || req.getParameter("tags")==null)
            tags.add("all");
        else {
            tags = (ArrayList<String>) Arrays.asList(req.getParameter("tags").split("\\s*,\\s*"));
        }

        if(req.getParameter("types").equals("types") || req.getParameter("types")==null)
            types.add("all");
        else {
            types = (ArrayList<String>) Arrays.asList(req.getParameter("types").split("\\s*,\\s*"));
        }


        DatabaseMiddleWare databaseMiddleWare = new DatabaseMiddleWare();


        System.out.println("Instanciation db mid");

        List<Capteur2> capteurs = databaseMiddleWare.getAllCapteurByResearchBar(ids, tags, types);
        System.out.println("Length : " + capteurs.size());
        String json = new Gson().toJson(capteurs);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        jsonObject.addProperty("data", json);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonObject.toString());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

}