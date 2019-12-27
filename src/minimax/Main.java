package minimax;

public class Main {

    public static void main(String[] args) {
        MinimaxFloat minimax = new MinimaxFloat(3);
        System.out.println(minimax);
        minimax.setMin();
        System.out.println(minimax);
        minimax.setMin();
        System.out.println(minimax);
        minimax.setMax();
        System.out.println(minimax);
    }

}
