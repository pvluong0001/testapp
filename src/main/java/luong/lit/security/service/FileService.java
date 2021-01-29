package luong.lit.security.service;

import luong.lit.security.exception.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class FileService {
    public void uploadFile(MultipartFile file) {
        Path resourceDirectory = Paths.get("src", "main", "resources", "public/uploads");
        String uploadDir = resourceDirectory.toFile().getAbsolutePath();

        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(
                            new Date().getTime() + "_" + file.getOriginalFilename()
                    ));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }
}
