import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Timer;

@WebListener
public class Listener implements ServletContextListener {

    private String hostAddress = "http://localhost:9000/capteurs";

    @Override
    public final void contextInitialized(final ServletContextEvent sce) {
        System.out.println("Server started");

        Timer timer = new Timer();
        timer.schedule(new CapteurSimilator(hostAddress), 0,10000);
    }

    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {
        System.out.println("Destroyed");
    }
}