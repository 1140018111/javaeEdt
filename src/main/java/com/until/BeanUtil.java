package com.until;





import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Modifier;

/**
 * com.until
 * dxl
 *
 * @author DL
 * @create 2020-06-19
 */
public class BeanUtil {
	/**
	      * 大小写可以忽略
	      * 下划线 _ 被忽略
	      * NULL值和空字符串不会覆盖新值
	      *
	      * @param source
	      * @param target
	      * @param <T>
	      * @return   CacheFieldMap
	      */
	public static <T> T transferObjectIgnoreCase(Object obj, Class clz) {
		T result = null;
		try {
			if (obj != null && !obj.equals("")) {
				result = (T) clz.newInstance();
				//获取目标类的属性集合
				Map<String, Field> destPropertyMap = new HashMap<>();
				for (Field curField : clz.getDeclaredFields()) {
					destPropertyMap.put(curField.getName().toLowerCase(), curField);
				}
				//拷贝属性
				for (Field curField : obj.getClass().getDeclaredFields()) {
					Field targetField = destPropertyMap.get(curField.getName().toLowerCase());
					if (targetField != null) {
						targetField.setAccessible(true);
						curField.setAccessible(true);
						targetField.set(result, curField.get(obj));
					}
				}
			}
		} catch (Exception e1) {
			return null;
		}
		return result;
	}
	/**
	 * 通过 ASM反射 速度比 Spring BeanUtils.copyProperties(source,target) 快一倍
	 * 大小写可以忽略
	 * 下划线 _ 被忽略
	 *
	 * @param source 源对象
	 * @param target 目标实例化对象
	 * @param <T>
	 * @return
	 */
	public static <T> T copyPropertiesASM( Object source, Object target ) {
		MethodAccess sourceMethodAccess = CacheMethodAccess.getMethodAccess(source.getClass());
		MethodAccess targetMethodAccess = CacheMethodAccess.getMethodAccess(target.getClass());
		Map<String, String> sourceGet = CacheAsmFiledMethod.getMethod("get", source.getClass());
		Map<String, String> targetSet = CacheAsmFiledMethod.getMethod("set", target.getClass());
		CacheFieldMap.getFieldMap(target.getClass()).keySet().forEach((it) -> {
			String sourceIndex = sourceGet.get(it);
			if (sourceIndex != null) {
				Object value = sourceMethodAccess.invoke(source, sourceIndex);
				String setIndex = targetSet.get(it);
				targetMethodAccess.invoke(target, setIndex, value);
			}
		});
		return (T) target;
	}

	private static class CacheAsmFiledMethod {
		private static Map<String, Map<String, String>> cacheGetMethod = new HashMap<>();
		private static Map<String, Map<String, String>> cacheSetMethod = new HashMap<>();

		private static Map<String, String> getMethod(String type, Class clazz) {
			MethodAccess methodAccess = CacheMethodAccess.getMethodAccess(clazz);
			Map<String, Field> allFields = CacheFieldMap.getFieldMap(clazz);
			Map<String, String> result = null;
			if (type.equals("get")) {
				result = cacheGetMethod.get(clazz.getName());
			} else if (type.equals("set")) {
				result = cacheSetMethod.get(clazz.getName());
			}
			if (result == null) {
				synchronized (CacheAsmFiledMethod.class) {
					if (result == null) {
						Map<String, String> set = new HashMap<>();
						Map<String, String> get = new HashMap<>();
						allFields.values().forEach((it) -> {
							//判断是否是静态
							if (!Modifier.isStatic(it.getModifiers())) {
								//首字母大写
								char[] f = it.getName().toCharArray();

								if (f[0]>= 'a' && f[0] <= 'z') {
									f[0] -= 32;
								}

								String fieldName = new String(f);
								char[] chars = "U".toCharArray();

								get.put(fieldName.toLowerCase().replace("_", ""), "get" + fieldName);
								set.put(fieldName.toLowerCase().replace("_", ""), "set" + fieldName);
							}
						});
						cacheGetMethod.put(clazz.getName(), get);
						cacheSetMethod.put(clazz.getName(), set);
						if (type.equals("get")) {
							result = cacheGetMethod.get(clazz.getName());
						} else if (type.equals("set")) {
							result = cacheSetMethod.get(clazz.getName());
						}
					}
				}
			}
			return result;
		}
	}

	private static class CacheMethodAccess {

		private CacheMethodAccess() {
		}

		private static Map<String, MethodAccess> cache = new HashMap<>();

		private static MethodAccess getMethodAccess(Class clazz) {
			MethodAccess result = cache.get(clazz.getName());
			if (result == null) {
				synchronized (CacheMethodAccess.class) {
					if (result == null) {
						cache.put(clazz.getName(), MethodAccess.get(clazz));
						result = cache.get(clazz.getName());
					}
				}
			}
			return result;
		}
	}

	private static class CacheFieldMap {
		private static Map<String, Map<String, Field>> cacheMap = new HashMap<>();

		private static Map<String, Field> getFieldMap(Class clazz) {
			Map<String, Field> result = cacheMap.get(clazz.getName());
			if (result == null) {
				synchronized (CacheFieldMap.class) {
					if (result == null) {
						Map<String, Field> fieldMap = new HashMap<>();
						for (Field field : clazz.getDeclaredFields()) {
							fieldMap.put(field.getName().toLowerCase().replace("_", ""), field);
						}
						cacheMap.put(clazz.getName(), fieldMap);
						result = cacheMap.get(clazz.getName());
					}
				}
			}
			return result;
		}
	}


}