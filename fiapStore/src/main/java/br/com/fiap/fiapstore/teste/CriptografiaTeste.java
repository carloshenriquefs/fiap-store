package br.com.fiap.fiapstore.teste;

import br.com.fiap.fiapstore.util.CriptografiaUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class CriptografiaTeste {

    public static void main(String[] args)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        System.out.println(CriptografiaUtils.criptografar("123456"));
        System.out.println(CriptografiaUtils.criptografar("123456"));
    }
}
