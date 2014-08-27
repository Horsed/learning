package org.horsed.rxcamel

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import org.apache.camel.ProducerTemplate
import org.apache.camel.Produce
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.test.junit4.CamelTestSupport
import org.hamcrest.Matchers.*

class MyRouteBuilderTest extends CamelTestSupport {

    @Produce(uri = "direct:hello")
    protected ProducerTemplate template

    @Test
    void shouldPrintBody() {
        String expectedBody = "<matched/>"

        template.sendBodyAndHeader(expectedBody, "foo", "bar")
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new MyRouteBuilder()
    }
}
