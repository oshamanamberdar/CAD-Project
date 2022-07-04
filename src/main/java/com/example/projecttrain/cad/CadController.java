package com.example.projecttrain.cad;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cad")
public class CadController {
    private final CadService cadService;

    @PostMapping("/addFile")
    public ResponseEntity<CadEntity> saveCadFile(@RequestParam("file") MultipartFile file) throws Exception {
        CadEntity cad = cadService.getBookMarks(file);
        return new ResponseEntity<>(cad, HttpStatus.OK);
    }

    @PostMapping("/upload/local")
    public void uploadLocal(@RequestParam("file")MultipartFile file) {
        cadService.uploadToLocal(file);
    }
}
