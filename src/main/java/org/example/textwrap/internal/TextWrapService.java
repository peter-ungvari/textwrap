package org.example.textwrap.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TextWrapService {

    private final TextWrapConfigProperties properties;

    @Async
    public Future<List<String>> wrap(String text) {
        int lineWidth = properties.getLineWidthInCharacters();
        List<String> result = new ArrayList<>(text.length() / lineWidth);
        int start = 0;
        while (start < text.length()) {
            while (start < text.length() && text.charAt(start) == ' ') {
                ++start; // remove leading spaces
            }

            int end;
            if (text.length() - start <= lineWidth) {
                end = text.length() - 1;  // last line
            } else {
                end = start + lineWidth;
                if (text.charAt(end) == ' ') {  // there is a space next to the line boundary
                    --end;
                } else { // find last space on valid position
                    while (text.charAt(end) != ' ') {
                        --end;
                    }
                }
            }

            String line = text.substring(start, end + 1);
            if (!line.isBlank()) {
                result.add(line);
            }
            start = end + 1;
        }
        return new AsyncResult<>(result);
    }

}
