package sample

import org.springframework.beans.factory.config.BeanDefinition


class TestAppConfiguration extends AppConfiguration {

  override val countingService = bean("countingService") {
    val cs :  CountingService =  new TestCountingService
    cs
    }
}
