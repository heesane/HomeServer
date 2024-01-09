package hhs.server.home_server.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TestService {

    private final String UPLOAD_DIR = "./uploads/";

    public void uploadFile(MultipartFile file) throws IOException {
        // 업로드 디렉토리가 없다면 생성
        if (!Files.exists(Paths.get(UPLOAD_DIR))) {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        }

        // 파일 저장 경로 설정
        Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());

        // 파일 저장
        Files.write(filePath, file.getBytes());
    }
}
