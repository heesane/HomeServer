package hhs.server.home_server.service;

import hhs.server.home_server.model.FileEntity;
import hhs.server.home_server.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final String fileDir = "/Users/heesang/home/";

    private final FileRepository fileRepository;

    public Long saveFile(MultipartFile files) throws IOException {
        if(files.isEmpty()){
            return null;
        }

        String origName = files.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = origName.substring(origName.lastIndexOf("."));
        String savedName = uuid + extension;
        String savedPath = fileDir + savedName;

        FileEntity file = FileEntity.builder()
                .orgNm(origName)
                .savedNm(savedName)
                .savedPath(savedPath)
                .build();

        files.transferTo(new File(savedPath));

        FileEntity savedFile = fileRepository.save(file);

        return savedFile.getId();
    }


}
