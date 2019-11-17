package br.edu.ifg.qtscontroleestoque.utils;

import java.util.Random;

public class Utils {
    public static String gerarEmailAleatorio() {
        // Determia as letras que poderão estar presente nas chaves
        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";

        Random random = new Random();

        String email = "";
        int index = -1;
        for( int i = 0; i < 5; i++ ) {
            index = random.nextInt( letras.length() );
            email += letras.substring( index, index + 1 );
        }

        return email + "@" + email + ".com";
    }
}
