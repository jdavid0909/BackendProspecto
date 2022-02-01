package com.sti.project.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageKey {

    //Not found.
    PROSPECTO_NOT_FOUND("prospecto-not-found"),

    //Student Service
    INVALID_PROSPECTO_SUBJECT("prospecto-subject-invalid");

    public final String key;

    public String getKey(){
        return this.key;
    }
}
