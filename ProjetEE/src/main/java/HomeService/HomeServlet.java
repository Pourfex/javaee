package HomeService;

import MiddleWares.DatabaseMiddleWare;
import Model.Capteur2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    public static final String ATT_CAPTEURS = "capteurs";
    public static final String VUE_LOGIN = "/WEB-INF/login.jsp";
    public static final String VUE_ACCEUIL = "/WEB-INF/home.jsp";
    public static final String ATT_CAPTEURS_RB = "capteursGivenByResearchBar";
    public static final String ATT_REQUEST_IDS = "ids";
    public static final String ATT_REQUEST_TAGS = "tags";
    public static final String ATT_REQUEST_TYPES = "types";
    public static final String ATT_REGEX = "g";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false) == null){
            this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
        }else{
            if(request.getSession(false).getAttribute("username") == null){
                this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
            }else{
                giveAllCapteurs(request);
                giveResearchBarCapteurs(request);
                /*Affiche page de login */
                this.getServletContext().getRequestDispatcher( VUE_ACCEUIL ).forward( request, response );
            }
        }
    }

    private void giveResearchBarCapteurs(HttpServletRequest request) {
        DatabaseMiddleWare databaseMiddleWare = new DatabaseMiddleWare();

        String ids = (String) request.getAttribute(ATT_REQUEST_IDS);
        String tags = (String) request.getAttribute(ATT_REQUEST_TAGS);
        String types = (String) request.getAttribute(ATT_REQUEST_TYPES);

        ArrayList<String> ids_result = getStringsInRequestByAttribute(ids);
        ArrayList<String> tags_result= getStringsInRequestByAttribute(tags);
        ArrayList<String> types_result= getStringsInRequestByAttribute(types);

        List<Capteur2> capteurSearchBar = databaseMiddleWare.getAllCapteurByResearchBar(ids_result,tags_result,types_result);
        request.setAttribute(ATT_CAPTEURS_RB,  capteurSearchBar);
    }

    private ArrayList<String> getStringsInRequestByAttribute(String stringAttribute){
        ArrayList<String> result = new ArrayList<>();
        if(stringAttribute == null){
            result.add("all");
        }else{
            String[] resultArray = stringAttribute.split(ATT_REGEX);
            for(String s : result){
                result.add(s);
            }
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    private void giveAllCapteurs(HttpServletRequest request) {
        DatabaseMiddleWare databaseMiddleWare = new DatabaseMiddleWare();

        //////// GET CAPTEUR BY ID EXEMPLE /////////////
                /*String toto = databaseMiddleWare.getAll().toString();
                request.setAttribute( "toto", toto);

                Capteur2 capteur2 = databaseMiddleWare.getCapteur2byId(1);
                request.setAttribute("capteur2", capteur2);*/

        //////// GET CAPTEUR BY IDs EXEMPLE /////////////
                /*ArrayList<Integer> ids = new ArrayList<>();
                ids.add(1);
                ids.add(2);
                List<Capteur2> capteur2s = databaseMiddleWare.getCapteur2byIds(ids);
                request.setAttribute("capteur2s", capteur2s);*/

        List<Capteur2> capteur2ss = databaseMiddleWare.getAllCapteur2();
        request.setAttribute(ATT_CAPTEURS,  capteur2ss);
    }
}
