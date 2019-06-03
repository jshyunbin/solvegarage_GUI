package scene.main;

import javafx.scene.*;
import javafx.scene.layout.Pane;

import java.util.HashMap;

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

    public void addScreen(String name, Pane pane) {
        screenMap.put(name, pane);
    }

    public void removeScreen(String name) {
        screenMap.remove(name);
    }

    public void activate(String name) {
        root = screenMap.get(name);
        currentName = name;
    }

    public Node currentScreen() {
        if (currentName.equals("undefined")) return null;
        return root;
    }

    public String getName() {
        return currentName;
    }
}
