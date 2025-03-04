package com.jsamkt.learn.functional.practical;

public class Button {
    private final String label;
    private Runnable onClickHandler;

    public Button(String label) {
        this.label = label;
    }

    public void setOnClick(Runnable handler) {
        this.onClickHandler = handler;
    }

    public void click() {
        if (onClickHandler != null) {
            onClickHandler.run();
        }
    }
}
