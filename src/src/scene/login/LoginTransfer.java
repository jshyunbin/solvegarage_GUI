package src.scene.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.main.Main;

import java.io.IOException;

/**
 *  A Runnable class for transfer with server
 *  posts user id and password to server
 */

public class LoginTransfer extends Thread {

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("loginPage");
            while(Main.stage.isShowing()) {
                ;
            }
            notify();
        }
    }
}
