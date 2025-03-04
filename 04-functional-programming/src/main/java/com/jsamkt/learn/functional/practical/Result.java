package com.jsamkt.learn.functional.practical;

import java.util.function.Function;

public class Result<T> {
    private final T value;
    private final String error;
    private final boolean success;

    private Result(T value, String error, boolean success) {
        this.value = value;
        this.error = error;
        this.success = success;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null, true);
    }

    public static <T> Result<T> failure(String error) {
        return new Result<>(null, error, false);
    }

    public <R> Result<R> map(Function<T, R> fn) {
        if (success) {
            return Result.success(fn.apply(value));
        } else {
            return Result.failure(error);
        }
    }

    public <R> Result<R> flatMap(Function<T, Result<R>> fn) {
        if (success) {
            return fn.apply(value);
        } else {
            return Result.failure(error);
        }
    }

    @Override
    public String toString() {
        return success ? "Success: " + value : "Failure: " + error;
    }
}
