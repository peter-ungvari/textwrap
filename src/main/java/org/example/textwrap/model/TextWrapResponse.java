package org.example.textwrap.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
@JsonDeserialize(builder =  TextWrapResponse.TextWrapResponseBuilder.class)
public class TextWrapResponse {

    List<String> lines;

}
