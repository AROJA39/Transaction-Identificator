package co.com.mstransactionidentificator;

import co.com.mstransactionidentificator.handler.get;
import io.javalin.Javalin;

public class MSTransactionIdentificatorApplication {
	
	public static void main(String args[]) {
		Javalin app = Javalin.create().start(8002);
		app.get("/get", new get());
	}
}