package bll.validators;

/**
 * Interfata folosita pentru clasele validatoare de obiecte
 *
 * @param <T> tipul obiectului care urmeaza sa fie verificat
 */
public interface Validator<T> {
    public void validate(T t) throws Exception;
}
