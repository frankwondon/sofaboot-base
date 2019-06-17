package com.api.app.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingBuilderPlugin;
import springfox.documentation.spi.service.contexts.ApiListingContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
@Slf4j
public class SwaggerParametersReader implements ApiListingBuilderPlugin {



    @Override
    public boolean supports(DocumentationType documentationType) {
        return false;
    }

    @Override
    public void apply(ApiListingContext apiListingContext) {
    }
}
