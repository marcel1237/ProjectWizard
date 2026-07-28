#!/bin/bash
echo "--- Iniciando Build Maven (ProjectWizard) ---"

# Garante que estamos usando o Java 21 LTS para o build
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

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
