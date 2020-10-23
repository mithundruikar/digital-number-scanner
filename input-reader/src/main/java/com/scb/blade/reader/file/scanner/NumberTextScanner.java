package com.scb.blade.reader.file.scanner;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.core.io.ClassPathResource;
import rx.Observable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NumberTextScanner {
    private String filePath;
    private BufferedReader bufferedReader;

    public NumberTextScanner(String filePath) {
        this.filePath = filePath;
    }

    public void init() throws IOException {
        try {
            ClassPathResource classPathResource = new ClassPathResource(this.filePath);
            this.bufferedReader = new BufferedReader(new FileReader(classPathResource.getFile()));
        } catch(FileNotFoundException fe) {
            this.bufferedReader = new BufferedReader(new FileReader(this.filePath));
        }
    }

    public Observable<String> observe() throws IOException {
        init();
        return Observable.create( observer -> {
            try {
                String nextLine = null;
                while ((nextLine = readNextLine()) != null) {
                    observer.onNext(nextLine);
                }
                observer.onCompleted();
            } catch(IOException io) {
                observer.onError(io);
                close();
            }
        });

    }

    public String readNextLine() throws IOException {
        return this.bufferedReader.readLine();
    }

    public void close()  {
        try {
            if(this.bufferedReader != null) {
                this.bufferedReader.close();
            }
        } catch(IOException io) {
            io.printStackTrace();
        }

    }

    @VisibleForTesting
    protected BufferedReader getBufferedReader() {
        return bufferedReader;
    }
}
