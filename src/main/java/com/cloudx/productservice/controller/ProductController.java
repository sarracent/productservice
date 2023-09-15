package com.cloudx.productservice.controller;

import com.cloudx.productservice.model.response.ProductResponse;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;

import static com.cloudx.productservice.constants.Constants.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = "Prices Controller", description = "The Prices Api")
@Validated
@RequestMapping(path = "/api/product")
@Timed(value="prices_method", extraTags = {"Version","1.0"})
public interface ProductController {

    @GetMapping(produces = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Get Price Info by applicationDate, productId, brandId", description = "Returns prices information")
    @Parameters(value = {
            @Parameter(name = SERVICE_NAME, in = ParameterIn.HEADER, description = SERVICE_DESCR, schema = @Schema(type = "string")),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS_CODE, description = SUCCESS_MSG, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = BADREQUEST_CODE, description = BADREQUEST_MSG, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = INTERNALSERVER_CODE, description = INTERNALSERVER_MSG, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))),
    })
    ResponseEntity<ProductResponse> getProduct(@RequestHeader Map<String, Object> httpHeadersMap,
                                              @RequestParam LocalDateTime applicationDate,
                                              @RequestParam Long productId,
                                              @RequestParam Long brandId);
}
