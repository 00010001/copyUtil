package sample;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class App {

    private static final String srcDir = "e:\\test\\a\\";
    private static final String targetDir = "e:\\test\\b\\";

    private static final String replaceWith = "2021";
    private static final List<String> versions = Arrays.asList("2020", "2019");


    public static void main(String[] args) {

        try (Stream<Path> paths = Files.walk(Paths.get(srcDir))) {
            paths.forEach(p -> {
                        try {

                            File source = p.toFile();
                            if(!source.isDirectory()){
                                File target = new File(p.toAbsolutePath().toString().replace(srcDir,targetDir));
                                FileUtils.copyFile(source, target);

                                String fileContext = FileUtils.readFileToString(target);
                                for (String version : versions) {
                                    fileContext = fileContext.replaceAll(version, replaceWith);
                                }
                                FileUtils.write(target, fileContext);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
