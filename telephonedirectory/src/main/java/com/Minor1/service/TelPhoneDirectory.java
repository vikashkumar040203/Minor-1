package com.Minor1.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.Minor1.dto.Directory;
import com.Minor1.dto.TrieNode;
import com.Minor1.utility.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TelPhoneDirectory {
    static TrieNode root = new TrieNode();
    static List<Directory> list = null;

    static {
        try {
            initializeDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initializeDirectory() throws IOException {
        File file = new File(Constants.DIRECTORY_PATH);
        if (!file.exists()) {
            return;
        }
        try (InputStream inputFS = new FileInputStream(file)) {
            MappingIterator<Directory> listItr = new CsvMapper().readerWithTypedSchemaFor(Directory.class).readValues(inputFS);
            listItr.next();
            list = listItr.readAll();
            insertIntoTrie(list);
        } catch (IOException e) {
            throw new IOException("Some Problem occurs while initializing directory." + e.getMessage());
        }
    }

    public static void insertIntoTrie(List<Directory> directoryList) {
        int length = directoryList.size();
        for (int i = 0; i < length; i++) {
            insertContact(directoryList.get(i));
        }
    }

    public static void insertContact(Directory directory) {
        TrieNode temp = root;
        char[] ch = directory.getFullName().toLowerCase().toCharArray();
        for (int i = 0; i < ch.length; i++) {
            TrieNode dir = temp.child.get(ch[i]);
            if (dir == null) {
                dir = new TrieNode();
                temp.child.put(ch[i], dir);
            }
            temp = dir;
            if (i == ch.length - 1) { //TODO
                temp.isLast = true;
                temp.companyName = directory.getCompanyName();
                temp.phone = directory.getPhone();
                temp.fullName = directory.getFullName();
                temp.email = directory.getEmail();
            }
        }
    }

    public List<Directory> queryResults(String query) {
        TrieNode temp = root;
        char[] ch = query.toLowerCase().toCharArray();
        for (int i = 0; i < ch.length; i++) {
            TrieNode currNode = temp.child.get(ch[i]);
            if (currNode == null) {
//                System.out.println("No Contacts available that starts with : " + query);
                return null;
            }
            temp = currNode;
        }
//        System.out.println("Contacts available that starts with " + query + " : ");
        List<Directory> result = new ArrayList<>();
        printQueryResults(temp, query, result);
        return result;
    }

    public void printQueryResults(TrieNode currNode, String prefix, List<Directory> result) {
        if (currNode.isLast) {
            result.add(new Directory(currNode.fullName, currNode.companyName, currNode.phone, currNode.email));
        }
        for (char ch = ' '; ch <= 'z'; ch++) {
            TrieNode node = currNode.child.get(ch);
            if (node != null) {
                printQueryResults(node, prefix + ch, result);
            }
        }
    }
}
