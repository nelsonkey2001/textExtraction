package com.amgen.datamarketplace;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TextExtraction {

    private Metadata metadata;
    private TikaConfig tikaConfig;
    private ArrayList result;
    private Integer numFiles;
    private String outputPath;

    public TextExtraction() {
        metadata = new Metadata();
        tikaConfig = TikaConfig.getDefaultConfig();
        result = new ArrayList();
        numFiles = 0;
    }



    public List<String> parseUsingAutoDetect(String input, String output) throws Exception {
        this.outputPath = output;
        try (Stream<Path> filePathStream=Files.walk(Paths.get(input))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                       fileWalk(filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TikaException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        return result;
    }

    public void fileWalk (Path filePath) throws IOException, TikaException, SAXException {

        AutoDetectParser parser = new AutoDetectParser(tikaConfig);
        ContentHandler handler = new BodyContentHandler();
        TikaInputStream stream = TikaInputStream.get(new File(filePath.toString()), metadata);
        parser.parse(stream, handler, metadata, new ParseContext());

        createTextFile(filePath.getFileName().toString(),handler.toString());
        result.add(handler.toString());

    }

    public void createTextFile(String fileName, String content) throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter(outputPath+"/"+fileName.replace(".pdf","")+".txt"));
        out.write(content);
        out.close();

    }
}
