package net.nueca.objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;

public class SerializedJSONArray implements Serializable {
	private transient JSONArray jsonArray;

    public SerializedJSONArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }
    
    public JSONArray getJSONArray(){
    	return jsonArray;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(jsonArray.toString());
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException, JSONException {
        ois.defaultReadObject();
        jsonArray = new JSONArray((String) ois.readObject());
    }
}
