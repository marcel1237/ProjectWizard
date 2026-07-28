package com.projectwizard.service;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfService {

    public void updateProjectPdf(String projectPath) {
        File root = new File(projectPath);
        if (!root.exists()) return;

        String output = projectPath + File.separator + "Project_Knowledge_Base.pdf";
        
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Project Knowledge Base");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.newLineAtOffset(50, 730);
                contentStream.showText("Generated on: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                contentStream.endText();
            }

            // Simple file listing for now to avoid PDF sizing complexity without a higher-level library
            List<String> extensions = Arrays.asList(".java", ".xml", ".md", ".txt", ".sh", ".css");
            
            Files.walk(Paths.get(projectPath))
                .filter(Files::isRegularFile)
                .filter(p -> extensions.stream().anyMatch(ext -> p.toString().endsWith(ext)))
                .limit(100)
                .forEach(p -> {
                    // In a real industrial app, we would append content to the PDF
                    // For now, we mimic the C++ logic structure
                });

            document.save(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
