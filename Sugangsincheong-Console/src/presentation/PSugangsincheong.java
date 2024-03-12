package presentation;

import java.util.Scanner;

import valueObject.VLecture;
import valueObject.VUserInfo;

public class PSugangsincheong {
    private PLectureSelection pLectureSelection;
    private PLectureBasket pMilidamgiBasket;
    private PLectureBasket pSincheongBasket;

    public PSugangsincheong() {
        this.pLectureSelection = new PLectureSelection();
        this.pMilidamgiBasket = new PLectureBasket();
        this.pSincheongBasket = new PLectureBasket();
    }

    public void run(VUserInfo vUserInfo, Scanner keyboard) {
        VLecture vLecture = null;
        boolean pRunning = true;
        while (pRunning) {
            System.out.println("강좌선택 0, 미리담기 1, 수강신청 2, 종료 9");

            String sCode = keyboard.next();
            int iCode = Integer.parseInt(sCode);
            int totalCredit = 0;
            switch (iCode) {
                case 0:
                    vLecture = this.pLectureSelection.selectLecture(vUserInfo, keyboard);
                    break;
                case 1:
                    if (pMilidamgiBasket.containsCode(vLecture.getCode())) {
                        System.out.println("중복된 강좌입니다.");
                    } else {
                        pMilidamgiBasket.add(vLecture);
                        pMilidamgiBasket.saveToFile();
                        pMilidamgiBasket.show();
                    }
                    break;
                case 2:
                    if (pSincheongBasket.containsCode(vLecture.getCode())) {
                        System.out.println("중복된 강좌입니다.");
                    } else {
                        pSincheongBasket.add(vLecture);
                        totalCredit = pSincheongBasket.getTotalCredit();
                        if (totalCredit > 18) {
                            System.out.println("18학점을 초과하셨습니다.");
                            pSincheongBasket.remove(vLecture);
                        } else {
                            pSincheongBasket.saveToFile2();
                            pSincheongBasket.show();
                            System.out.println("수강 신청한 학점: " + totalCredit);
                        }
                    }
                    break;
                case 9:
                    pRunning = false;
                    System.out.println("시스템이 종료되었습니다.");
                    break;
                default:
                    break;
            }
        }
    }
}
