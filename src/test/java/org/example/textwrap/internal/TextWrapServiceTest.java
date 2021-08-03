package org.example.textwrap.internal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

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
    void wrapEmpty() throws Exception {
        String text = "";
        when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(10);
        List<String> lines = textWrapService.wrap(text).get();
        assertTrue(lines.isEmpty());
    }

    @Test
    void wrapEqualToLineWidth() throws Exception {
        String text = "some words";
        when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(text.length());
        List<String> lines = textWrapService.wrap(text).get();
        assertEquals(1, lines.size());
        assertEquals(List.of(text), lines);
    }

    @Test
    void wrapShorterThanLineWidth() throws Exception {
        String text = "some words";
        when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(10);
        List<String> lines = textWrapService.wrap(text).get();
        assertEquals(1, lines.size());
        assertEquals(List.of(text), lines);
    }

    @Test
    void wrapLongerThanLineWidth() throws Exception {
        String text = "lorem   ipsum      dolor   sit amet    ";
        when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(10);
        List<String> lines = textWrapService.wrap(text).get();
        assertEquals(4, lines.size());
        assertEquals(List.of("lorem   ", "ipsum     ", "dolor   ", "sit amet  "), lines);
    }

    @Test
    void testSentence1() throws Exception {
        String text = "This is a test sentence to smoke-test line breaking.";
        when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(10);
        List<String> lines = textWrapService.wrap(text).get();
        assertEquals(List.of(
                "This is a ",
                "test ",
                "sentence ",
                "to ",
                "smoke-test",
                "line ",
                "breaking."
        ), lines);
    }

    @Test
    void testSentence2() throws Exception {
        String text = "This is a test where n is very tiny.";
        when(textWrapConfigProperties.getLineWidthInCharacters()).thenReturn(5);
        List<String> lines = textWrapService.wrap(text).get();
        assertEquals(List.of(
                "This ",
                "is a ",
                "test ",
                "where",
                "n is ",
                "very ",
                "tiny."
        ), lines);
    }

}
