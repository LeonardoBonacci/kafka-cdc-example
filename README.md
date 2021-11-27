# Run

Spring Reactor Kafka Outbox pattern with Debezium connector

```
mvn package
docker-compose up --build
```

```
docker run --tty --rm -i         --network cdc_default         debezium/tooling:1.1         bash -c 'pgcli postgresql://postgres:mysecretpassword@my-db:5432/postgres'  
```


```
curl -sX POST http://localhost:8083/connectors -d @debezium.json --header "Content-Type: application/json" | jq
```

```
docker run --tty \
           --network cdc_default \
           confluentinc/cp-kafkacat \
           kafkacat -b kafka:9092 \
		    -X auto.offset.reset=earliest \
                    -C -K: \
                    -f '\nKey (%K bytes): %k\t\nValue (%S bytes): %s\n\Partition: %p\tOffset: %o\n--\n' \
                    -t dbserver1.public.outbox
```


