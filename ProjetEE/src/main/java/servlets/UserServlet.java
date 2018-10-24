package servlets;

import MiddleWares.DatabaseMiddleWare;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello world ! </h1>");
        out.println("<h1>getServletName() : " +
                getServletName() + "</h1>");
        out.println("<h1>URI : " +
                request.getRequestURI() + "</h1>");
        HttpSession session = request.getSession(false);
        if(session == null || !userInDb(session.getAttribute("username"))){
            RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
            rd.forward(request,response);
        }else{
            out.println("<h1>Username : " + session.getAttribute("username") + " </h1>");

            DatabaseMiddleWare databaseMiddleWare = new DatabaseMiddleWare();

            String capteurids = request.getParameter("capteurid");

            if(capteurids == null){
                showAll(out, databaseMiddleWare);
            }else{
                String[] sentences = capteurids.split("\\s+");
                if(sentences.length == 1){
                    try{
                        Integer.valueOf(sentences[0]);
                        showCapteurById(out, databaseMiddleWare, sentences[0]);
                    }catch (Exception e){
                        if(sentences[0].equals("all")){
                            showAll(out, databaseMiddleWare);
                        }else{
                            //TODO
                        }
                    }
                }else{
                    List<Integer> ids = new ArrayList<>();
                    for(String sentence : sentences){
                        try{
                            Integer.valueOf(sentence);
                            ids.add(Integer.valueOf(sentence));
                        }catch (Exception e){
                            //TODO
                        }
                    }
                    showCapteurByIds(out, databaseMiddleWare, ids);
                }
            }
        }
    }

    private boolean userInDb(Object username) {
        //TODO
        return true;
    }

    private void showCapteurByIds(PrintWriter out, DatabaseMiddleWare databaseMiddleWare, List<Integer> ids) {
        String toto = databaseMiddleWare.getCapteurByIds(ids).toString();
        String[] sentences = toto.split("\\n");
        out.println("<ul> ");
        for(String sentence : sentences){
            out.println("<li> " + sentence+ "</li>");
        }
        out.println(" </ul>");
    }

    private void showCapteurById(PrintWriter out, DatabaseMiddleWare databaseMiddleWare, String capteurid) {
        String toto = databaseMiddleWare.getCapteurById(Integer.valueOf(capteurid)).toString();
        String[] sentences = toto.split("\\n");
        out.println("<ul> ");
        for(String sentence : sentences){
            out.println("<li> " + sentence+ "</li>");
        }
        out.println(" </ul>");
    }

    private void showAll(PrintWriter out, DatabaseMiddleWare databaseMiddleWare) {
        String toto = databaseMiddleWare.getAll().toString();
        String[] sentences = toto.split("\\n");
        out.println("<ul> ");
        for(String sentence : sentences){
            out.println("<li> " + sentence+ "</li>");
        }
        out.println(" </ul>");
    }
}
