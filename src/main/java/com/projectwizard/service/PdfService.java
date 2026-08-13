/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
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
import java.util.stream.Stream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Componente público { PdfService}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class PdfService {
    private static final Logger LOGGER = Logger.getLogger(PdfService.class.getName());

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
            
            try (Stream<Path> paths = Files.walk(Paths.get(projectPath))) {
                paths
                .filter(Files::isRegularFile)
                .filter(p -> extensions.stream().anyMatch(ext -> p.toString().endsWith(ext)))
                .limit(100)
                .forEach(p -> {
                    // In a real industrial app, we would append content to the PDF
                    // For now, we mimic the C++ logic structure
                });
            }

            document.save(output);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not generate project PDF", e);
        }
    }
}
