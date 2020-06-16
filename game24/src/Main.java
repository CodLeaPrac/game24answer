import calclatour.Calculator;
import calclatour.Game24;
import game.Game24Gui;


public class Main {

    public static void main(String[] args) {

        new Game24Gui(new Game24(new Calculator()));
    }
}
