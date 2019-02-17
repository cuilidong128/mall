package com.mall.forum.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Gson解析的Number类型的字段解析适配器
 * @author duanxinyuan
 * 2018/6/20 14:58
 */
public class NumberTypeAdapter<T> extends TypeAdapter<Number> {
    private Class<T> c;

    public NumberTypeAdapter(Class<T> c) {
        this.c = c;
    }

    @Override
    public void write(JsonWriter jsonWriter, Number number) throws IOException {
        jsonWriter.value(number);
    }

    @Override
    public Number read(JsonReader jsonReader) {
        try {
            String json = jsonReader.nextString();
            if (c == short.class) {
                return (short) NumberUtils.toDouble(json);
            } else if (c == Short.class) {
                if (StringUtils.isEmpty(json)) {
                    return null;
                }
                return (short) NumberUtils.toDouble(json);
            } else if (c == int.class) {
                return (int) NumberUtils.toDouble(json);
            } else if (c == Integer.class) {
                if (StringUtils.isEmpty(json)) {
                    return null;
                }
                return (int) NumberUtils.toDouble(json);
            } else if (c == long.class) {
                return (long) NumberUtils.toDouble(json);
            } else if (c == Long.class) {
                if (StringUtils.isEmpty(json)) {
                    return null;
                }
                return (long) NumberUtils.toDouble(json);
            } else if (c == float.class) {
                return NumberUtils.toFloat(json);
            } else if (c == Float.class) {
                if (StringUtils.isEmpty(json)) {
                    return null;
                }
                return NumberUtils.toFloat(json);
            } else if (c == double.class) {
                return NumberUtils.toDouble(json);
            } else if (c == Double.class) {
                if (StringUtils.isEmpty(json)) {
                    return null;
                }
                return NumberUtils.toDouble(json);
            } else if (c == BigDecimal.class) {
                if (StringUtils.isEmpty(json)) {
                    return null;
                }
                return new BigDecimal(json);
            } else {
                return Integer.parseInt(json);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
