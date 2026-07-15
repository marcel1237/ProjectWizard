package com.projectwizard.service.editor;

import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.internal.compiler.parser.Scanner;

@SuppressWarnings("restriction")
public class JavaTokenScanner {
    private final Scanner scanner = new Scanner();

    public void tokenize(String source, TokenHandler handler) {
        if (source == null) return;
        
        // Define a fonte para análise
        scanner.setSource(source.toCharArray());
        
        try {
            int token;
            // Loop principal de tokenização do JDT Core
            while ((token = scanner.getNextToken()) != ITerminalSymbols.TokenNameEOF) {
                int start = scanner.getCurrentTokenStartPosition();
                int end = scanner.getCurrentTokenEndPosition();
                String type = getStyleClass(token);
                handler.onToken(type, start, end + 1);
            }
        } catch (Exception e) {
            System.err.println("[JDT] Erro na tokenização: " + e.getMessage());
        } finally {
            // OTIMIZAÇÃO: Limpa a referência do array de caracteres no scanner interno
            // Isso ajuda o Garbage Collector (G1GC) a liberar memória pesada [18.4]
            scanner.setSource((char[]) null);
        }
    }

    private String getStyleClass(int token) {
        return switch (token) {
            case ITerminalSymbols.TokenNameIdentifier -> "token-identifier";
            case ITerminalSymbols.TokenNameStringLiteral -> "token-string";
            case ITerminalSymbols.TokenNameCOMMENT_BLOCK, ITerminalSymbols.TokenNameCOMMENT_LINE -> "token-comment";
            case ITerminalSymbols.TokenNameint, ITerminalSymbols.TokenNamepublic, ITerminalSymbols.TokenNameclass, 
                 ITerminalSymbols.TokenNamestatic, ITerminalSymbols.TokenNamevoid, ITerminalSymbols.TokenNamereturn,
                 ITerminalSymbols.TokenNameprivate, ITerminalSymbols.TokenNameprotected, ITerminalSymbols.TokenNameif,
                 ITerminalSymbols.TokenNameelse, ITerminalSymbols.TokenNamefor, ITerminalSymbols.TokenNamewhile,
                 ITerminalSymbols.TokenNametry, ITerminalSymbols.TokenNamecatch, ITerminalSymbols.TokenNamefinally,
                 ITerminalSymbols.TokenNamenew, ITerminalSymbols.TokenNameimport, ITerminalSymbols.TokenNamepackage -> "token-keyword";
            default -> "token-default";
        };
    }

    public interface TokenHandler {
        void onToken(String type, int start, int end);
    }
}
