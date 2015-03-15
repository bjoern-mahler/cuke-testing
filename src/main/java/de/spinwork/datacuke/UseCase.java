package de.spinwork.datacuke;

/**
 * A usecase encapsulates all client methods to communicate with some destination part e.g. a server communication
 * via http for better usage in cucumber functional tests.
 */
public interface UseCase<T> {

    /**
     * @return data response object
     */
    public T getResponse();


}
