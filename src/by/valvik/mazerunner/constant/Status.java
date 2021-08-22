package by.valvik.mazerunner.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Status {

    PASSAGE("\u00A0\u00A0"),
    WALL("\u2588\u2588"),
    PATH("//");

    private static final Map<String, Status> MAPPING = new HashMap<>();

    private final String sign;

    static {

        Arrays.stream(Status.values())
              .forEach(s -> MAPPING.put(s.sign, s));

    }

    Status(String sign) {

        this.sign = sign;

    }

    public String getSign() {

        return sign;

    }

    public static Status forSign(String sign) {

        return MAPPING.get(sign);

    }

}
