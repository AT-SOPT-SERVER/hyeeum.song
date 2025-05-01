
package org.sopt.exception;

public class MethodNotAllowedException extends CustomException {
    public MethodNotAllowedException() {
        super(Error.METHOD_NOT_ALLOWED_ERROR);
    }
}
