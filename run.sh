#!/bin/bash
echo "--- Iniciando ProjectWizard ---"

# Respeita JAVA_HOME/PATH do ambiente para funcionar em diferentes instalações.
if ! command -v java >/dev/null || ! command -v mvn >/dev/null; then
    echo "❌ Java 21 e Maven precisam estar disponíveis no PATH."
    exit 1
fi

mvn javafx:run

if [ $? -eq 0 ]; then
    echo "---------------------------------------"
    echo "👋 Aplicação finalizada."
    echo "---------------------------------------"
else
    echo "---------------------------------------"
    echo "❌ Erro ao iniciar a aplicação."
    echo "---------------------------------------"
    exit 1
fi
