package com.myzonespringboot.util;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

@Component
public class JsonDateSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		String dateStr = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);  
        jsonGenerator.writeString(dateStr);
		
	}

}
