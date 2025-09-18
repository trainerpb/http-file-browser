package com.soham.lnd.usecases.filebrowser.simple_http_file_browser.model;

public class FileResult {
    private String path;
    private boolean isDirectory;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public FileResult(String path, boolean isDirectory) {
        this.path = path;
        this.isDirectory = isDirectory;
    }
}
