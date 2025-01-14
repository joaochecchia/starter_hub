package com.example.StarterHub.infra.util;

import java.util.Base64;

public class DecodeBase64 {

    public byte[] decode(String encodedBase64){
        if(encodedBase64 == null){
            return null;
        }

        return Base64.getDecoder().decode(encodedBase64);
    }

}
