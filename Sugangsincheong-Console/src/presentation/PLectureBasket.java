package presentation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import valueObject.VLecture;

public class PLectureBasket {
    private Vector<VLecture> vLectureVector;

    public PLectureBasket() {
        this.vLectureVector = new Vector<VLecture>();
    }

    public void add(VLecture vLecture) {
        this.vLectureVector.add(vLecture);
    }

    public void show() {
        for (VLecture vLecture : vLectureVector) {
            vLecture.show();
        }
    }

    public void saveToFile() { //미리담기 강좌 저장
        try {
            FileWriter writer = new FileWriter("data/Milidamgi.txt");
            for (VLecture vLecture : vLectureVector) {
                String lectureData = vLecture.getCode() + " " + vLecture.getName() + " " + vLecture.getCredit() + " " + vLecture.getTime() + System.lineSeparator();
                writer.write(lectureData);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveToFile2() { //수강신청 강좌 저장
        try {
            FileWriter writer = new FileWriter("data/sincheong.txt");
            for (VLecture vLecture : vLectureVector) {
                String lectureData = vLecture.getCode() + " " + vLecture.getName() + " " + vLecture.getCredit() + " " + vLecture.getTime() + System.lineSeparator();
                writer.write(lectureData);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getTotalCredit() { //신청한 총 학점 계산
        int totalCredit = 0;
        for (VLecture vLecture : vLectureVector) {
            totalCredit += vLecture.getCredit();
        }
        return totalCredit;
    }
    
    public void remove(VLecture vLecture) { //18학점을 넘게 담으면 선택한 강좌 삭제
        this.vLectureVector.remove(vLecture);
    }

    public boolean containsCode(int code) {
        for (VLecture vLecture : vLectureVector) {
            if (vLecture.getCode() == code) {
                return true;
            }
        }
        return false;
    }


}

