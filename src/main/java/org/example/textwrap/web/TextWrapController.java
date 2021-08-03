package org.example.textwrap.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.concurrent.Future;

import org.example.textwrap.internal.TextWrapTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/lineBreak")
@RequiredArgsConstructor
public class TextWrapController {

    private final TextWrapTaskService lineBreakTaskService;
    private final ObjectMapper objectMapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public String post(@RequestBody String textAsJson) throws Exception {
        // Spring only deserializes complex objects from JSON automatically, so manual deserialization is required.
        String text = objectMapper.readValue(textAsJson, String.class);

        // Spring only serializes complex objects to JSON automatically, so manual serialization is required.
        return objectMapper.writeValueAsString(lineBreakTaskService.enqueue(text));
    }

    @GetMapping("{id}")
    public ResponseEntity<List<String>> get(@PathVariable("id") String id) throws Exception {
        Future<List<String>> listFuture = lineBreakTaskService.get(id);
        if (listFuture == null) {
            return ResponseEntity.notFound().build();
        }
        return listFuture.isDone()
                ? ResponseEntity.ok(listFuture.get())
                : ResponseEntity.accepted().build();
    }

    // TODO think about adding DELETE endpoints (or persistent storage). Memory may be depleted over time.

}
