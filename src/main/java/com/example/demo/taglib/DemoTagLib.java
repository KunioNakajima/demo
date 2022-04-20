package com.example.demo.taglib;

import com.example.demo.taglib.params.QueryParamInterface;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Component
public class DemoTagLib {
    public String queryParam(String path, QueryParamInterface params) {
        return queryParam(path, params, new HashMap<>());
    }

    public String queryParam(String path, QueryParamInterface params, Map<String, String> override) {
        List<String> out = new ArrayList<>();

        if (Objects.nonNull(params)) {
            for (String key : params.getParamsMap().keySet()) {
                if (override.containsKey(key) && StringUtils.isNotEmpty(override.get(key))) {
                    out.add(String.join("=", key, override.get(key)));
                } else if (!Objects.isNull(params.getParamsMap().get(key))) {
                    out.add(String.join("=", key, params.getParamsMap().get(key).toString()));
                }
            }
        } else {
            for (String key : override.keySet()) {
                out.add(String.join("=", key, override.get(key)));
            }
        }

        return path + (out.size() > 0 ? "?" + String.join("&", out) : "");
    }

    public String postTime(LocalDateTime postTs) {
        if (Objects.isNull(postTs)) {
            return null;
        }
        String out = DateTimeFormatter.ofPattern("M/d(E) H:mm", Locale.JAPANESE).format(postTs);
        return out;
    }
}
