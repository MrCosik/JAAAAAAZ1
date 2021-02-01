package pl.edu.pjwstk.jaz.zad2.request;

import java.util.List;

public class PhotoRequest {
    private List<String> newPhotos;

    public PhotoRequest() {
    }

    public PhotoRequest(List<String> newPhotos) {
        this.newPhotos = newPhotos;
    }

    public List<String> getNewPhotos() {
        return newPhotos;
    }

    public void setNewPhotos(List<String> newPhotos) {
        this.newPhotos = newPhotos;
    }
}
