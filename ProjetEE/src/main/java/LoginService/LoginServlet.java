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
        public static final String VUE_LOGIN = "/WEB-INF/login.jsp";
        public static final String SERVLET_HOME = "/home";


        public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

            if(request.getSession(false) == null){
                /* Affichage de la page de connexion */
                this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
            }else if(request.getSession(false).getAttribute("username") == null){
                /* Affichage de la page de connexion */
                this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
            }else{
                this.getServletContext().getRequestDispatcher(SERVLET_HOME).forward(request,response);
                //TODO
            }


        }



    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

                /* Préparation de l'objet formulaire */
                ConnectForm form = new ConnectForm();

                /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
                User user = form.getUser(request);

                if(user.isCorrect() && form.getErrors().isEmpty()){
                    request.getSession(true);
                    request.getSession().setAttribute("username", request.getParameter("username"));
                    this.getServletContext().getRequestDispatcher(SERVLET_HOME).forward(request,response);
                   //TODO
                }else{
                    request.getSession().invalidate(); //TODO Useless ?

                    /* Stockage du formulaire et du bean dans l'objet request */
                    request.setAttribute( ATT_FORM, form );
                    this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
                }
        }
}

