package org.example.textwrap.internal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TextWrapService {

    private final TextWrapConfigProperties properties;

    public String wrap(String text) {
        return text;
    }

}
