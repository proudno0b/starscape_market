
package com.market.app;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.*;
@JsonIgnoreProperties
public record DynamicMarketItem (
    JsonNode itemName
) {}    