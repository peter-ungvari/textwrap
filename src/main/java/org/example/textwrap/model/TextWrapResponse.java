package org.example.textwrap.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
@JsonDeserialize(builder =  TextWrapResponse.TextWrapResponseBuilder.class)
public class TextWrapResponse {

    String text;

}
