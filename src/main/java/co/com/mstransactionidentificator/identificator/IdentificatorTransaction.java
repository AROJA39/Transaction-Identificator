package co.com.mstransactionidentificator.identificator;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import co.com.mstransactionidentificator.constants.Constants;
import co.com.mstransactionidentificator.usage.ClientFileUpload;

public class IdentificatorTransaction {

	public static HashMap<String, Object> responseInfo = null;

	ConcurrentHashMap<String, Object> codeProcess = new ClientFileUpload().getDataFile(Constants.PROCESSCODE);
	ConcurrentHashMap<String, Object> additionalInfo = new ClientFileUpload().getDataFile(Constants.SUPPORTINFO);

	public String extraccionClasificacion1(HashMap<String, String> map) {
		responseInfo = new HashMap<>();
		Long initialTime = System.nanoTime();
		try {
			Set<String> keys = map.keySet();
			String field = new String();
			String key1 = null;
			for (String key : keys) {
				if (key.equals("P3")) {
					key1 = map.get(key);
					field = codeProcess.containsKey(map.get(key)) ? (String) codeProcess.get(map.get(key))
							: Constants.INFO_NOT_FOUND;
				} else if (key.contains("EXCEPCION")) {
					key1 = key1 + map.get(key);
					field = codeProcess.containsKey(key1) ? (String) codeProcess.get(key1) : Constants.INFO_NOT_FOUND;
					responseInfo.put("P3", field);
				} else {
					field = additionalInfo.containsKey(key + "_" + map.get(key))
							? (String) additionalInfo.get(key + "_" + map.get(key))
							: Constants.INFO_NOT_FOUND;
				}
				responseInfo.put(key, field);
			}
			responseInfo.put("Z_STATUS", responseInfo.containsValue(Constants.INFO_NOT_FOUND) ? "FAILED" : "SUCCESS");

			responseInfo.put("VERSION", Constants.VERSION);
			responseInfo.put("TIEMPO", (System.nanoTime() - initialTime) + " Nanosegundos");
			responseInfo.put("SERVICIO", Constants.SERVICIO);
			responseInfo.put("PAQUETE", Constants.PAQUETE);
			responseInfo.put("CLASE", Constants.CLASE);
			responseInfo.put("ENDPOINT", Constants.ENDPOINT);

			JSONObject jsonObject = new JSONObject(responseInfo);
			return jsonObject.toString();
		} catch (Exception e) {
			responseInfo.put("error ", "error al intentar abrir el archivo "+e);
			JSONObject jsonObject = new JSONObject(responseInfo);
			return jsonObject.toString();
		}
	}

}