package io.vertx.book.hello;

// We use the .rxjava. package containing the RX-ified APIS

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;

public class MyFirstRXVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        // We get the stream of request as Observable
        server.requestStream().toObservable()
                .subscribe(req ->
                        // foe each HTTP request, this method is called
                        req.response().end("Hello from "
                                + Thread.currentThread().getName()));
        // We start the server using rxListen returning a
        // Single of HTTP Server. We need to subscribe to
        // trigger the operation
        server
                .rxListen(8080)
                .subscribe();
    }
}
