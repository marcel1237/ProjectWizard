/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.rule;

/**
 * Componente público { CloudResolutionException}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class CloudResolutionException extends RuntimeException {
    public CloudResolutionException(String message) {
        super(message);
    }
}
