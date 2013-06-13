package net.nueca.objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class SerializedJSONObject implements Serializable {
	
	private transient JSONObject jsonObject;

    public SerializedJSONObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
    
    public JSONObject getJSONObject(){
    	return jsonObject;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(jsonObject.toString());
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException, JSONException {
        ois.defaultReadObject();
        jsonObject = new JSONObject((String) ois.readObject());
    }

}
