package Utils;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateAdapter implements JsonDeserializer<Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            java.util.Date utilDate = dateFormat.parse(json.getAsString());
            return new Date(utilDate.getTime()); // Convertimos a java.sql.Date
        } catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }
}



//TODO: ESTA CLASE ESTA SOLO PARA MANEJAR CON ESTO EN TODOS LADOS LAS DIFERENTES DATES. SOLO PARA ESO.