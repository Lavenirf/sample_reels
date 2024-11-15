package org.example.labsystem.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.labsystem.pojo.common.CodeState;
@EqualsAndHashCode(callSuper = true)
@Data
public class ReadableException extends RuntimeException {
    protected CodeState codeState;
    public ReadableException(CodeState codeState) {
        this.codeState = codeState;
    }
}
