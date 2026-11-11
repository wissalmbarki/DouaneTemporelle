package com.example.douanetemporelle;

public class KeyUtils {
    // Methode static bech nbniw el mfete7 m3a 4 ar9am mte3 el matricule
    public static String buildKey(String matricule4Digits, String keyName) {
        return matricule4Digits + "_" + keyName;
    }
}