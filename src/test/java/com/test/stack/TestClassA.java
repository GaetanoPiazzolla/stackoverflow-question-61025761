package com.test.stack;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonRootName("TestClassA")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class TestClassA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String helloWorld = "Hello World";

	public TestClassA() {
		super();
	}

	public String getHelloWorld() {
		return helloWorld;
	}

	public void setHelloWorld(String helloWorld) {
		this.helloWorld = helloWorld;
	}
	

}