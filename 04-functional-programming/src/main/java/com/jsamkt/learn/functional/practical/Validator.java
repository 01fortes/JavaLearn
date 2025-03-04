package com.jsamkt.learn.functional.practical;

@FunctionalInterface
public interface Validator<T> {
    ValidationResult validate(T t);

    default Validator<T> and(Validator<T> other) {
        return t -> {
            ValidationResult result = this.validate(t);
            return result.isValid() ? other.validate(t) : result;
        };
    }
}