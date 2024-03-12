package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import control.CLogin;
import model.MAccount;
import valueObject.VLogin;
import valueObject.VUserInfo;

public class PLogin {

    public VUserInfo login(Scanner scanner) {
        System.out.println("사용자 이름을 입력하세요: ");
        String userId = scanner.next().trim();
        System.out.println("비밀번호를 입력하세요: ");
        String password = scanner.next().trim();

        VLogin vLogin = new VLogin();
        vLogin.setUserId(userId);
        vLogin.setPassword(password);

        CLogin cLogin = new CLogin();
        VUserInfo vUserInfo = cLogin.login(vLogin);
        if (vUserInfo == null) {
            System.out.println("잘못 입력하였습니다.");
        } else {
            System.out.println(vUserInfo.getName() + "님 안녕하세요");
        }

        return vUserInfo;
    }

    public void signUp(Scanner scanner) {
        System.out.println("사용자 이름을 입력해주세요: ");
        String userId = scanner.next().trim(); // Trim leading/trailing whitespaces
        System.out.println("비밀번호를 입력해주세요: ");
        String password = scanner.next().trim(); // Trim leading/trailing whitespaces
        scanner.nextLine(); // Consume newline character

        System.out.println("이름을 입력해주세요: "); // 이름 입력 받기
        String name = scanner.nextLine().trim(); // Trim leading/trailing whitespaces

        // 회원가입 정보 생성
        VLogin vLogin = new VLogin();
        vLogin.setUserId(userId);
        vLogin.setPassword(password);

        // 회원가입 정보 저장
        MAccount mAccount = new MAccount();
        boolean isRegistered = mAccount.register(vLogin, name);
        if (isRegistered) {
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("중복된 사용자 이름입니다.");
        }
    }
    public void findUser(Scanner scanner) {
        System.out.println("찾기를 원하는 사용자 이름(본명)을 입력하세요: ");
        String searchName = scanner.nextLine().trim();

        try {
            File file = new File("account/account");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] userInfo = line.split(" ");
                String userId = userInfo[0];
                String password = userInfo[1];
                String name = userInfo[2];

                if (name.equals(searchName)) {
                    System.out.println("사용자 이름은 " + userId + "이며 비밀번호는 " + password + "이며 이름은 " + name + "입니다.");
                    fileScanner.close();
                    return;
                }
            }

            fileScanner.close();
            System.out.println("일치하는 사용자를 찾을 수 없습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("사용자 정보 파일을 찾을 수 없습니다.");
        }
    }

}
