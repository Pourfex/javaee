package LoginService;

import MiddleWares.DatabaseMiddleWare;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ConnectForm {

    private static final String CHAMP_USERNAME  = "username";
    private static final String CHAMP_PASSWORD   = "password";
    private static final String CHAMP_INCORRECT   = "incorrect";


    private Map<String, String> errors = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public User getUser( HttpServletRequest request ) {
        String username = getValeurChamp( request, CHAMP_USERNAME );
        String password = getValeurChamp( request, CHAMP_PASSWORD );

        User user = new User();

        try {
            validateUsername( username );
        } catch ( Exception e ) {
            setErreur( CHAMP_USERNAME, e.getMessage() );
        }
        user.setUsername( username );

        try {
            validatePassword( password);
        } catch ( Exception e ) {
            setErreur( CHAMP_PASSWORD, e.getMessage() );
        }
        user.setPassword( password );

        boolean correct=false;
        try {
            correct = userInDb( user);
        } catch ( Exception e ) {
            setErreur( CHAMP_INCORRECT, e.getMessage() );
        }

        user.setCorrect(correct);

        return user;
    }

    private boolean userInDb(User user) throws Exception {


        DatabaseMiddleWare databaseMiddleWare = new DatabaseMiddleWare();

        if(databaseMiddleWare.userInDb(user.getUsername(), user.getPassword())){
            return true;
        }else{
            throw new Exception( "Identifiants incorrects" );
        }
    }

    private void validateUsername( String username ) throws Exception {
        if ( username != null ) {
            //do nothing
        } else {
            throw new Exception( "Merci de saisir une adresse mail." );
        }
    }

    private void validatePassword( String password) throws Exception {
        if ( password != null) {
            if ( password.length() < 3 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
            }else{
                //do nothing
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setErreur( String champ, String message ) {
        errors.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String value = request.getParameter( nomChamp );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value.trim();
        }
    }


}
