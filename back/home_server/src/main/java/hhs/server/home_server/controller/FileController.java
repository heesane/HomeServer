package hhs.server.home_server.controller;

import hhs.server.home_server.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {
    private final FileService fileService;

    @GetMapping("/upload")
    public String testUploadForm(){
        return "test/uploadTest";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("files")List<MultipartFile> files) throws IOException{
        fileService.saveFile(file);

        for (MultipartFile multipartFile : files){
            fileService.saveFile(multipartFile);
        }
        return "redirect:/";
    }

}
