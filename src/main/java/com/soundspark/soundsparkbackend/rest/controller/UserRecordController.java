package com.soundspark.soundsparkbackend.rest.controller;

import com.soundspark.soundsparkbackend.model.AudioRecordDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200", "https://soundspark.herokuapp.com"})
@RestController
public class UserRecordController {

    @PostMapping("/records")
    public ResponseEntity<AudioRecordDetails> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, UnsupportedAudioFileException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        AudioSystem.getAudioFileFormat(convFile);
        return ResponseEntity.status(HttpStatus.OK).body(
                new AudioRecordDetails(
                        AudioSystem.getAudioFileFormat(convFile).getByteLength(),
                        AudioSystem.getAudioFileFormat(convFile).getType().toString()
                )
        );
    }
}

