package co.com.mstransactionidentificator.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.com.mstransactionidentificator.identificator.IdentificatorTransaction;
import io.javalin.http.ContentType;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class get implements Handler {

	private IdentificatorTransaction extraccion = new IdentificatorTransaction();

	@Override
	public void handle(Context ctx) throws Exception {
		Map<String, List<String>> params = ctx.queryParamMap();
		HashMap<String, String> requestParams = new HashMap<>();
		params.forEach((key, value) -> requestParams.put(key, value.get(0)));
		ctx.contentType(ContentType.APPLICATION_JSON);
		ctx.result(extraccion.extraccionClasificacion1(requestParams));

	}

}
