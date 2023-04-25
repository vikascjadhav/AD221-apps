// TODO: Add Camel K imports

//kamel-k: resource=file:./data/manuscripts/book-01.xml
//kamel-k: resource=file:./data/manuscripts/book-02.xml

//kamel-k: dependency=camel-quarkus-jacksonxml
//kamel-k: dependency=camel-quarkus-jackson

// TODO: Add Camel K resources

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ManuscriptsApi extends RouteBuilder {

    private final List<Object> inMemoryBooks = new ArrayList<>();

    @Override
    public void configure() throws Exception {
        restConfiguration().bindingMode(RestBindingMode.json);

        // TODO: Add a route to store all books in the local variable

        from("file:/etc/camel/resources?noop=true")
        .unmarshal().jacksonxml()
        .process().body(Object.class,(Consumer<Object>) inMemoryBooks::add);

        rest("/manuscripts")
            .get()
                .route()
                .routeId("rest-manuscripts")
                .setBody(exchange -> inMemoryBooks)
            .endRest();
    }
}
