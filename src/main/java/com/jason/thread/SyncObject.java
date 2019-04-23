package com.jason.thread;

import java.util.Objects;

/**
 * @author : yusik
 * @date : 2019-04-20
 */
public class SyncObject {

    long serial;
    String text;

    public SyncObject(long serial, String text) {
        this.serial = serial;
        this.text = text;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SyncObject)) return false;
        SyncObject that = (SyncObject) o;
        return getSerial() == that.getSerial() &&
                getText().equals(that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSerial(), getText());
    }
}
