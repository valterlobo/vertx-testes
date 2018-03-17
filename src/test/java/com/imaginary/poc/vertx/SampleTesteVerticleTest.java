package com.imaginary.poc.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("👋 A fairly basic test example-Vertx-Valter Lobo")
@ExtendWith(VertxExtension.class)
class SampleTesteVerticleTest {

	@Test
	  @DisplayName("⏱ Count 3 timer ticks")
	  void countThreeTicks(Vertx vertx, VertxTestContext testContext) {
	    AtomicInteger counter = new AtomicInteger();
	    vertx.setPeriodic(100, id -> {
	      if (counter.incrementAndGet() == 3) {
	        testContext.completeNow();
	      }
	    });
	  }

	  @Test
	  @DisplayName("⏱ Count 3 timer ticks, with a checkpoint")
	  void countThreeTicksWithCheckpoints(Vertx vertx, VertxTestContext testContext) {
	    Checkpoint checkpoint = testContext.checkpoint(3);
	    vertx.setPeriodic(100, id -> checkpoint.flag());
	  }

	  @Test
	  @DisplayName("🚀 Deploy a HTTP service verticle and make 10 requests")
	  void useSampleVerticle(Vertx vertx, VertxTestContext testContext) {
	    WebClient webClient = WebClient.create(vertx);
	    Checkpoint deploymentCheckpoint = testContext.checkpoint();
	    Checkpoint requestCheckpoint = testContext.checkpoint(10);

	    vertx.deployVerticle(new SampleTesteVerticle(), testContext.succeeding(id -> {
	      deploymentCheckpoint.flag();

	      for (int i = 0; i < 10; i++) {
	        webClient.get(11981, "localhost", "/")
	          .as(BodyCodec.string())
	          .send(testContext.succeeding(resp -> {
	            testContext.verify(() -> {
	              assertThat(resp.statusCode()).isEqualTo(200);
	              assertThat(resp.body()).contains("Yo!");
	              requestCheckpoint.flag();
	            });
	          }));
	      }
	    }));
	  }

	  @DisplayName("➡️ A nested test with customized lifecycle")
	  @Nested
	  class CustomLifecycleTest {

	    Vertx vertx;

	    @BeforeEach
	    void prepare() {
	      vertx = Vertx.vertx(new VertxOptions()
	        .setMaxEventLoopExecuteTime(1000)
	        .setPreferNativeTransport(true)
	        .setFileResolverCachingEnabled(true));
	    }

	    @Test
	    @DisplayName("⬆️ Deploy SampleVerticle")
	    void deploySampleVerticle(VertxTestContext testContext) {
	      vertx.deployVerticle(new SampleTesteVerticle(), testContext.succeeding(id -> testContext.completeNow()));
	    }

	    @Test
	    @DisplayName("🛂 Make a HTTP client request to SampleVerticle")
	    void httpRequest(VertxTestContext testContext) {
	      WebClient webClient = WebClient.create(vertx);
	      vertx.deployVerticle(new SampleTesteVerticle(), testContext.succeeding(id -> {
	        webClient.get(11981, "localhost", "/yo")
	          .as(BodyCodec.string())
	          .send(testContext.succeeding(resp -> {
	            testContext.verify(() -> {
	              assertThat(resp.statusCode()).isEqualTo(200);
	              assertThat(resp.body()).contains("Yo!");
	              testContext.completeNow();
	            });
	          }));
	      }));
	    }

	    @AfterEach
	    void cleanup() {
	      vertx.close();
	    }
	}

}
