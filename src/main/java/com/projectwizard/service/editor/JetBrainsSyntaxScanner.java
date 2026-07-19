package com.projectwizard.service.editor;

import com.intellij.lang.java.lexer.JavaLexer;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.XmlLexer;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.tree.IElementType;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JetBrainsSyntaxScanner - Realce de sintaxe profissional e resiliente.
 * Suporta Java, XML/FXML, Markdown (.md), Shell Script (.sh), JS/TS e CSS.
 */
public class JetBrainsSyntaxScanner {

    private static final Set<String> JAVA_KEYWORDS = new HashSet<>(Arrays.asList(
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
        "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
        "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
        "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
        "volatile", "while", "true", "false", "null", "var", "record", "yield", "sealed", "non-sealed", "permits"
    ));

    private static final Set<String> SH_KEYWORDS = new HashSet<>(Arrays.asList(
        "if", "then", "else", "elif", "fi", "for", "in", "do", "done", "while", "until", "case", "esac", "function", "select", "time"
    ));

    private static final Set<String> JS_KEYWORDS = new HashSet<>(Arrays.asList(
        "async", "await", "break", "case", "catch", "class", "const", "continue", "debugger", "default", "delete", "do",
        "else", "enum", "export", "extends", "false", "finally", "for", "function", "if", "import", "in", "instanceof",
        "new", "null", "return", "super", "switch", "this", "throw", "true", "try", "typeof", "var", "void", "while",
        "with", "yield", "let", "static", "of", "get", "set"
    ));

    // Regex para Markdown
    private static final Pattern MD_PATTERN = Pattern.compile(
            "(?<HEADER>^(#+)(.*)$)" +
            "|(?<CODEBLOCK>```[\\s\\S]*?```|`[^`\\n]+`)" +
            "|(?<BOLD>\\*\\*.*?\\*\\*|__.*?__)" +
            "|(?<ITALIC>\\*.*?\\*|_.*?_)" +
            "|(?<LINK>\\[.*?\\]\\(.*?\\))" +
            "|(?<LIST>^[ \\t]*[*+-][ \\t]+.*$)" +
            "|(?<QUOTE>^[ \\t]*>.*$)",
            Pattern.MULTILINE
    );

    // Regex para Shell Script
    private static final Pattern SH_PATTERN = Pattern.compile(
            "(?<COMMENT>#.*)" +
            "|(?<STRING>\"(\\\\.|[^\"])*\"|'[^']*')" +
            "|(?<VARIABLE>\\$([a-zA-Z_][a-zA-Z0-9_]*|\\{[^}]+\\}|[0-9]))" +
            "|(?<KEYWORD>\\b(" + String.join("|", SH_KEYWORDS) + ")\\b)" +
            "|(?<COMMANDSUB>\\$\\([^)]+\\)|`[^`]+`)"
    );

    // Regex para JavaScript / TypeScript
    private static final Pattern JS_PATTERN = Pattern.compile(
            "(?<COMMENT>//.*|/\\*[\\s\\S]*?\\*/)" +
            "|(?<STRING>\"(\\\\.|[^\"])*\"|'(\\\\.|[^'])*'|`(\\\\.|[^`])*`)" +
            "|(?<KEYWORD>\\b(" + String.join("|", JS_KEYWORDS) + ")\\b)" +
            "|(?<NUMBER>\\b\\d+(\\.\\d+)?\\b)" +
            "|(?<ANNOTATION>@[a-zA-Z_][a-zA-Z0-9_]*)"
    );

    // Regex para CSS
    private static final Pattern CSS_PATTERN = Pattern.compile(
            "(?<COMMENT>/\\*[\\s\\S]*?\\*/)" +
            "|(?<SELECTOR>(^|\\})[^{]+(?=\\{))" +
            "|(?<PROPERTY>[a-zA-Z\\- ]+(?=:))" +
            "|(?<VALUE>:[^;\\}]+)" +
            "|(?<BRACE>[\\{\\}])"
    );

    public void tokenize(String source, String fileName, TokenHandler handler) {
        if (source == null || source.isEmpty()) return;

        if (fileName != null) {
            String lowerName = fileName.toLowerCase();
            if (lowerName.endsWith(".md")) {
                tokenizeByPattern(source, MD_PATTERN, handler, "md");
                return;
            }
            if (lowerName.endsWith(".sh") || lowerName.endsWith(".bash")) {
                tokenizeByPattern(source, SH_PATTERN, handler, "sh");
                return;
            }
            if (lowerName.endsWith(".js") || lowerName.endsWith(".ts") || lowerName.endsWith(".jsx") || lowerName.endsWith(".tsx")) {
                tokenizeByPattern(source, JS_PATTERN, handler, "js");
                return;
            }
            if (lowerName.endsWith(".css") || lowerName.endsWith(".scss")) {
                tokenizeByPattern(source, CSS_PATTERN, handler, "css");
                return;
            }
        }

        Lexer lexer = getLexerForFile(fileName);
        lexer.start(source);

        while (lexer.getTokenType() != null) {
            IElementType type = lexer.getTokenType();
            int start = lexer.getTokenStart();
            int end = lexer.getTokenEnd();

            String styleClass = mapTypeToStyle(type, fileName);
            handler.onToken(styleClass, start, end);

            lexer.advance();
        }
    }

    private void tokenizeByPattern(String source, Pattern pattern, TokenHandler handler, String lang) {
        Matcher matcher = pattern.matcher(source);
        int lastKwEnd = 0;
        while (matcher.find()) {
            String styleClass = "token-default";
            
            if (lang.equals("md")) {
                if (matcher.group("HEADER") != null) styleClass = "token-md-header";
                else if (matcher.group("CODEBLOCK") != null) styleClass = "token-md-code";
                else if (matcher.group("BOLD") != null) styleClass = "token-md-bold";
                else if (matcher.group("ITALIC") != null) styleClass = "token-md-italic";
                else if (matcher.group("LINK") != null) styleClass = "token-md-link";
                else if (matcher.group("LIST") != null) styleClass = "token-md-list";
                else if (matcher.group("QUOTE") != null) styleClass = "token-md-quote";
            } else if (lang.equals("sh")) {
                if (matcher.group("COMMENT") != null) styleClass = "token-comment";
                else if (matcher.group("STRING") != null) styleClass = "token-string";
                else if (matcher.group("VARIABLE") != null) styleClass = "token-sh-var";
                else if (matcher.group("KEYWORD") != null) styleClass = "token-keyword";
                else if (matcher.group("COMMANDSUB") != null) styleClass = "token-sh-cmd";
            } else if (lang.equals("js")) {
                if (matcher.group("COMMENT") != null) styleClass = "token-comment";
                else if (matcher.group("STRING") != null) styleClass = "token-string";
                else if (matcher.group("KEYWORD") != null) styleClass = "token-keyword";
                else if (matcher.group("NUMBER") != null) styleClass = "token-number";
                else if (matcher.group("ANNOTATION") != null) styleClass = "token-annotation";
            } else if (lang.equals("css")) {
                if (matcher.group("COMMENT") != null) styleClass = "token-comment";
                else if (matcher.group("SELECTOR") != null) styleClass = "token-tag"; // Seletor como tag
                else if (matcher.group("PROPERTY") != null) styleClass = "token-attribute"; // Propriedade como atributo
                else if (matcher.group("VALUE") != null) styleClass = "token-string"; // Valor como string
                else if (matcher.group("BRACE") != null) styleClass = "token-punctuation";
            }

            if (matcher.start() > lastKwEnd) {
                handler.onToken("token-default", lastKwEnd, matcher.start());
            }
            handler.onToken(styleClass, matcher.start(), matcher.end());
            lastKwEnd = matcher.end();
        }
        if (source.length() > lastKwEnd) {
            handler.onToken("token-default", lastKwEnd, source.length());
        }
    }

    private Lexer getLexerForFile(String fileName) {
        if (fileName != null && (fileName.endsWith(".xml") || fileName.endsWith(".fxml"))) {
            return new XmlLexer();
        }
        return new JavaLexer(LanguageLevel.JDK_21);
    }

    private String mapTypeToStyle(IElementType type, String fileName) {
        String name = type.toString().toUpperCase();

        if (fileName != null && (fileName.endsWith(".xml") || fileName.endsWith(".fxml"))) {
            if (name.contains("TAG_NAME") || name.contains("XML_NAME")) return "token-tag";
            if (name.contains("ATTRIBUTE_NAME")) return "token-attribute";
            if (name.contains("ATTRIBUTE_VALUE") || name.contains("STRING") || name.contains("XML_CHAR_ENTITY")) return "token-string";
            if (name.contains("COMMENT")) return "token-comment";
            if (name.contains("TAG_CHAR") || name.contains("START_TAG") || name.contains("END_TAG") || 
                name.contains("XML_EQ") || name.contains("XML_PI") || name.contains("DOCTYPE") || name.contains("CONDITIONAL_SECTION")) return "token-punctuation";
            return "token-default";
        }

        String lowerName = type.toString().toLowerCase();
        if (JAVA_KEYWORDS.contains(lowerName) || name.contains("KEYWORD")) return "token-keyword";
        if (name.contains("COMMENT")) return "token-comment";
        if (name.contains("STRING") || name.contains("CHAR_LITERAL")) return "token-string";
        if (name.contains("NUMBER") || (name.contains("LITERAL") && (name.contains("INT") || name.contains("FLOAT") || name.contains("DOUBLE") || name.contains("LONG")))) return "token-number";
        if (name.equals("@") || name.contains("AT")) return "token-annotation";
        
        if (name.contains("EQ") || name.contains("GT") || name.contains("LT") || 
            name.contains("PLUS") || name.contains("MINUS") || name.contains("ASTERISK") || 
            name.contains("DIV") || name.contains("OR") || name.contains("AND") || 
            name.contains("PERC") || name.contains("QUEST") || name.contains("COLON") ||
            name.contains("EXCL") || name.contains("TILDE") || name.contains("AMPER") || name.contains("BAR")) return "token-operator";

        if (name.contains("PAREN") || name.contains("BRACE") || name.contains("BRACKET") || 
            name.contains("SEMICOLON") || name.contains("COMMA") || name.contains("DOT")) return "token-punctuation";

        if (name.contains("IDENTIFIER")) return "token-identifier";

        return "token-default";
    }

    public interface TokenHandler {
        void onToken(String type, int start, int end);
    }
}
