package com.soundspark.soundsparkbackend.rest.controller;

import com.soundspark.soundsparkbackend.model.TranscriptionResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200", "https://soundspark.herokuapp.com"})
@RestController
public class TranscriptionController {
    String transcriptionURL = "https://soundspark-model.herokuapp.com/asr";

    @PostMapping("/transcription")
    public ResponseEntity<TranscriptionResponse> handleTranscription(@RequestParam("file") MultipartFile requestFile) throws IOException {

        File file = new File(System.getProperty("java.io.tmpdir") + "/" + requestFile.getOriginalFilename());
        requestFile.transferTo(file);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file.getPath()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate
                .postForEntity(transcriptionURL, requestEntity, String.class);
        return ResponseEntity.status(HttpStatus.OK).body(new TranscriptionResponse(response.getBody()));
    }
}

