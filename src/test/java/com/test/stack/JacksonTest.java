package com.test.stack;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonTest {

	public JacksonTest() {
		super();
	}
	
	@Test
	public void test() throws JsonProcessingException {
		ObjectMapper mapper = new Jackson2ObjectMapperBuilder().createXmlMapper(false)
				.modules(new JavaTimeModule(), new Jdk8Module()).serializationInclusion(JsonInclude.Include.NON_NULL)
				.build();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
		mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
		mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
		mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
		mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		TestClassA test = new TestClassA();
		test.setHelloWorld("Hello World");

		BlockChainWrapper<TestClassA> block = new BlockChainWrapper<TestClassA>();
		block.setPayload(test);
		block.setSignature("SGVsbG8gV29ybGQ=");
		block.setNonce(255);

		String out = mapper.writeValueAsString(block);
		assertEquals("{\"@type\":\"BlockChainWrapper\",\"@id\":1,\"payload\":{\"@type\":\"TestClassA\",\"@id\":2,\"helloWorld\":\"Hello World\"},\"nonce\":255,\"signature\":\"SGVsbG8gV29ybGQ=\"}", out);
		

	}

}
