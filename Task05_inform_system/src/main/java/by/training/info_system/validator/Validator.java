package by.training.info_system.validator;

public interface Validator<T> {
    boolean validate(T object);
}
