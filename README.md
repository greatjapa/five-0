docker build -t five-0 .
docker run -p 8080:8080 five-0
curl -H "Content-Type: application/json" -XPOST "http://localhost:9200/routes/active" -d @samples/samples.json
http://localhost:9200/routes/active/_search