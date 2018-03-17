package com.imaginary.poc.vertx;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleTesteVerticle  extends AbstractVerticle {

	  private final Logger logger = LoggerFactory.getLogger(SampleTesteVerticle.class);

	  @Override
	  public void start(Future<Void> startFuture) {
	    vertx.createHttpServer()
	      .requestHandler(req -> {
	        req.response()
	          .putHeader("Content-Type", "plain/text")
	          .end("Yo!");
	        logger.info("Handled a request on path {} from {}", req.path(), req.remoteAddress().host());
	      })
	      .listen(11981, ar -> {
	        if (ar.succeeded()) {
	          startFuture.complete();
	        } else {
	          startFuture.fail(ar.cause());
	        }
	      });
	}

}
