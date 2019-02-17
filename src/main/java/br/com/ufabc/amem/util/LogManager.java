package br.com.ufabc.amem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.ufabc.amem.model.function.Function;
import br.com.ufabc.amem.model.function.FunctionAndParams;

public class LogManager {
	
	private String logLocation;
	
	public LogManager() {
		
		Properties props = new Properties();
		FileInputStream in;
		
		try {
			
			in = new FileInputStream("src/br/com/ufabc/amem/util/default.properties");
			props.load(in);
			in.close();
			
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		this.logLocation = props.getProperty("logOutPutFolder");
	}
	
	@SuppressWarnings("unchecked")
	public void writeLog(FunctionAndParams functionAndParams) throws IOException {
		
		Function function = functionAndParams.getFunction();
		String[] params   = functionAndParams.getParams();
		
		JSONObject obj = new JSONObject();
		obj.put("Operation", function.getName());
 
		JSONArray JSONparams = new JSONArray();
		
		for(int i = 0; i < params.length; i++) {
			
			JSONparams.add("Param[" + i + "]: " + params[i]);
		}
		
		obj.put("Params", JSONparams);
		
		Date date = new Date();
		obj.put("Date", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(date));
		
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd");
		String dia = dataFormatada.format(date);

		dataFormatada = new SimpleDateFormat("MM");
		String mes = dataFormatada.format(date);

		dataFormatada = new SimpleDateFormat("yyyy");
		String ano = dataFormatada.format(date);

		String logFile = this.logLocation + "/AMEMLOGS/" + ano + "/" + mes + "/" + dia + ".log";
		
		File fileDestination = new File(logFile);
		fileDestination.getParentFile().mkdirs();
		
		try (FileWriter file = new FileWriter(logFile, true)) {
			
			file.append(obj.toJSONString() + "\n");
		}
	}
}