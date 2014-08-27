package org.horsed.rxcamel

import org.apache.camel.CamelContext
import org.apache.camel.ProducerTemplate
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.rx.ReactiveCamel
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.context.annotation.Bean

import rx.Observable

@Configuration
class AppConfig extends SingleRouteCamelConfiguration implements InitializingBean {

    CamelContext camelCtx
    ReactiveCamel rxCtx
    Observable hello

    static void main(String[] args) {
        ApplicationContext springCtx = new ClassPathXmlApplicationContext("rxcamel-spring.xml")
        CamelContext camelCtx = springCtx.getBean("camelCtx", CamelContext.class)
        ProducerTemplate template = camelCtx.createProducerTemplate()

        template.sendBody("direct:hello", "world")
    }

    @Bean
    ReactiveCamel rxCtx() {
        new ReactiveCamel(getContext())
    }

    @Bean
    Observable hello() {
        def hello = rxCtx.toObservable("direct:hello", String.class)
        hello.subscribe({println("hello " + it)})
    }

    @Bean
    CamelContext camelCtx() {
        new DefaultCamelContext()
    }
}
