package com.dyspersja.mp3metadataeditor.metadata;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class Mp3File {

    private ID3v1Metadata id3v1;
    private ID3v2Metadata id3v2;

    private byte[] audioData;
    private File file;

    public Mp3File(File mp3File) {
        id3v1 = new ID3v1Metadata(mp3File);
        id3v2 = new ID3v2Metadata(mp3File);
    }

    public void saveFile() {

    }
}
