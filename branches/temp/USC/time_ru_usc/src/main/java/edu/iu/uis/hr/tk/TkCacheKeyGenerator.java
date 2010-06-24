package edu.iu.uis.hr.tk;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInvocation;
import org.springmodules.cache.key.CacheKeyGenerator;

public class TkCacheKeyGenerator implements CacheKeyGenerator {

  public final Serializable generateKey(MethodInvocation methodInvocation) {
    Object[] methodArguments = methodInvocation.getArguments();
    StringBuffer key = new StringBuffer(methodInvocation.getMethod().getName());
    if (methodArguments != null) {
      for (int i = 0; i < methodArguments.length; i++) {
        Object methodArgument = methodArguments[i];
        key.append(methodArgument.hashCode());
      }
    }
    Serializable cacheKey = key.toString();
    return cacheKey;
  }
}