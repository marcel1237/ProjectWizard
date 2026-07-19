package com.projectwizard.view.editor;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import javafx.concurrent.Task;
import javafx.scene.layout.BorderPane;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import com.projectwizard.service.editor.JetBrainsSyntaxScanner;

/**
 * EditorPane com Realce de Sintaxe Industrial.
 */
public class EditorPane extends BorderPane {

    private static final long MAX_FILE_SIZE = 1024 * 1024; // 1MB

    private final CodeArea codeArea = new CodeArea();
    private final JetBrainsSyntaxScanner scanner = new JetBrainsSyntaxScanner();
    private File currentFile;

    public EditorPane(File file, String content) {
        this.currentFile = file;

        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.replaceText(content);

        if (file != null && file.length() > MAX_FILE_SIZE) {
            System.err.println("[JDT] Arquivo muito grande para realce.");
            setCenter(new VirtualizedScrollPane<>(codeArea));
            return;
        }

        // Listener assíncrono para manter a UI fluida
        codeArea.multiPlainChanges().subscribe(ignore -> runHighlightingAsync());

        // Realce da linha atual
        codeArea.setParagraphStyle(0, Collections.singleton("current-line"));
        codeArea.caretPositionProperty().addListener((obs, oldPos, newPos) -> {
            int oldLine = codeArea.offsetToPosition(oldPos, CodeArea.Bias.Forward).getMajor();
            int newLine = codeArea.offsetToPosition(newPos, CodeArea.Bias.Forward).getMajor();
            if (oldLine != newLine) {
                codeArea.setParagraphStyle(oldLine, Collections.emptyList());
                codeArea.setParagraphStyle(newLine, Collections.singleton("current-line"));
            }
        });

        runHighlightingAsync();
        setCenter(new VirtualizedScrollPane<>(codeArea));
    }

    private void runHighlightingAsync() {
        String text = codeArea.getText();

        Task<StyleSpans<Collection<String>>> highlightTask = new Task<>() {
            @Override
            protected StyleSpans<Collection<String>> call() {
                return computeHighlighting(text);
            }
        };

        highlightTask.setOnSucceeded(
                e -> codeArea.setStyleSpans(0, highlightTask.getValue()));

        Thread thread = new Thread(highlightTask);
        thread.setDaemon(true);
        thread.start();
    }

    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        StyleSpansBuilder<Collection<String>> spansBuilder =
                new StyleSpansBuilder<>();

        final int[] lastKwEnd = { 0 };

        String processableText = text.length() > 50000
                ? text.substring(0, 50000)
                : text;

        String fileName = currentFile != null ? currentFile.getName() : "Main.java";

        scanner.tokenize(processableText, fileName, (type, start, end) -> {

            int spacer = start - lastKwEnd[0];
            if (spacer > 0) {
                spansBuilder.add(Collections.singleton("token-default"), spacer);
            }

            spansBuilder.add(Collections.singleton(type), end - start);

            lastKwEnd[0] = end;
        });

        int remaining = text.length() - lastKwEnd[0];
        if (remaining > 0) {
            spansBuilder.add(Collections.singleton("token-default"), remaining);
        }

        return spansBuilder.create();
    }

    public String getText() {
        return codeArea.getText();
    }

    public File getFile() {
        return currentFile;
    }
}