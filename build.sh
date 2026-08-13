#!/bin/bash
echo "--- Iniciando Build Maven (ProjectWizard) ---"

# Respeita JAVA_HOME/PATH do ambiente para funcionar em Linux, macOS e Windows/WSL.
if ! command -v java >/dev/null || ! command -v mvn >/dev/null; then
    echo "❌ Java 21 e Maven precisam estar disponíveis no PATH."
    exit 1
fi

mvn clean compile

if [ $? -eq 0 ]; then
    echo "---------------------------------------"
    echo "✅ Build finalizado com sucesso!"
    echo "---------------------------------------"
else
    echo "---------------------------------------"
    echo "❌ Erro durante a compilação Maven."
    echo "---------------------------------------"
    exit 1
fi
