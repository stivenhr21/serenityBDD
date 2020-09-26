package net.thucydides.core.screenshots;

import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.util.EnvironmentVariables;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class ScreenshotDigest {

    public ScreenshotDigest(EnvironmentVariables environmentVariables, BlurLevel blurLevel) {
        this.environmentVariables = environmentVariables;
        this.blurLevel = Optional.ofNullable(blurLevel);
    }

    private final Optional<BlurLevel> blurLevel;
    private final EnvironmentVariables environmentVariables;

    public String forScreenshot(File screenshotFile) throws IOException {
        try(InputStream screenshot = new FileInputStream(screenshotFile)){
            return DigestUtils.sha256Hex(screenshot)
                + "_" + blurLevel.orElse(BlurLevel.NONE).toString()
                + optionalWidth()
                + ".png";
        }
    }

    private String optionalWidth() {
        return ThucydidesSystemProperty.THUCYDIDES_RESIZED_IMAGE_WIDTH.from(environmentVariables,"");
    }
}
