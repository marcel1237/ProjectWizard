#!/bin/bash
set -euo pipefail

PROJECT_ROOT="$(cd "$(dirname "$0")" && pwd)"

if ! command -v java >/dev/null || ! command -v mvn >/dev/null; then
    echo "Java 21 e Maven precisam estar disponíveis no PATH."
    exit 1
fi

echo "--- Building ProjectWizard ---"
mvn -f "$PROJECT_ROOT/pom.xml" clean verify

echo "--- Building Cloud Wizard ---"
mvn -f "$PROJECT_ROOT/cloudwizard/pom.xml" clean verify

echo "Build completo: ProjectWizard + Cloud Wizard"
