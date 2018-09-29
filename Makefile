default:
	echo "Loading data"
	curl -H "Content-Type: application/json" -XPOST "http://localhost:9200/routes/active/1" -d @samples/01.json
	curl -H "Content-Type: application/json" -XPOST "http://localhost:9200/routes/active/2" -d @samples/02.json

clean:
	curl -XDELETE  "http://localhost:9200/routes"