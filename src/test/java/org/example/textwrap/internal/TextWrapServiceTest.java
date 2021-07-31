package org.example.textwrap.internal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TextWrapServiceTest {

    @InjectMocks
    TextWrapService textWrapService;

    @Mock
    TextWrapConfigProperties textWrapConfigProperties;

    @Test
    void wrapEqualToLineWidth() {
        String text = "some words";
        //when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(text.length());
        assertEquals(text, textWrapService.wrap(text));
    }

    @Test
    void wrapShorterThanLineWidth() {
        // TODO
    }

    @Test
    void wrapLongerThanLineWidth() {
        // TODO
    }

    @Test
    void wrapMultiline() {
        // TODO
    }

}