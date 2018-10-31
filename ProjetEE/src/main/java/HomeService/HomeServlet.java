package HomeService;

import MiddleWares.DatabaseMiddleWare;
import Model.Capteur2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    public static final String ATT_CAPTEURS = "capteurs";
    public static final String VUE_LOGIN = "/WEB-INF/login.jsp";
    public static final String VUE_ACCEUIL = "/WEB-INF/home.jsp";

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
                /*Affiche page de login */
                this.getServletContext().getRequestDispatcher( VUE_ACCEUIL ).forward( request, response );
            }
        }
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
