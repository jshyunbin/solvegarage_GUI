package src.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.Pane;

import java.util.HashMap;

/**
 *  Utility class for controlling multiple scenes
 *
 */

public class MultiScreen { // control screen to switch through panes

    private HashMap<String, Node> screenMap; // saves several panes to switch from
    private Node root;    // current screen(pane)
    String currentName;     // current screen(pane) name

    public MultiScreen() {
        screenMap = new HashMap<>();
        root = new Parent() {
        };
        currentName = "undefined";
    }

    /**
     * Constructor for setting n number of scenes as 'emptyScreen'
     *
     * @param s name of each scene
     */
    public MultiScreen(String[] s){

        Parent emptyScreen;
        try{
            emptyScreen = FXMLLoader.load(getClass().getResource("../../FXMLs/emptyScreen.fxml"));
        } catch(Exception e) {
            System.out.println("No file named \"../../FXMLs/emptyScreen.fxml\"");
            return;
        }
        screenMap = new HashMap<>();
        for (String temp : s) {
            screenMap.put(temp, emptyScreen);
        }
        currentName = s[0];
        root = screenMap.get(s[0]);
    }

    /**
     * Adds a scene to the instance
     * Replaces the scene if there exists a scene with the same name
     *
     * @param name name of the scene you want to add
     * @param pane the scene you want to add
     */
    public void addScreen(String name, Node pane) {
        if (screenMap.get(name) != null) {
            screenMap.remove(name);
        }
        screenMap.put(name, pane);
    }

    /**
     * Removes a particular scene
     *
     * @param name the name of the scene you want to remove
     */
    public void removeScreen(String name) {
        screenMap.remove(name);
    }

    /**
     * Activates a particular scene
     *
     * @param name the name of the scene you want to activate
     */
    public void activate(String name) {
        root = screenMap.get(name);
        currentName = name;
    }

    /**
     * Returns the current Scene
     *
     * @return returns the current activated scene
     */
    public Node currentScreen() {
        if (currentName.equals("undefined")) return null;
        return root;
    }

    /**
     *
     * @return returns the current scene name
     */
    public String getName() {
        return currentName;
    }
}
