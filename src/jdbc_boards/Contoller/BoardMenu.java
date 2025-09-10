package jdbc_boards.Contoller;

import jdbc_boards.model.BoardDAO;
import jdbc_boards.vo.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BoardMenu {
    BoardDAO dao;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public BoardMenu() {
        dao = new BoardDAO();
    }

    public void boardMenu() throws IOException {
        System.out.println("\n메인 메뉴: 1.Create | 2.Read | 3.Update | 4.Delete | 5.Exit");
        System.out.print("메뉴 선택: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(input.readLine());
        } catch (NumberFormatException e1) {
            System.out.println("숫자만 입력하세요.");
        }

        switch (choice) {
            case 1: // Create
                Board row = boardDataInput();
                boolean ack = dao.createBoard(row);
                if (ack) {
                    System.out.println("글이 성공적으로 입력되었습니다.");
                } else {
                    System.out.println("입력 실패, 다시 시도 부탁드립니다.");
                }
                break;

            case 2: // Read
                readMenu();
                break;

            case 3: // Update
                System.out.print("수정할 글 번호 입력: ");
                int ubno = Integer.parseInt(input.readLine());
                Board updateBoard = dao.selectOne(ubno);
                if (updateBoard != null) {
                    System.out.print("새 제목: ");
                    updateBoard.setBtitle(input.readLine());
                    System.out.print("새 내용: ");
                    updateBoard.setBcontent(input.readLine());
                    if (dao.updateBoard(updateBoard)) {
                        System.out.println("수정 성공!");
                    } else {
                        System.out.println("수정 실패.");
                    }
                } else {
                    System.out.println("글을 찾을 수 없습니다.");
                }
                break;

            case 4: // Delete
                System.out.print("삭제할 글 번호 입력: ");
                int dbno = Integer.parseInt(input.readLine());
                if (dao.deleteBoard(dbno)) {
                    System.out.println("삭제 성공!");
                } else {
                    System.out.println("삭제 실패.");
                }
                break;

            case 5: // Exit
                System.out.println("프로그램 종료");
                System.exit(0);
                break;
        }
    }

    private void readMenu() throws IOException {
        System.out.println("조회 메뉴: 1.전체 글 | 2.글 번호로 조회");
        int ch = Integer.parseInt(input.readLine());
        if (ch == 1) {
            List<Board> list = dao.selectAll();
            for (Board b : list) {
                System.out.println(b);
                System.out.println("-------------------------");
            }
        } else if (ch == 2) {
            System.out.print("조회할 글 번호: ");
            int bno = Integer.parseInt(input.readLine());
            Board b = dao.selectOne(bno);
            if (b != null) {
                System.out.println(b);
            } else {
                System.out.println("글이 존재하지 않습니다.");
            }
        }
    }

    public Board boardDataInput() throws IOException {
        Board board = new Board();
        System.out.println("새로운 글 입력");
        System.out.print("제목 입력: ");
        board.setBtitle(input.readLine());
        System.out.print("내용 입력: ");
        board.setBcontent(input.readLine());
        System.out.print("작성자 입력: ");
        board.setBwriter(input.readLine());
        return board;
    }
}
