package com.dyspersja.mp3metadataeditor.metadata;

import com.dyspersja.mp3metadataeditor.view.errorscreen.ErrorScreenProvider;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
        // Check if the file is large enough to contain ID3v2 metadata
        if (mp3File.length() < 10) return;

        try {
            // Read first 10 bytes of the file and check if they contain the ID3v2 header
            byte[] header = readMetadataBytes(mp3File, 10);
            if (!isTagPresent(header)) return;
            this.isPresent = true;

            // Parse the ID3v2 metadata length
            int metadataLength = parseMetadataLength(header);

            // Parse the metadata and retrieve the mp3 file data from it
            byte[] metadata = readMetadataBytes(mp3File, metadataLength);
            List<byte[]> frames = parseMetadata(metadata);
            retrieveDataFromFrames(frames);
        } catch (IOException e) {
            ErrorScreenProvider.displayFileErrorWindow(e);
        }
    }

    private byte[] readMetadataBytes(File mp3File, int metadataLength) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(mp3File, "r")) {
            byte[] metadata = new byte[metadataLength];
            raf.read(metadata);
            return metadata;
        }
    }

    private boolean isTagPresent(byte[] header) {
        return header[0] == 'I' && header[1] == 'D' && header[2] == '3';
    }

    private int parseMetadataLength(byte[] header) {
        int metadataLength = (header[6] & 0x7F) << 21 |
                (header[7] & 0x7F) << 14 |
                (header[8] & 0x7F) << 7 |
                (header[9] & 0x7F);
        return metadataLength + 10; // Add header size
    }

    private List<byte[]> parseMetadata(byte[] metadata) {
        List<byte[]> frames = new ArrayList<>();

        int offset = 10;
        while (offset < metadata.length) {
            if (metadata[offset] == 0) break;

            int frameSize = parseFrameLength(metadata, offset);

            frames.add(Arrays.copyOfRange(metadata, offset, offset + frameSize));
            offset += frameSize;
        }
        return frames;
    }

    private int parseFrameLength(byte[] metadata, int offset) {
        int frameSize = (metadata[offset + 4] & 0xFF) << 24 |
                (metadata[offset + 5] & 0xFF) << 16 |
                (metadata[offset + 6] & 0xFF) << 8 |
                (metadata[offset + 7] & 0xFF);
        return frameSize + 10; // Add header size
    }

    private void retrieveDataFromFrames(List<byte[]> frames) {
        for (byte[] frame : frames) {
            String frameTag = getFrameTag(frame);
            ID3v2FrameTag tag = ID3v2FrameTag.fromString(frameTag);

            switch (tag) {
                case ALBUM_ARTIST -> this.albumArtist = getText(getFrameData(frame));
                case ALBUM -> this.album = getText(getFrameData(frame));
                case SONG_ARTIST -> this.songArtist = getText(getFrameData(frame));
                case TITLE -> this.title = getText(getFrameData(frame));
                case TRACK -> this.track = getText(getFrameData(frame));
                case YEAR -> this.year = getText(getFrameData(frame));
                case ALBUM_COVER -> this.albumCover = retrieveImage(getFrameData(frame));
            }
        }
    }

    private String getFrameTag(byte[] frame) {
        return new String(frame, 0, 4);
    }

    private byte[] getFrameData(byte[] frame) {
        return Arrays.copyOfRange(frame, 11, frame.length);
    }

    private String getText(byte[] frameData) {
        Charset charset = StandardCharsets.UTF_8;
        if (frameData.length >= 2 &&
                frameData[0] == (byte) 0xFF &&
                frameData[1] == (byte) 0xFE) {
            charset = StandardCharsets.UTF_16LE;
        } else if (frameData.length >= 2 &&
                frameData[0] == (byte) 0xFE &&
                frameData[1] == (byte) 0xFF) {
            charset = StandardCharsets.UTF_16BE;
        }

        return new String(frameData, charset);
    }

    private byte[] retrieveImage(byte[] frameData) {
        int index = IntStream.range(0, frameData.length - 2)
                .filter(i -> (frameData[i] & 0xFF) == 255 &&
                        (frameData[i + 1] & 0xFF) == 216 &&
                        (frameData[i + 2] & 0xFF) == 255)
                .findFirst()
                .orElse(-1);

        return index != -1
                ? Arrays.copyOfRange(frameData, index, frameData.length - index)
                : null;
    }
}
