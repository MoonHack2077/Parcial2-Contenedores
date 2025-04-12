docker build -t juanma1301/libros-app .
docker-compose down -v
docker-compose up --build -d
