package com.imaginary.poc.vertx;

import com.imaginary.poc.vertx.util.Runner;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

public class MainVerticle extends AbstractVerticle {

	// Convenience method so you can run it in your IDE
	public static void main(String[] args) {
		Runner.runExample(MainVerticle.class);
	}

	@Override
	public void start() throws Exception {
		System.out.println("[Main] Running in " + Thread.currentThread().getName());
		vertx.deployVerticle("com.imaginary.poc.vertx.WorkerVerticle",
				new DeploymentOptions().setWorker(true));

		vertx.eventBus().send("sample.data", "hello vert.x", r -> {
			System.out.println(
					"[Main] Receiving reply ' " + r.result().body() + "' in " + Thread.currentThread().getName());
		});
	}

}
