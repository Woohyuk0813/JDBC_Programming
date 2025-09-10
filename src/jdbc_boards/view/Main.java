package jdbc_boards.view;

import jdbc_boards.Contoller.BoardMenu;

public class Main {
    public static void main(String[] args) {
        BoardMenu menu = new BoardMenu();
        while (true) {
            try {
                menu.boardMenu();
            } catch (Exception e) {
                System.out.println("오류 발생 : " + e.getMessage());
            }
        }
    }
}
