package com.dyspersja.mp3metadataeditor.metadata;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class Mp3File {

    private byte[] audioData;
    private File file;

    public Mp3File(File mp3File) {

    }

    public void saveFile() {

    }
}
