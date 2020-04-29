package com.soundspark.soundsparkbackend.rest.controller;

import com.soundspark.soundsparkbackend.model.TranscriptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.File;

@CrossOrigin(origins = {"http://localhost:4200", "https://soundspark.herokuapp.com"})
@RestController
public class TranscriptionController {

    @PostMapping("/transcription")
    public ResponseEntity<TranscriptionResponse> handleTranscription(@RequestParam("file") MultipartFile file) {
        try {
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);
            AudioFileFormat.Type audioFormat = AudioSystem.getAudioFileFormat(convFile).getType();
            if (!new AudioFileFormat.Type("WAVE", "wav").equals(audioFormat)) {
                throw new Exception("Wrong audio format");
            }
            String mockedTranscription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                    " In ac aliquet diam. Vestibulum ante ipsum primis in faucibus orci luctus.";
            return ResponseEntity.status(HttpStatus.OK).body(new TranscriptionResponse(mockedTranscription));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
        }
    }
}

