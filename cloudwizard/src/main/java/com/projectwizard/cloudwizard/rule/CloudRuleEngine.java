/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.rule;

import com.projectwizard.cloudwizard.model.CloudProjectSpec;
import com.projectwizard.cloudwizard.model.CloudProvider;
import com.projectwizard.cloudwizard.model.CloudServiceSpec;
import com.projectwizard.cloudwizard.model.ResolutionPlan;
import com.projectwizard.cloudwizard.model.ServiceBinding;
import com.projectwizard.cloudwizard.provider.AwsCloudAdapter;
import com.projectwizard.cloudwizard.provider.CloudProviderAdapter;
import com.projectwizard.cloudwizard.provider.GoogleCloudAdapter;
import com.projectwizard.cloudwizard.provider.AzureCloudAdapter;
import com.projectwizard.cloudwizard.provider.OracleCloudAdapter;
import com.projectwizard.cloudwizard.provider.IbmCloudAdapter;
import com.projectwizard.cloudwizard.provider.AlibabaCloudAdapter;
import com.projectwizard.cloudwizard.provider.TencentCloudAdapter;
import com.projectwizard.cloudwizard.provider.HuaweiCloudAdapter;
import com.projectwizard.cloudwizard.provider.YandexCloudAdapter;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Componente público { CloudRuleEngine}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class CloudRuleEngine {
    private final Map<CloudProvider, CloudProviderAdapter> adapters;

    public CloudRuleEngine(List<CloudProviderAdapter> adapters) {
        this.adapters = new EnumMap<>(CloudProvider.class);
        for (CloudProviderAdapter adapter : adapters) {
            if (adapter.provider() == CloudProvider.AUTO) throw new IllegalArgumentException("AUTO cannot be an adapter");
            if (this.adapters.put(adapter.provider(), adapter) != null) {
                throw new IllegalArgumentException("Duplicate adapter for " + adapter.provider());
            }
        }
    }

    public static CloudRuleEngine standard() {
        List<CloudProviderAdapter> discovered = new ArrayList<>();
        ServiceLoader.load(CloudProviderAdapter.class).forEach(discovered::add);
        if (discovered.stream().noneMatch(adapter -> adapter.provider() == CloudProvider.AWS)) {
            discovered.add(new AwsCloudAdapter());
        }
        if (discovered.stream().noneMatch(adapter -> adapter.provider() == CloudProvider.GOOGLE_CLOUD)) {
            discovered.add(new GoogleCloudAdapter());
        }
        if (discovered.stream().noneMatch(adapter -> adapter.provider() == CloudProvider.MICROSOFT_AZURE)) {
            discovered.add(new AzureCloudAdapter());
        }
        if (discovered.stream().noneMatch(adapter -> adapter.provider() == CloudProvider.ORACLE_CLOUD)) {
            discovered.add(new OracleCloudAdapter());
        }
        if (discovered.stream().noneMatch(adapter -> adapter.provider() == CloudProvider.IBM_CLOUD)) {
            discovered.add(new IbmCloudAdapter());
        }
        if (discovered.stream().noneMatch(adapter -> adapter.provider() == CloudProvider.ALIBABA_CLOUD)) {
            discovered.add(new AlibabaCloudAdapter());
        }
        if (discovered.stream().noneMatch(adapter -> adapter.provider() == CloudProvider.TENCENT_CLOUD)) {
            discovered.add(new TencentCloudAdapter());
        }
        if (discovered.stream().noneMatch(adapter -> adapter.provider() == CloudProvider.HUAWEI_CLOUD)) {
            discovered.add(new HuaweiCloudAdapter());
        }
        if (discovered.stream().noneMatch(adapter -> adapter.provider() == CloudProvider.YANDEX_CLOUD)) {
            discovered.add(new YandexCloudAdapter());
        }
        return new CloudRuleEngine(discovered);
    }

    public ResolutionPlan resolve(CloudProjectSpec project) {
        List<ServiceBinding> bindings = new ArrayList<>();
        List<String> warnings = new ArrayList<>();

        for (CloudServiceSpec service : project.services()) {
            List<CloudProvider> targets = service.provider() == CloudProvider.AUTO
                    ? project.providers()
                    : List.of(service.provider());
            for (CloudProvider provider : targets) {
                CloudProviderAdapter adapter = adapters.get(provider);
                if (adapter == null) {
                    handleMissing(service, provider, warnings, "No adapter registered");
                    continue;
                }
                adapter.resolve(service.capability())
                        .ifPresentOrElse(
                                resolved -> bindings.add(new ServiceBinding(service.name(), resolved)),
                                () -> handleMissing(service, provider, warnings, "Capability is not supported"));
            }
        }
        return new ResolutionPlan(bindings, warnings);
    }

    private void handleMissing(
            CloudServiceSpec service, CloudProvider provider, List<String> warnings, String reason) {
        String message = reason + ": " + provider + " / " + service.capability();
        if (service.required()) throw new CloudResolutionException(message);
        warnings.add(message);
    }
}
