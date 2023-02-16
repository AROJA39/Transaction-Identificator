package co.com.mstransactionidentificator.identificator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IdentificatorTransactionTest {

	@Test
	void test() {
		IdentificatorTransaction extraccion = new IdentificatorTransaction();
		HashMap<String, String> request = new HashMap<>();
		String response = "{\"Z_STATUS\":\"FAILED\",\"P3\":\"Informacion no encontrada\"}";
		request.put("P3", "031000");
		System.out.println(extraccion.extraccionClasificacion1(request));
		Assertions.assertEquals(response, extraccion.extraccionClasificacion1(request));
	}

}
