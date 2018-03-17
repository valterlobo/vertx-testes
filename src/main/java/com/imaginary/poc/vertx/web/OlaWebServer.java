package com.imaginary.poc.vertx.web;

import com.imaginary.poc.vertx.util.Runner;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class OlaWebServer  extends AbstractVerticle {
	
	/* VER exemple com RX 
	 * Ver exemplo com Spring 
	 * Ver Exemplo  com Cluster 
	 */
	
	  public static void main(String[] args) {
	    Runner.runExample(OlaWebServer.class);
	  }

	  @Override
	  public void start() throws Exception {

	    Router router = Router.router(vertx);

	    router.route().handler(routingContext -> {
	      routingContext.response().putHeader("content-type", "text/html").end("Hello World! Valter ?");
	    });

	    vertx.createHttpServer().requestHandler(router::accept).listen(8091);
	  }
	

}
