package com.dyspersja.mp3metadataeditor.mp3.metadata;

import com.dyspersja.mp3metadataeditor.view.errorscreen.ErrorScreenProvider;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ID3v1Metadata {

    private static final int TAG_LENGTH = 3;
    private static final int TITLE_LENGTH = 30;
    private static final int ARTIST_LENGTH = 30;
    private static final int ALBUM_LENGTH = 30;
    private static final int YEAR_LENGTH = 4;
    private static final int COMMENT_LENGTH = 28;
    private static final int FULL_COMMENT_LENGTH = 30;

    private static final int TAG_OFFSET = 0;
    private static final int TITLE_OFFSET = 3;
    private static final int ARTIST_OFFSET = 33;
    private static final int ALBUM_OFFSET = 63;
    private static final int YEAR_OFFSET = 93;
    private static final int COMMENT_OFFSET = 97;

    private String title;
    private String artist;
    private String album;
    private String year;
    private String comment;
    private int track;

    private boolean isPresent = false;

    public ID3v1Metadata(File mp3File) {
        // Check if the file is large enough to contain ID3v1 metadata
        if (mp3File.length() < 128) return;

        // Read last 128 bytes of the file and check if they contain the ID3v1 tag
        byte[] buffer = readMetadataBytes(mp3File);
        if (!isTagPresent(buffer)) return;

        // Set the flag indicating that ID3v1 is present and extract the metadata
        this.isPresent = true;
        parseMetadata(buffer);
    }

    private byte[] readMetadataBytes(File mp3File) {
        byte[] buffer = new byte[128];

        try (RandomAccessFile raf = new RandomAccessFile(mp3File, "r")) {
            raf.seek(raf.length() - 128);
            raf.readFully(buffer);
        } catch (IOException e) {
            ErrorScreenProvider.displayFileErrorWindow(e);
        }
        return buffer;
    }

    private boolean isTagPresent(byte[] buffer) {
        return buffer[0] == 'T' || buffer[1] == 'A' || buffer[2] == 'G';
    }

    private void parseMetadata(byte[] metadata) {
        this.title = new String(metadata, TITLE_OFFSET, TITLE_LENGTH).trim();
        this.artist = new String(metadata, ARTIST_OFFSET, ARTIST_LENGTH).trim();
        this.album = new String(metadata, ALBUM_OFFSET, ALBUM_LENGTH).trim();
        this.year = new String(metadata, YEAR_OFFSET, YEAR_LENGTH).trim();
        this.comment = new String(metadata, COMMENT_OFFSET, COMMENT_LENGTH).trim();
        this.track = metadata[125] == 0 ? metadata[126] & 0xFF : -1;
    }
}
