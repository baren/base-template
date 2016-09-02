package com.enwu.mb.common.cache;

import java.lang.reflect.Method;

/**
 * Created by user on 16/8/2.
 */
public interface IBeforeCheckFunc {


    Object beforeCheck(Object target, Method method, Object... params);
}
