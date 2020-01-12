package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.EngineLightAlert;

/**
 * User: blangel
 *
 * @see {@literal https://www.json.org/}
 * @see {@literal https://en.wikipedia.org/wiki/JSON}
 */
public class JsonParser {

    /**
     * @param alert to serialize into {@literal JSON}
     * @implNote a null value should be an {@linkplain IllegalArgumentException}; i.e. {@code throw new IllegalArgumentException}
     * @return the serialized {@literal JSON} representation of {@code alert}
     */
    public static String toJson(EngineLightAlert alert) {

        if (alert == null)
            throw new IllegalArgumentException();

        String vehicleId;

        if (alert.getVehicleId() != null && alert.getVehicleId().length() != 0)
            vehicleId = "\"vehicleId\":" + "\"" + alert.getVehicleId().replaceAll("\"", "\\\\\"") + "\",";
        else
            vehicleId = "";

        String dateTime = "\"dateTime\":" + alert.getDateTime();

        String codes = "";
        if(alert.getCodes() != null && alert.getCodes().length != 0) {
            dateTime += ",";
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < alert.getCodes().length; i++) {
                if(alert.getCodes()[i] != null && alert.getCodes()[i].getCode() != null)
                    code.append("{" + "\"code\":" + "\"" + alert.getCodes()[i].getCode() + "\"" + "},");
                else if(alert.getCodes()[i] == null)
                    code.append("");
                else if(alert.getCodes()[i].getCode() == null)
                    code.append("{},");
            }
            if(code.charAt(code.length() - 1) == ',')
                code.deleteCharAt(code.length() - 1);
            codes += "\"codes\":" + "[" + code.toString() + "]";
        }

        String alertToJson = "{" + vehicleId + dateTime + codes + "}";

        return alertToJson;
    }

}
