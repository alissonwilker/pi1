package br.edu.ifb.java_web_dev;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	static byte[] toJson(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}
}