package org.example;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Frequency implements Runnable{
    private boolean onSwitch = true;
    private byte[] buf;
    SourceDataLine sDataLine;
    AudioFormat audioFormat;
    private String name;
    private final float frequency;
    long timeToSleep;

    public Frequency (String name, float frequency ) {
        this.name = name;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }
    public float getFrequency() {
        return frequency;
    }

    public void startSound(){
        generateSineWave(this);
        while(onSwitch){
           playSound();
            try {
                Thread.sleep(timeToSleep);
            }catch(Exception e){
                System.out.println("Ultragay");
                throw new RuntimeException(e);
            }
        }
            sDataLine.drain();
            sDataLine.close();
    }

    public void playSound(){
        sDataLine.start();
        sDataLine.write(buf, 0, buf.length);

    }

    public void generateSineWave(Frequency frequency){
        float SAMPLE_RATE = 44100f;
        buf = new byte[(int) SAMPLE_RATE];
        timeToSleep = (long) (buf.length / (200 * Math.PI));
        final int vol = 1;
        final double SAMPLE_RATED_DIV_BY_100 = SAMPLE_RATE / 100.0;

        if (frequency.getFrequency() <= 0) {
            throw new IllegalArgumentException("Frequency <= 0 hz");
        }

        for (int i = 0; i < buf.length; i++) {
            double angle = i / (SAMPLE_RATE / frequency.getFrequency()) * 2.0 * Math.PI;
            buf[i] = (byte) (Math.sin(angle) * 127.0 * vol);
        }

// shape the front and back 10ms of the wave form
        for (int i = 0; i < SAMPLE_RATED_DIV_BY_100 && i < buf.length / 2; i++) {
            buf[i] = (byte) (buf[i] * i / SAMPLE_RATED_DIV_BY_100);
            buf[buf.length - 1 - i] =
                    (byte) (buf[buf.length - 1 - i] * i / SAMPLE_RATED_DIV_BY_100);
        }

        audioFormat = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);

        try {
            sDataLine = AudioSystem.getSourceDataLine(audioFormat);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            sDataLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        startSound();
    }
}
