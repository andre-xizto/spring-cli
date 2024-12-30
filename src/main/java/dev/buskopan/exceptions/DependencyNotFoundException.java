package dev.buskopan.exceptions;

public class DependencyNotFoundException extends RuntimeException{
    public DependencyNotFoundException(String msg) {
        super(msg);
    }
}
