package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public interface MethMappingAble {
    default Object methodsMapping(Object objSrc, Object objDest)
            throws InvocationTargetException, IllegalAccessException {
        Method[] methods = objSrc.getClass().getDeclaredMethods();
        Map<String, Method> namePerMethod = new HashMap<>();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") || methodName.startsWith("set")) {
                namePerMethod.put(methodName, method);
            }
        }
        for (String methodName : namePerMethod.keySet()) {
            if (methodName.startsWith("get")) {
                Method getMethod = namePerMethod.get(methodName);
                Method setMethod = namePerMethod.get(methodName.replace("get", "set"));
                if (setMethod == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Impossible invoke set method from object : " + objDest
                                    + ", Check set and get pairs.");
                }
                var newValue = getMethod.invoke(objSrc);
                if (newValue != null) {
                    setMethod.invoke(objDest, newValue);
                }
            }
        }
        return objDest;
    }
}
