package spring;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe utilitaria para conversao de JSON.
 */
public class JsonUtil {
	/**
	 * Converte um objeto em um array de bytes que representa o objeto em JSON.
	 * 
	 * @param object o objeto a ser convertido em array de bytes.
	 * @return um array de bytes representando o objeto em formato JSON.
	 * @throws IOException se ocorrer um erro na conversao.
	 */
	static byte[] toJson(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}
}