package com.dyspersja.mp3metadataeditor.metadata;

import java.io.File;

public class ID3v2Metadata {

    private String albumArtist;
    private String album;
    private String songArtist;
    private String title;
    private String track;
    private String year;
    private byte[] albumCover;

    private boolean isPresent = false;

    public ID3v2Metadata(File mp3File) {

    }
}
