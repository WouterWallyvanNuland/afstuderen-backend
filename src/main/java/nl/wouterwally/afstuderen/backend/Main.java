package nl.wouterwally.afstuderen.backend;

import java.util.concurrent.atomic.AtomicInteger;

import static spark.Spark.*;

/**
 * Backend TODO lijst:
 * - HOWTO Spark applicatie naar JAR builden (mvn clean assemble)
 * - HOWTO JAR in een Docker image stoppen (docker build .)
 * - Docker image naar registry versturen (docker push ....)
 */
public class Main {

        static AtomicInteger counter = new AtomicInteger(0);

        public static void main(String[] args) {
            // enable CORS for local testing.
            enableCORS();

            // Demo routes
            get("/hello", (req, res) -> "Hello World");
            get( "/afstuderen", (req, res) -> "kei gek");

            // Application routes
            post("/pluseen", (req, res) -> {
                // Add 1 to counter & return
                int updatedCounter = counter.incrementAndGet();

                // Response payload
                CounterResponseMessage message = new CounterResponseMessage(updatedCounter);

                res.type("application/json");
                res.status(200);
                return message;
            }, new JsonTransformer());

            get("/currentcounter", "application/json", (req, res) -> {
                // Get current counter value
                int currentValue = counter.intValue();

                // Response payload
                CounterResponseMessage message = new CounterResponseMessage(currentValue);

                res.type("application/json");
                res.status(200);
                return message;
            }, new JsonTransformer());
        }

    // Enables CORS on requests. This method is an initialization method and should be called once.
    private static void enableCORS() {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://localhost:4200");
            response.header("Access-Control-Request-Method", "GET,POST");
            response.header("Access-Control-Allow-Headers", "");
        });
    }
}
