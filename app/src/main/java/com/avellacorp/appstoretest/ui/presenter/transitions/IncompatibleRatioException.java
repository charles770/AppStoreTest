package com.avellacorp.appstoretest.ui.presenter.transitions;

public class IncompatibleRatioException extends RuntimeException {

    private static final long serialVersionUID = 234608108593115395L;

    public IncompatibleRatioException() {
        super("No se puede realizar efectos en vistas con distintas proporciones de aspecto!");
    }

}
