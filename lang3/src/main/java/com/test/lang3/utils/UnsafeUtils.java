package com.test.lang3.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe工具类
 */
@SuppressWarnings("unused")
public class UnsafeUtils {

	public static final Unsafe UNSAFE;
	public static final boolean SUPPORT_UNSAFE;

	static {
		Unsafe unsafe = null;
		try {
			Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafeField.setAccessible(true);
			unsafe = (Unsafe) theUnsafeField.get(null);
		} catch (Throwable ignored) {
			// ignored
		}
		UNSAFE = unsafe;
		SUPPORT_UNSAFE = unsafe != null;
	}

	public static Object getObject(Object o, long offset) {
		return UNSAFE.getObject(o, offset);
	}

	public static long getLong(Object o, long offset) {
		return UNSAFE.getLong(o, offset);
	}

	public static int getInt(Object o, long offset) {
		return UNSAFE.getInt(o, offset);
	}

	public static short getShort(Object o, long offset) {
		return UNSAFE.getShort(o, offset);
	}

	public static byte getByte(Object o, long offset) {
		return UNSAFE.getByte(o, offset);
	}

	public static float getFloat(Object o, long offset) {
		return UNSAFE.getFloat(o, offset);
	}

	public static double getDouble(Object o, long offset) {
		return UNSAFE.getDouble(o, offset);
	}

	public static boolean getBoolean(Object o, long offset) {
		return UNSAFE.getBoolean(o, offset);
	}

	public static char getChar(Object o, long offset) {
		return UNSAFE.getChar(o, offset);
	}

	public static void putObject(Object o, long offset, Object x) {
		UNSAFE.putObject(o, offset, x);
	}

	public static void putInt(Object o, long offset, int x) {
		UNSAFE.putInt(o, offset, x);
	}

	public static void putLong(Object o, long offset, long x) {
		UNSAFE.putLong(o, offset, x);
	}

	public static void putFloat(Object o, long offset, float x) {
		UNSAFE.putFloat(o, offset, x);
	}

	public static void putDouble(Object o, long offset, double x) {
		UNSAFE.putDouble(o, offset, x);
	}

	public static void putShort(Object o, long offset, short x) {
		UNSAFE.putShort(o, offset, x);
	}

	public static void putByte(Object o, long offset, byte x) {
		UNSAFE.putByte(o, offset, x);
	}

	public static void putChar(Object o, long offset, char x) {
		UNSAFE.putChar(o, offset, x);
	}

	public static void putBoolean(Object o, long offset, boolean x) {
		UNSAFE.putBoolean(o, offset, x);
	}

	/**
	 * 创建实例
	 *
	 * @param cls {@link Class}
	 * @return 实例对象
	 * @throws InstantiationException 初始化异常
	 */
	public static Object allocateInstance(Class<?> cls) throws InstantiationException {
		return UNSAFE.allocateInstance(cls);
	}

	/**
	 * 获取成员变量的offset，用于getXXX方法重的long参数
	 *
	 * @return offset
	 */
	public static long objectFieldOffset(Field field) {
		return UNSAFE.objectFieldOffset(field);
	}

}

