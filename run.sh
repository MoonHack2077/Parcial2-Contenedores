#!/bin/bash

# 🚀 Configuración
API_BASE="http://localhost:8080/api/libros"
BACKUP_NAME="respaldo-librosdb-$(date +%F).sql"
NETWORK_NAME="libros_libros_net"
DB_HOST="mariadb"
DB_PORT="3306"
DB_NAME="librosdb"
DB_USER="root"
DB_PASS="root"

echo "🚀 Levantando servicios..."
docker-compose up --build -d

echo "⏳ Esperando a que MariaDB esté lista..."
ATTEMPTS=0
until docker exec mariadb mysqladmin ping -u$DB_USER -p$DB_PASS --silent &>/dev/null || [ $ATTEMPTS -eq 10 ]; do
  echo "🔄 Esperando MariaDB... ($ATTEMPTS)"
  ATTEMPTS=$((ATTEMPTS+1))
  sleep 3
done

if [ $ATTEMPTS -eq 10 ]; then
  echo "❌ MariaDB no respondió. Abortando."
  exit 1
fi

echo "✅ MariaDB está lista."

# 📦 Backup de la base de datos desde otro contenedor usando la red
# echo "💾 Realizando respaldo de la base de datos..."
# docker run --rm --network=$NETWORK_NAME mysql \
#   sh -c "mysqldump -h $DB_HOST -u$DB_USER -p$DB_PASS $DB_NAME" > $BACKUP_NAME

# echo "📁 Respaldo guardado como: $BACKUP_NAME"

# 📋 Probar endpoints con curl
echo "⏳ Esperando a que el microservicio esté listo..."
for i in {1..10}; do
  if curl -s "$API_BASE" &>/dev/null; then
    echo "✅ Microservicio disponible."
    break
  else
    echo "🔁 Esperando microservicio... ($i)"
    sleep 2
  fi
done

# # 📚 Crear libro
# echo "📚 Creando libro..."
# curl -s -X POST "$API_BASE" \
#   -H "Content-Type: application/json" \
#   -d '{"titulo":"Prueba","autor":"Juan","anio":2024,"editorial":"Tester"}'

# # 📚 Obtener libros
# echo "📖 Libros en la base:"
# curl -s "$API_BASE" | jq

# -------------------------------
# 📚 CRUD con cURL
# -------------------------------

# Crear libro
echo "📚 Creando libro..."
RESPONSE=$(curl -s -X POST "$API_BASE" \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Libro Test","autor":"Juan","anio":2024,"editorial":"Calistenia"}')

# Obtener ID
if command -v jq &> /dev/null; then
  ID=$(echo "$RESPONSE" | jq -r '.id')
else
  ID=$(echo "$RESPONSE" | grep -o '"id":[0-9]*' | cut -d ':' -f2)
fi

echo "🆔 ID del libro creado: $ID"

# GET todos
echo "📖 Todos los libros:"
curl -s "$API_BASE"

# PUT actualizar
echo "✏️ Actualizando libro..."
curl -s -X PUT "$API_BASE/$ID" \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Libro Actualizado","autor":"Juan M.","anio":2025,"editorial":"UpdateBooks"}'

# GET por ID
echo "🔍 Libro actualizado:"
curl -s "$API_BASE/$ID"

# DELETE
echo "🗑️ Eliminando libro..."
curl -s -X DELETE "$API_BASE/$ID"
echo "✅ Libro eliminado."

# GET final
echo "📘 Libros actuales:"
curl -s "$API_BASE"

echo "🎉 Script finalizado correctamente."

echo "✅ Script completado correctamente."
