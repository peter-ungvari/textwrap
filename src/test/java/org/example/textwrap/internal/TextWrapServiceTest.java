package org.example.textwrap.internal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
    void wrapEmpty() {
        String text = "";
        when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(10);
        List<String> lines = textWrapService.wrap(text);
        assertTrue(lines.isEmpty());
    }

    @Test
    void wrapEqualToLineWidth() {
        String text = "some words";
        when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(text.length());
        List<String> lines = textWrapService.wrap(text);
        assertEquals(1, lines.size());
        assertEquals(List.of(text), lines);
    }

    @Test
    void wrapShorterThanLineWidth() {
        String text = "some words";
        when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(10);
        List<String> lines = textWrapService.wrap(text);
        assertEquals(1, lines.size());
        assertEquals(List.of(text), lines);
    }

    @Test
    void wrapLongerThanLineWidth() {
        String text = "lorem   ipsum      dolor   sit amet    ";
        when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(10);
        List<String> lines = textWrapService.wrap(text);
        assertEquals(4, lines.size());
        assertEquals(List.of("lorem  ", "ipsum     ", "dolor  ", "sit amet  "), lines);
    }

}
