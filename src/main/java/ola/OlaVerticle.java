package ola;

import io.vertx.core.AbstractVerticle;

public class OlaVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		// The start method will be called when the verticle is deployed

		System.out.println("In OtherVerticle.start");

		System.out.println("Config is " + config());
	}

	@Override
	public void stop() throws Exception {

		// You can optionally override the stop method too, if you have some clean-up to
		// do

		System.out.println("In OtherVerticle.stop");

	}

}
