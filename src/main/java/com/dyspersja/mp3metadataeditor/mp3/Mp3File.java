package com.dyspersja.mp3metadataeditor.mp3;

import com.dyspersja.mp3metadataeditor.mp3.metadata.ID3v1Metadata;
import com.dyspersja.mp3metadataeditor.mp3.metadata.ID3v2Metadata;
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

    public void createID3v1() {
        id3v1.setPresent(true);
    }

    public void createID3v2() {
        id3v2.setPresent(true);
    }

    public boolean isID3v1Present() {
        return id3v1.isPresent();
    }

    public boolean isID3v2Present() {
        return id3v2.isPresent();
    }
}
