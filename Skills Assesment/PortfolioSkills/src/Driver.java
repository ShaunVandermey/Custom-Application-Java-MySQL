import com.formdev.flatlaf.FlatLightLaf;

/**Very simple Driver that acts as the start of the program*/
public class Driver {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        LoginScreen login = new LoginScreen();
    }

}
