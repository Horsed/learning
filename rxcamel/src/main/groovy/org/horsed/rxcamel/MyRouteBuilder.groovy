package org.horsed.rxcamel

import org.apache.camel.CamelContext
import org.apache.camel.ProducerTemplate
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.rx.ReactiveCamel
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext


class MyRouteBuilder extends RouteBuilder {

	static void main(String[] args) {
		ApplicationContext springCtx = new ClassPathXmlApplicationContext("rxcamel-spring.xml")
		CamelContext camelCtx = springCtx.getBean("camelCtx", CamelContext.class)
		ProducerTemplate template = camelCtx.createProducerTemplate()

		template.sendBody("direct:hello", "world")
	}

	void configure() {
		CamelContext camelCtx = getContext()

		ReactiveCamel rx = new ReactiveCamel(camelCtx)
		rx.Observable<String> directObservable = rx.toObservable("direct:hello", String.class)
		rx.Observable<String> restletObservable = rx.toObservable("restlet:http://localhost:80/users/{username}?restletMethods=post,get,put")

		directObservable.subscribe({println("hello " + it)})
		restletObservable.subscribe({println(it.getIn().getBody())})
	}
}
