package com.projectwizard;

/**
 * Classe de entrada alternativa para evitar problemas com o Java Module System (JPMS).
 * Ao iniciar a aplicação por uma classe que não estende 'Application', o JavaFX
 * permite que dependências não-modulares funcionem corretamente no Classpath.
 */
public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
    }
}
