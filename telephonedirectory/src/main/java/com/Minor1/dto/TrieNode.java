package com.Minor1.dto;

import java.util.HashMap;

public class TrieNode {
    public HashMap<Character, TrieNode> child;
    public Boolean isLast;
    public String fullName;
    public String companyName;
    public String email;
    public String phone;

    public TrieNode() {
        this.child = new HashMap<>();
        this.isLast = false;
    }
}
