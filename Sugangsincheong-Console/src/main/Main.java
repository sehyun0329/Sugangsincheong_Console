package main;

import java.util.Scanner;

import presentation.PLogin;
import presentation.PSugangsincheong;
import valueObject.VUserInfo;

public class Main {
    private Scanner keyboard;
    private PLogin pLogin;
    private PSugangsincheong pSugangsincheong;

    public Main() {
        keyboard = new Scanner(System.in);
        pLogin = new PLogin();
        pSugangsincheong = new PSugangsincheong();
    }

    private void run() {
        boolean isLoggedIn = false;
        VUserInfo vUserInfo = null;

        while (!isLoggedIn) {
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 사용자 정보 찾기");
            System.out.println("0. 종료");
            System.out.print("메뉴를 선택하세요: ");
            String choiceStr = keyboard.nextLine();
            int choice = Integer.parseInt(choiceStr);

            switch (choice) {
                case 1:
                    vUserInfo = pLogin.login(keyboard);
                    if (vUserInfo != null) {
                        isLoggedIn = true;
                    }
                    break;
                case 2:
                    pLogin.signUp(keyboard);
                    break;
                case 3:
                    pLogin.findUser(keyboard);
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    keyboard.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
                    break;
            }
        }

        pSugangsincheong.run(vUserInfo, keyboard);
        keyboard.close();
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
