package net.thucydides.core.util;

import net.thucydides.core.guice.Injectors;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class WhenLoadingPreferencesFromALocalPropertiesFile {

    Logger LOGGER = LoggerFactory.getLogger(WhenLoadingPreferencesFromALocalPropertiesFile.class);

    @Rule
    public ExtendedTemporaryFolder temporaryFolder = new ExtendedTemporaryFolder();

    File homeDirectory;
    File thucydidesPropertiesFile;
    EnvironmentVariables environmentVariables;
    PropertiesFileLocalPreferences localPreferences;

    @Before
    public void setupDirectories() throws IOException {
        environmentVariables = new MockEnvironmentVariables();
        localPreferences = new PropertiesFileLocalPreferences(environmentVariables);

        homeDirectory = temporaryFolder.newFolder();
        localPreferences.setHomeDirectory(homeDirectory);
    }

    @Test
    public void the_default_preferences_directory_is_the_users_home_directory() throws Exception {
        PropertiesFileLocalPreferences localPreferences = new PropertiesFileLocalPreferences(environmentVariables);

        String homeDirectory = System.getProperty("user.home");

        assertThat(localPreferences.getHomeDirectory().getPath(), is(homeDirectory));
    }

    @Test
    public void should_load_property_values_from_local_preferences() throws Exception {
        writeToPropertiesFile("webdriver.driver = opera");

        localPreferences.setHomeDirectory(homeDirectory);

        localPreferences.loadPreferences();

        assertThat(environmentVariables.getProperty("webdriver.driver"), is("opera"));
    }

    @Test
    public void home_properties_should_override_classpath_properties() throws Exception {
        writeToPropertiesFile("test.property = reset");

        localPreferences.setHomeDirectory(homeDirectory);

        localPreferences.loadPreferences();

        assertThat(environmentVariables.getProperty("test.property"), is("reset"));
    }

    @Test
    public void local_preferences_should_not_override_system_preferences() throws Exception {
        writeToPropertiesFile("webdriver.driver = opera");

        environmentVariables.setProperty("webdriver.driver", "iexplorer");
        localPreferences.setHomeDirectory(homeDirectory);

        localPreferences.loadPreferences();

        assertThat(environmentVariables.getProperty("webdriver.driver"), is("iexplorer"));
    }

    @Test
    public void should_load_preferences_from_a_designated_properties_file_if_specified() throws Exception {
        writeToPropertiesFileCalled("myprops.properties", "webdriver.driver = safari");

        System.setProperty("properties", "myprops.properties");

        localPreferences.setHomeDirectory(homeDirectory);

        localPreferences.loadPreferences();

        assertThat(environmentVariables.getProperty("webdriver.driver"), is("safari"));

        System.clearProperty("properties");
    }

    @Test
    public void should_load_preferences_from_a_designated_properties_filepath_if_specified() throws Exception {
        String fullPath = writeToPropertiesFileCalled("myprops.properties", "webdriver.driver = safari");

        System.setProperty("properties", fullPath);

        localPreferences.setHomeDirectory(homeDirectory);

        localPreferences.loadPreferences();

        assertThat(environmentVariables.getProperty("webdriver.driver"), is("safari"));

        System.clearProperty("properties");
    }

    @Test
    public void should_ignore_preferences_if_specified_file_does_not_exist() throws Exception {

        System.setProperty("properties", "noexistant.properties");

        localPreferences.setHomeDirectory(homeDirectory);

        localPreferences.loadPreferences();

        assertThat(environmentVariables.getProperty("webdriver.driver"), is(nullValue()));

        System.clearProperty("properties");

    }

    @Test
    public void local_preferences_should_be_loaded_with_the_environment_variables() {
        EnvironmentVariables loadedEnvironmentVariables = Injectors.getInjector().getProvider(EnvironmentVariables.class).get() ;
        assertThat(loadedEnvironmentVariables.getProperty("test.property"), is("set"));
    }


    private String writeToPropertiesFile(String... lines) throws IOException, InterruptedException {
        return writeToPropertiesFileCalled("thucydides.properties", lines);
    }

    @Test
    public void should_load_property_values_from_typesafe_config() throws Exception {
        localPreferences.setHomeDirectory(homeDirectory);

        localPreferences.loadPreferences();

        assertThat(environmentVariables.getProperty("serenity.logging"), is("VERBOSE"));
    }

    @Test
    public void should_load_arbitrary_property_values_from_typesafe_config() throws Exception {
        localPreferences.setHomeDirectory(homeDirectory);

        localPreferences.loadPreferences();

        assertThat(environmentVariables.getProperty("environment.uat"), is("uat-server"));
    }

    @Test
    public void users_can_define_optional_custom_properties() {

        // WHEN
        environmentVariables.setProperty("env","QA");

        // THEN
        assertThat(environmentVariables.optionalProperty("env").isPresent(), is(true));

        // BUT
        assertThat(environmentVariables.optionalProperty("undefined").isPresent(), is(false));

    }

    @SuppressWarnings("static-access")
    private String writeToPropertiesFileCalled(String filename, String... lines) throws IOException, InterruptedException {
        thucydidesPropertiesFile = new File(homeDirectory, filename);
        thucydidesPropertiesFile.setReadable(true);
        thucydidesPropertiesFile.setWritable(true);
        thucydidesPropertiesFile.setExecutable(true);

        try {
        	thucydidesPropertiesFile.createNewFile();
        } catch (IOException e) {
            LOGGER.error("Exception during writing properties",e);
		}
        Thread.currentThread().sleep(100);
        FileWriter outFile = new FileWriter(thucydidesPropertiesFile);
        PrintWriter out = new PrintWriter(outFile);
        for(String line : lines) {
            out.println(line);
        }
        out.close();
        return thucydidesPropertiesFile.getAbsolutePath();
    }
}
