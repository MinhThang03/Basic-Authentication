package Intern.model.playload.response;

import Intern.common.ErrorDefine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.LinkedHashMap;
import java.util.Map;

public class ResponeHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj, Boolean success) {
        int codeValue = status.value();
        StringBuilder message = new StringBuilder();
        StringBuilder code = new StringBuilder();
        ErrorDefine[] errors = ErrorDefine.values();
        for (ErrorDefine error : errors){
            String keyEnum = error.name().split("_")[1];
            if (keyEnum.equals(String.valueOf(codeValue))){
                message.append(error.getError());
                code.append(codeValue);
            }
        }

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("success", success);
        map.put("code", code.toString());
        map.put("message", message.toString());
        map.put("data", responseObj);
        map.put("popup", new PopupResponse());
        return new ResponseEntity<Object>(map,status);
    }
}
