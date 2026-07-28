#!/bin/bash
echo "--- Iniciando ProjectWizard ---"

# Garante que estamos usando o Java 21 LTS para a execução
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

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
