package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import valueObject.VLogin;
import valueObject.VUserInfo;

public class MAccount {

    public VUserInfo login(VLogin vLogin) {
        VUserInfo vUserInfo = null;
        try {
            Scanner scanner = new Scanner(new File("account/account"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] tokens = line.split("[ ]");
                if (tokens[0].equals(vLogin.getUserId())) {
                    if (tokens[1].equals(vLogin.getPassword())) {
                        vUserInfo = new VUserInfo();
                        vUserInfo.setName(tokens[2]);
                        break;
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vUserInfo;
    }

    public boolean register(VLogin vLogin, String name) {
        try {
            File file = new File("account/account");
            FileWriter writer = new FileWriter(file, true);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] tokens = line.split("[ ]");
                if (tokens[0].equals(vLogin.getUserId())) {
                    scanner.close();
                    return false; // User ID already exists
                }
            }

            if (file.length() != 0) {
                writer.write(System.lineSeparator()); // Add line separator if file is not empty
            }

            String userData = vLogin.getUserId() + " " + vLogin.getPassword() + " " + name;
            writer.write(userData);
            writer.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true; // Registration successful
    }
}
