package com.github.gustav9797.PowerfulPermsAPI;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class DBDocument {

    protected Map<String, Object> data;

    public DBDocument(Map<String, Object> data) {
        this.data = data;
    }

    public Object get(String key) {
        return data.get(key);
    }

    public String getString(String key) {
        return (String) data.get(key);
    }

    public int getInt(String key) {
        Object input = data.get(key);
        if (input instanceof Long) {
            return ((Long) input).intValue();
        }
        return (Integer) input;
    }

    public boolean getBoolean(String key) {
        return (Boolean) data.get(key);
    }

    public Timestamp getTimeStamp(String key) {
        Object timeObject = data.get(key);
        if (timeObject == null) {
            return null;
        }
        if (timeObject instanceof Timestamp) {
            return (Timestamp) timeObject;
        }

        LocalDateTime localDateTime = (LocalDateTime) timeObject;
        if (localDateTime == null) {
            return null;
        }

        return Timestamp.valueOf(localDateTime);
    }

    public Date getDate(String key) {
        if (getTimeStamp(key) != null) {
            Calendar start = Calendar.getInstance();
            start.setTimeInMillis(getTimeStamp(key).getTime());
            return start.getTime();
        }
        return null;
    }
}
