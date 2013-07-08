package sample

import java.util.concurrent.atomic.AtomicInteger

class TestCountingService extends CountingService{
  private val called = new AtomicInteger(0);

  override def  increment( count : Int) = {
    called.incrementAndGet()
    super.increment(count)
  }

  /**
   * How many times we have called this service.
   */
  def getNumberOfCalls()  =  called.get()

}
