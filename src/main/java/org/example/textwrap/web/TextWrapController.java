package org.example.textwrap.web;

import org.example.textwrap.internal.TextWrapService;
import org.example.textwrap.model.TextWrapRequest;
import org.example.textwrap.model.TextWrapResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("textwrap")
@RequiredArgsConstructor
public class TextWrapController {

    private final TextWrapService textWrapService;

    @PostMapping("wrap")
    public TextWrapResponse wrap(@RequestBody TextWrapRequest request) {
        return TextWrapResponse.builder()
                .withText(textWrapService.wrap(request.getText()))
                .build();
    }
}
