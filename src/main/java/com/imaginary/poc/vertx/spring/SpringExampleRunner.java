package com.imaginary.poc.vertx.spring;

import io.vertx.core.Vertx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.imaginary.poc.vertx.spring.context.ExampleSpringConfiguration;
import com.imaginary.poc.vertx.spring.verticle.ServerVerticle;
import com.imaginary.poc.vertx.spring.verticle.SpringDemoVerticle;

/**
 * Runner for the vertx-spring sample
 *
 */
public class SpringExampleRunner {

  public static void main( String[] args ) {
    ApplicationContext context = new AnnotationConfigApplicationContext(ExampleSpringConfiguration.class);
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new SpringDemoVerticle(context));
    vertx.deployVerticle(new ServerVerticle());
    System.out.println("Deployment done");
  }

}
