package org.example.textwrap.internal;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TextWrapTaskService {

    private final TextWrapService textWrapService;

    private final Map<String, Future<List<String>>> processes = new ConcurrentHashMap<>();

    public String enqueue(String text) {
        String id = UUID.randomUUID().toString();
        processes.put(id, textWrapService.wrap(text));
        return id;
    }

    public Future<List<String>> get(String id) {
        return processes.get(id);
    }

}
