package com.proyecto.reusa.services.products.serializers;

public class ImageProductDTO {
    private String path;
    private byte[] photo;

    public ImageProductDTO(String path, byte[] photo) {
        this.path = path;
        this.photo = photo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
