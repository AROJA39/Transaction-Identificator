package co.com.mstransactionidentificator.usage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;

import co.com.mstransactionidentificator.constants.Constants;

public class ClientFileUpload {

	public ConcurrentHashMap<String, Object> getDataFile(String file) {
		try {
			StringBuilder result = new StringBuilder();
			URL url = new URL(Constants.ENDPOINTFILE + file);
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			String linea;
			while ((linea = reader.readLine()) != null) {
				result.append(linea);
			}
			reader.close();
			ConcurrentHashMap<String, Object> dataFile = new Gson().fromJson(result.toString(),
					ConcurrentHashMap.class);
			return dataFile;
		} catch (Exception e) {
			return null;
		}
	}
}
