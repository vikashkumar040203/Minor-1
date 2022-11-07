package com.Minor1.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Utility {
    private Utility() {
    }

    public static void appendToFile(String record) throws IOException {
        File file = new File (Constants.DIRECTORY_PATH);
        boolean isFileExist = true;
        if (!file.exists()) {
            isFileExist = false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));) {
            if(!isFileExist) {
                bw.write("id,fullName,companyName,phone,email");
            }
            bw.write("\n");
            bw.write(record);
            bw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Exception occured while writing new record to system." + ex.getMessage());
        }

    }

}
