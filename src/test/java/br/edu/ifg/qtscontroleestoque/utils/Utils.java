package br.edu.ifg.qtscontroleestoque.utils;

import java.util.Random;

public class Utils {
    public static String gerarEmailAleatorio() {
        return gerarStringAleatoria() + "@" + gerarStringAleatoria() + ".com";
    }

    public static String gerarStringAleatoria() {
        // Determia as letras que poderão estar presente nas chaves
        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";

        Random random = new Random();

        String str = "";
        int index = -1;
        for( int i = 0; i < 5; i++ ) {
            index = random.nextInt( letras.length() );
            str += letras.substring( index, index + 1 );
        }

        return str;
    }
}
