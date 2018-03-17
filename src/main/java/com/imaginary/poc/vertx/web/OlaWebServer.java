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
	    Runner.runClusteredExample(OlaWebServer.class);
	    
	  }

	  @Override
	  public void start() throws Exception {

	    Router router = Router.router(vertx);
	    
	    router.route("/").
	    handler(requestHandler ->requestHandler.response().
	    		putHeader("content-type", "application/json").end("{\"path\": \"home\", \"page\": \"home\"}"));
	    //Content-Type: application/json
	    
	    
	    router.route("/ola/valter").
	    handler(requestHandler ->requestHandler.response().
	    		putHeader("content-type", "application/json").end("{\"name\": \"Valter Lobo\", \"github\": \"valterlobo1\"}"));
	    //Content-Type: application/json
	    
	    vertx.createHttpServer().requestHandler(router::accept).listen(8091);
	   

	  }
	

}
