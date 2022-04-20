package com.example.demo.config;

import org.seasar.doma.boot.autoconfigure.DomaConfigBuilder;
import org.seasar.doma.boot.autoconfigure.DomaProperties;
import org.seasar.doma.jdbc.UnknownColumnHandler;
import org.seasar.doma.jdbc.entity.EntityType;
import org.seasar.doma.jdbc.query.Query;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DomaConfig 拡張</br>
 * [DOMA2002] 対応、マッピングのない 項目の SELECT を許可する</br>
 * 標準 DomaProperties の Builder を継承しつつ UnknownColumnHandler を差替える</br>
 */
@Configuration
public class DomaConfig {

    @Bean
    public DomaConfigBuilder domaConfigBuilder(DomaProperties domaProperties) {
        DomaConfigBuilder domaConfigBuilder = new DomaConfigBuilder(domaProperties);
        return domaConfigBuilder.unknownColumnHandler(new UnknownColumnHandler() {
            @Override
            public void handle(Query query, EntityType<?> entityType, String unknownColumnName) {
                // no handle
            }
        });
    }

}
