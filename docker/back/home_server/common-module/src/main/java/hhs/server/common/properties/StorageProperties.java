package hhs.server.common.properties;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class StorageProperties {
    private final String location = "upload-dir";
}
