package org.example.textwrap.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TextWrapService {

    private final TextWrapConfigProperties properties;

    public List<String> wrap(String text) {
        // 1st attempt:
        // return wrap(List.of(text));
        return wrapV2(text);
    }

    /**
     * 1st attempt which looks good at first and passes all tests but it is a ticking bomb.
     * @param lines Wrapped lines, except the last one.
     * @return Last line of input is wrapped at limit if neccessary.
     */
    private List<String> wrap(List<String> lines) {
        int lastLineIndex = lines.size() - 1;
        String lastLine = lines.get(lastLineIndex).replaceAll("^ +", ""); // remove leading spaces
        if (lastLine.isEmpty()) {
            return lines.subList(0, lastLineIndex);
        }
        int lineWidth = properties.getLineWidthInCharacters();
        if (lineWidth >= lastLine.length()) {
            return lines; // terminate recursion if line if the limit is bigger than the length of the last line
        }

        int endIndex = lineWidth;
        while (lastLine.charAt(endIndex) != ' ') { // find last space within limit
            --endIndex;
        }

        // create result list with all lines except the last
        ArrayList<String> result = new ArrayList<>(lines.subList(0, lastLineIndex));
        result.add(lastLine.substring(0, endIndex)); // add the 1st part of the split line
        result.add(lastLine.substring(endIndex)); // add the rest
        return wrap(result); // tail recursion (not optimized in java, so stack may overflow for very big inputs)
        // list references stored on stack till the end, so temporary results cannot be garbage collected,
        // so even heap memory might be exhausted => logic needs to be reimplemented another way.
    }

    /**
     * 2nd attempt focusing on memory-efficiency.
     * @param text Original text.
     * @return Original text wrapped into a list of lines.
     */
    private List<String> wrapV2(String text) {
        int lineWidth = properties.getLineWidthInCharacters();
        List<String> result = new ArrayList<>(text.length() / lineWidth);
        int start = 0;
        while (start < text.length()) {
            while (start < text.length() && text.charAt(start) == ' ') {
                ++start; // remove leading spaces
            }

            int end;
            if (text.length() - start <= lineWidth) {
                end = text.length(); // remaining text fits in the last line
            } else {
                end = start + lineWidth;
                while (text.charAt(end) != ' ') {
                    --end;
                }
            }

            String line = text.substring(start, end);
            if (!line.isBlank()) {
                result.add(line);
            }
            start = end + 1;
        }
        return result;
    }

}
