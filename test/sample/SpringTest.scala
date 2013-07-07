package sample

/**
 * Created with IntelliJ IDEA.
 * User: ouertani
 * Date: 07/07/13
 * Time: 23:57
 * To change this template use File | Settings | File Templates.
 */
class SpringTest {
  @Test
  def testSpring() throws Exception {
    // create a spring context and scan the classes
    AnnotationConfigApplicationContext ctx =
      new AnnotationConfigApplicationContext();
    ctx.scan("sample");
    ctx.refresh();

    // get hold of the actor system
    val system = ctx.getBean(ActorSystem.class);
    // use the Spring Extension to create props for a named actor bean
    val counter = system.actorOf(
      SpringExtProvider.get(system).props("CountingActor"), "counter");

    // tell it to count three times
    counter.tell(COUNT, null);
    counter.tell(COUNT, null);
    counter.tell(COUNT, null);

    // check that it has counted correctly
    FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
    Future<Object> result = ask(counter, new Get(),
      Timeout.durationToTimeout(duration));
    assertEquals(3, Await.result(result, duration));

    // shut down the actor system
    system.shutdown();
    system.awaitTermination();
  }
}
