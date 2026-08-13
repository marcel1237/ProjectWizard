/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.application.Platform;

final class FxTestSupport {
    private static final AtomicBoolean STARTED = new AtomicBoolean();

    private FxTestSupport() { }

    static void startToolkit() throws Exception {
        if (STARTED.compareAndSet(false, true)) {
            CompletableFuture<Void> started = new CompletableFuture<>();
            Platform.startup(() -> started.complete(null));
            started.get(10, TimeUnit.SECONDS);
        }
    }

    static <T> T onFxThread(Callable<T> action) throws Exception {
        CompletableFuture<T> result = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                result.complete(action.call());
            } catch (Throwable exception) {
                result.completeExceptionally(exception);
            }
        });
        return result.get(10, TimeUnit.SECONDS);
    }
}
