up:
	mvn clean package
	docker-compose up --build

load:
	echo "Loading data"
	curl -H "Content-Type: application/json" -XPOST "http://localhost:9200/routes/active/1" -d @samples/01.json
	curl -H "Content-Type: application/json" -XPOST "http://localhost:9200/routes/active/2" -d @samples/02.json
	curl -H "Content-Type: application/json" -XPOST "http://localhost:9200/routes/active/3" -d @samples/03.json
	curl -H "Content-Type: application/json" -XPOST "http://localhost:9200/routes/active/4" -d @samples/04.json
	curl -H "Content-Type: application/json" -XPOST "http://localhost:9200/routes/active/5" -d @samples/05.json
	curl -H "Content-Type: application/json" -XPOST "http://localhost:9200/routes/active/6" -d @samples/06.json

clean:
	curl -XDELETE  "http://localhost:9200/routes"