package com.dyspersja.mp3metadataeditor.metadata;

import java.io.File;

public class ID3v1Metadata {

    private String title;
    private String artist;
    private String album;
    private String year;
    private String comment;
    private int track;

    private boolean isPresent = false;

    public ID3v1Metadata(File mp3File) {

    }
}
