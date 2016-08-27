package br.ufrn.sgr.services;

import static org.junit.Assert.fail;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PacienteServiceWSTest {
	
	Client client;
	

	@Before
	public void setUp() throws Exception {
		
		client = Client.create();
		
	}

	@Test
	public void test() {
		
		WebResource webResource = client.resource("http://localhost/sgr/service/paciente/inserirPaciente");
		
		String input = "{\"nome\":\"mais em\",\"prontuario\":\"1\",\"nomeMae\":\"Mariazinha\",\"cpf\":\"aa\" ,\"cns\":\"098\",\"dataNascimento\":\"18/12/2000\"}";

		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, input);

		if (response.getStatus() != 201) {
			fail("Failed : HTTP error code : " + response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
		
	}

}
