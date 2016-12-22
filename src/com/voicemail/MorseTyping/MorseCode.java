package com.voicemail.MorseTyping;

/**
 * Created by Sanket Shahane on 08-Mar-16.
 */
public class MorseCode {
    public char characters[];
    public String code[];

    public MorseCode() {
        characters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        code = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..--", "...-", ".--", "-..-", "-.--", "--.."};
    }

    public int ReturnCharacterIndex(String codeToTranslate) {
        int index = -1;
        int i;
        for (i = 0; i < 26; i++) {
            if (code[i].equals(codeToTranslate))
                index = i;
        }
        return index;
    }
}
