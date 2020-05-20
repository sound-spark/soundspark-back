package com.soundspark.soundsparkbackend.rest.controller;

import com.soundspark.soundsparkbackend.model.TranscriptionResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = {"http://localhost:4200", "https://soundspark.herokuapp.com"})
@RestController
public class TranscriptionController {
    private final String TRANSCRIPTION_API_URL = "https://soundspark-model.herokuapp.com/asr";

    @PostMapping("/transcription")
    public ResponseEntity<TranscriptionResponse> handleTranscription(@RequestParam("file") MultipartFile file) {
        ResponseEntity<String> response = requestTranscription(file.getResource());
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(new TranscriptionResponse(response.getBody()));
        } else {
            return ResponseEntity.status(response.getStatusCode()).build();
        }
    }

    private ResponseEntity<String> requestTranscription(Resource file) {
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .postForEntity(TRANSCRIPTION_API_URL, requestEntity, String.class);
        return response;
    }
}

