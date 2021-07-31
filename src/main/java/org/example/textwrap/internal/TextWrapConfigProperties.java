package org.example.textwrap.internal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("textwrap")
public class TextWrapConfigProperties {

    int lineWidthInCharacters;

}
