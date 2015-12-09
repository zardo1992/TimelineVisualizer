package zardoni.matteo.SerializzazioneTimeline.test;

import java.lang.reflect.Type;

import zardoni.matteo.SerializzazioneTimeline.lightTimelines.TimeInterval;
import zardoni.matteo.SerializzazioneTimeline.timedValue.TimedValueImpl;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;
import zardoni.matteo.SerializzazioneTimeline.value.valueLibrary.NameValue;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


public class TimedValueSerializer implements JsonDeserializer<TimedValueImpl<? extends AbstractValue>>{

    @Override
    public TimedValueImpl<? extends AbstractValue> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        if(je.isJsonObject()){
            JsonObject obj = je.getAsJsonObject();
            
//            String clazzName = obj.getAsJsonPrimitive("type").getAsString();
            
//            String valueClazzName = obj.getAsJsonPrimitive("parametertype").getAsString();
               
            TimeInterval ti = jdc.deserialize(obj.get("_timeInterval"), TimeInterval.class);
            NameValue nv = jdc.deserialize(obj.get("_value"), NameValue.class);
            
            return new TimedValueImpl<NameValue>(nv,ti);
        }
        return new TimedValueImpl<NameValue>();
    }
}
