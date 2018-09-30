docker build -t five-0 .
docker run -p 8080:8080 five-0
docker-compose up --build
curl -H "Content-Type: application/json" -XPOST "http://localhost:9200/routes/active" -d @samples/01.json
http://localhost:9200/routes/active/_search