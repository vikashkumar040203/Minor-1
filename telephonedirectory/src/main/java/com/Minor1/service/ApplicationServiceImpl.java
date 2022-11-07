package com.Minor1.service;

import com.Minor1.dto.Directory;
import com.Minor1.utility.Utility;

import java.io.IOException;

public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public void saveDetails(String fullName, String companyName, String phone, String email) throws IOException {
        fullName = fullName.isEmpty() ? "No Name" : fullName.trim();
        companyName = companyName.isEmpty() ? null : companyName.trim();
        phone = phone.isEmpty() ? null : phone.trim();
        email = email.isEmpty() ? null : email.trim();
        Integer id = TelPhoneDirectory.list == null ? 1 : TelPhoneDirectory.list.size() + 1;
        String record = id + "," + fullName + "," + companyName + "," + phone + "," + email;
        Utility.appendToFile(record);
        Directory dir = new Directory(fullName, companyName, phone, email);
        TelPhoneDirectory.insertContact(dir);
    }
}
