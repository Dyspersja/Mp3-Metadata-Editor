package com.dyspersja.mp3metadataeditor.mp3;

import com.dyspersja.mp3metadataeditor.mp3.metadata.ID3v1Metadata;
import com.dyspersja.mp3metadataeditor.mp3.metadata.ID3v2Metadata;
import com.dyspersja.mp3metadataeditor.view.errorscreen.ErrorScreenProvider;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

@Getter
@Setter
public class Mp3File {

    private ID3v1Metadata id3v1;
    private ID3v2Metadata id3v2;

    private byte[] audioData;
    private File file;

    public Mp3File(File mp3File) {
        this.file = mp3File;

        id3v1 = new ID3v1Metadata(mp3File);
        id3v2 = new ID3v2Metadata(mp3File);

        retrieveAudioData();
    }

    private void retrieveAudioData() {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long fileLength = raf.length();
            int offset = id3v2.getMetadataLength();

            audioData = id3v1.isPresent()
                    ? new byte[(int) (fileLength - offset) - 128]
                    : new byte[(int) (fileLength - offset)];

            raf.seek(offset);
            raf.readFully(audioData);
        } catch (IOException e) {
            ErrorScreenProvider.displayFileReadErrorWindow(e);
        }
    }

    public void saveFile() {
        try (FileOutputStream fos = new FileOutputStream(file, false)) {
            // Write ID3v2 metadata at the beginning of the file
            if (id3v2.isPresent()) fos.write(id3v2.getMetadata());

            // Write audio data
            fos.write(audioData);

            // Write ID3v1 metadata at the end of the file
            if (id3v1.isPresent()) fos.write(id3v1.getMetadata());
        } catch (IOException e) {
            ErrorScreenProvider.displayFileWriteErrorWindow(e);
        }
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
