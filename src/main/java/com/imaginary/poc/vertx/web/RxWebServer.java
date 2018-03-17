package com.imaginary.poc.vertx.web;

import com.imaginary.poc.vertx.util.Runner;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.http.HttpServer;

public class RxWebServer  extends AbstractVerticle {

	  // Convenience method so you can run it in your IDE
	  public static void main(String[] args) {
	    Runner.runExample(RxWebServer.class);
	  }

	  @Override
	  public void start() throws Exception {
	    HttpServer server = new HttpServer(vertx.createHttpServer());
	    server.requestStream().toFlowable().subscribe(req -> {
	      req.response().putHeader("content-type", "application/json").end(new JsonObject().put("time", System.currentTimeMillis()).toString());
	    });
	    server.listen(8092);
	  }

}
