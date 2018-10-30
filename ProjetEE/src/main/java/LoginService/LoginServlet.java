package LoginService;

import MiddleWares.DatabaseMiddleWare;
import Model.Capteur2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
        public static final String ATT_USER = "user";
        public static final String ATT_FORM = "form";
        public static final String ATT_CAPTEURS = "capteurs";
        public static final String VUE_LOGIN = "/WEB-INF/login.jsp";
        public static final String VUE_ACCEUIL = "/WEB-INF/home.jsp";

        public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
            /* Affichage de la page d'inscription */
            this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
        }

        public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
            /* Préparation de l'objet formulaire */
            ConnectForm form = new ConnectForm();

            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            User user = form.getUser(request);

            if(user.isCorrect() && form.getErrors().isEmpty()){

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

                /* Stockage du formulaire et du bean dans l'objet request */
                request.setAttribute( ATT_FORM, form );
                request.setAttribute( ATT_USER, user );

                this.getServletContext().getRequestDispatcher( VUE_ACCEUIL ).forward( request, response );
            }else{
                request.setAttribute( ATT_FORM, form );
                this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
            }


        }
}

