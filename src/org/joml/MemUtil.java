/*
 * (C) Copyright 2015-2016 Kai Burjack

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.

 */
package org.joml;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Helper class to do efficient memory copies.
 * 
 * @author The LWJGL authors
 * @author Kai Burjack
 */
abstract class MemUtil {
    static final MemUtil INSTANCE = createInstance();

    private static final MemUtil createInstance() {
        MemUtil accessor;
        try {
            accessor = new MemUtilUnsafe();
        } catch (UnsupportedOperationException e) {
            accessor = new MemUtilNIO();
        }
        return accessor;
    }

    abstract void put(Matrix4f m, int offset, FloatBuffer dest);
    abstract void put(Matrix4f m, int offset, ByteBuffer dest);
    abstract void putTransposed(Matrix4f m, int offset, FloatBuffer dest);
    abstract void putTransposed(Matrix4f m, int offset, ByteBuffer dest);
    abstract void put(Matrix3f m, int offset, FloatBuffer dest);
    abstract void put(Matrix3f m, int offset, ByteBuffer dest);
    abstract void get(Matrix4f m, int offset, FloatBuffer src);
    abstract void get(Matrix4f m, int offset, ByteBuffer src);
    abstract void get(Matrix3f m, int offset, FloatBuffer src);
    abstract void get(Matrix3f m, int offset, ByteBuffer src);

    static final class MemUtilNIO extends MemUtil {
        final void put(Matrix4f m, int offset, FloatBuffer dest) {
            dest.put(offset,    m.m00);
            dest.put(offset+1,  m.m01);
            dest.put(offset+2,  m.m02);
            dest.put(offset+3,  m.m03);
            dest.put(offset+4,  m.m10);
            dest.put(offset+5,  m.m11);
            dest.put(offset+6,  m.m12);
            dest.put(offset+7,  m.m13);
            dest.put(offset+8,  m.m20);
            dest.put(offset+9,  m.m21);
            dest.put(offset+10, m.m22);
            dest.put(offset+11, m.m23);
            dest.put(offset+12, m.m30);
            dest.put(offset+13, m.m31);
            dest.put(offset+14, m.m32);
            dest.put(offset+15, m.m33);
        }

        final void put(Matrix4f m, int offset, ByteBuffer dest) {
            dest.putFloat(offset,    m.m00);
            dest.putFloat(offset+4,  m.m01);
            dest.putFloat(offset+8,  m.m02);
            dest.putFloat(offset+12, m.m03);
            dest.putFloat(offset+16, m.m10);
            dest.putFloat(offset+20, m.m11);
            dest.putFloat(offset+24, m.m12);
            dest.putFloat(offset+28, m.m13);
            dest.putFloat(offset+32, m.m20);
            dest.putFloat(offset+36, m.m21);
            dest.putFloat(offset+40, m.m22);
            dest.putFloat(offset+44, m.m23);
            dest.putFloat(offset+48, m.m30);
            dest.putFloat(offset+52, m.m31);
            dest.putFloat(offset+56, m.m32);
            dest.putFloat(offset+60, m.m33);
        }

        final void putTransposed(Matrix4f m, int offset, FloatBuffer dest) {
            dest.put(offset,    m.m00);
            dest.put(offset+1,  m.m10);
            dest.put(offset+2,  m.m20);
            dest.put(offset+3,  m.m30);
            dest.put(offset+4,  m.m01);
            dest.put(offset+5,  m.m11);
            dest.put(offset+6,  m.m21);
            dest.put(offset+7,  m.m31);
            dest.put(offset+8,  m.m02);
            dest.put(offset+9,  m.m12);
            dest.put(offset+10, m.m22);
            dest.put(offset+11, m.m32);
            dest.put(offset+12, m.m03);
            dest.put(offset+13, m.m13);
            dest.put(offset+14, m.m23);
            dest.put(offset+15, m.m33);
        }

        final void putTransposed(Matrix4f m, int offset, ByteBuffer dest) {
            dest.putFloat(offset,    m.m00);
            dest.putFloat(offset+4,  m.m10);
            dest.putFloat(offset+8,  m.m20);
            dest.putFloat(offset+12, m.m30);
            dest.putFloat(offset+16, m.m01);
            dest.putFloat(offset+20, m.m11);
            dest.putFloat(offset+24, m.m21);
            dest.putFloat(offset+28, m.m31);
            dest.putFloat(offset+32, m.m02);
            dest.putFloat(offset+36, m.m12);
            dest.putFloat(offset+40, m.m22);
            dest.putFloat(offset+44, m.m32);
            dest.putFloat(offset+48, m.m03);
            dest.putFloat(offset+52, m.m13);
            dest.putFloat(offset+56, m.m23);
            dest.putFloat(offset+60, m.m33);
        }

        final void put(Matrix3f m, int offset, FloatBuffer dest) {
            dest.put(offset,   m.m00);
            dest.put(offset+1, m.m01);
            dest.put(offset+2, m.m02);
            dest.put(offset+3, m.m10);
            dest.put(offset+4, m.m11);
            dest.put(offset+5, m.m12);
            dest.put(offset+6, m.m20);
            dest.put(offset+7, m.m21);
            dest.put(offset+8, m.m22);
        }

        final void put(Matrix3f m, int offset, ByteBuffer dest) {
            dest.putFloat(offset,    m.m00);
            dest.putFloat(offset+4,  m.m01);
            dest.putFloat(offset+8,  m.m02);
            dest.putFloat(offset+12, m.m10);
            dest.putFloat(offset+16, m.m11);
            dest.putFloat(offset+20, m.m12);
            dest.putFloat(offset+24, m.m20);
            dest.putFloat(offset+28, m.m21);
            dest.putFloat(offset+32, m.m22);
        }

        final void get(Matrix4f m, int offset, FloatBuffer src) {
            m.m00 = src.get(offset);
            m.m01 = src.get(offset+1);
            m.m02 = src.get(offset+2);
            m.m03 = src.get(offset+3);
            m.m10 = src.get(offset+4);
            m.m11 = src.get(offset+5);
            m.m12 = src.get(offset+6);
            m.m13 = src.get(offset+7);
            m.m20 = src.get(offset+8);
            m.m21 = src.get(offset+9);
            m.m22 = src.get(offset+10);
            m.m23 = src.get(offset+11);
            m.m30 = src.get(offset+12);
            m.m31 = src.get(offset+13);
            m.m32 = src.get(offset+14);
            m.m33 = src.get(offset+15);
        }

        final void get(Matrix4f m, int offset, ByteBuffer src) {
            m.m00 = src.getFloat(offset);
            m.m01 = src.getFloat(offset+4);
            m.m02 = src.getFloat(offset+8);
            m.m03 = src.getFloat(offset+12);
            m.m10 = src.getFloat(offset+16);
            m.m11 = src.getFloat(offset+20);
            m.m12 = src.getFloat(offset+24);
            m.m13 = src.getFloat(offset+28);
            m.m20 = src.getFloat(offset+32);
            m.m21 = src.getFloat(offset+36);
            m.m22 = src.getFloat(offset+40);
            m.m23 = src.getFloat(offset+44);
            m.m30 = src.getFloat(offset+48);
            m.m31 = src.getFloat(offset+52);
            m.m32 = src.getFloat(offset+56);
            m.m33 = src.getFloat(offset+60);
        }

        final void get(Matrix3f m, int offset, FloatBuffer src) {
            m.m00 = src.get(offset);
            m.m01 = src.get(offset+1);
            m.m02 = src.get(offset+2);
            m.m10 = src.get(offset+3);
            m.m11 = src.get(offset+4);
            m.m12 = src.get(offset+5);
            m.m20 = src.get(offset+6);
            m.m21 = src.get(offset+7);
            m.m22 = src.get(offset+8);
        }

        final void get(Matrix3f m, int offset, ByteBuffer src) {
            m.m00 = src.getFloat(offset);
            m.m01 = src.getFloat(offset+4);
            m.m02 = src.getFloat(offset+8);
            m.m10 = src.getFloat(offset+12);
            m.m11 = src.getFloat(offset+16);
            m.m12 = src.getFloat(offset+20);
            m.m20 = src.getFloat(offset+24);
            m.m21 = src.getFloat(offset+28);
            m.m22 = src.getFloat(offset+32);
        }
    }

    static final class MemUtilUnsafe extends MemUtil {
        private final sun.misc.Unsafe UNSAFE;
        private final long ADDRESS;

        MemUtilUnsafe() throws UnsupportedOperationException {
            UNSAFE = getUnsafeInstance();
            try {
                ADDRESS = UNSAFE.objectFieldOffset(getDeclaredField(Buffer.class, "address")); //$NON-NLS-1$
            } catch (NoSuchFieldException e) {
                throw new UnsupportedOperationException();
            }
        }

        private static final java.lang.reflect.Field getDeclaredField(Class root, String fieldName) throws NoSuchFieldException {
            Class type = root;
            do {
                try {
                    java.lang.reflect.Field field = type.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    return field;
                } catch (NoSuchFieldException e) {
                    type = type.getSuperclass();
                } catch (SecurityException e) {
                    type = type.getSuperclass();
                }
            } while (type != null);
            throw new NoSuchFieldException(fieldName + " does not exist in " + root.getName() + " or any of its superclasses."); //$NON-NLS-1$ //$NON-NLS-2$
        }

        private static final sun.misc.Unsafe getUnsafeInstance() throws SecurityException {
            java.lang.reflect.Field[] fields = sun.misc.Unsafe.class.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                java.lang.reflect.Field field = fields[i];
                if (!field.getType().equals(sun.misc.Unsafe.class))
                    continue;
                int modifiers = field.getModifiers();
                if (!(java.lang.reflect.Modifier.isStatic(modifiers) && java.lang.reflect.Modifier.isFinal(modifiers)))
                    continue;
                field.setAccessible(true);
                try {
                    return (sun.misc.Unsafe) field.get(null);
                } catch (IllegalAccessException e) {
                    /* Ignore */
                }
                break;
            }
            throw new UnsupportedOperationException();
        }

        private final long addressOf(Buffer buffer) {
            return UNSAFE.getLong(buffer, ADDRESS);
        }

        private final void memPutFloat(long ptr, float value) {
            UNSAFE.putFloat(ptr, value);
        }

        private final float memGetFloat(long ptr) {
            return UNSAFE.getFloat(ptr);
        }

        private final void put(Matrix4f m, long destAddr) {
            memPutFloat(destAddr,      m.m00);
            memPutFloat(destAddr + 4,  m.m01);
            memPutFloat(destAddr + 8,  m.m02);
            memPutFloat(destAddr + 12, m.m03);
            memPutFloat(destAddr + 16, m.m10);
            memPutFloat(destAddr + 20, m.m11);
            memPutFloat(destAddr + 24, m.m12);
            memPutFloat(destAddr + 28, m.m13);
            memPutFloat(destAddr + 32, m.m20);
            memPutFloat(destAddr + 36, m.m21);
            memPutFloat(destAddr + 40, m.m22);
            memPutFloat(destAddr + 44, m.m23);
            memPutFloat(destAddr + 48, m.m30);
            memPutFloat(destAddr + 52, m.m31);
            memPutFloat(destAddr + 56, m.m32);
            memPutFloat(destAddr + 60, m.m33);
        }

        private final void putTransposed(Matrix4f m, long destAddr) {
            memPutFloat(destAddr,      m.m00);
            memPutFloat(destAddr + 4,  m.m10);
            memPutFloat(destAddr + 8,  m.m20);
            memPutFloat(destAddr + 12, m.m30);
            memPutFloat(destAddr + 16, m.m01);
            memPutFloat(destAddr + 20, m.m11);
            memPutFloat(destAddr + 24, m.m21);
            memPutFloat(destAddr + 28, m.m31);
            memPutFloat(destAddr + 32, m.m02);
            memPutFloat(destAddr + 36, m.m12);
            memPutFloat(destAddr + 40, m.m22);
            memPutFloat(destAddr + 44, m.m32);
            memPutFloat(destAddr + 48, m.m03);
            memPutFloat(destAddr + 52, m.m13);
            memPutFloat(destAddr + 56, m.m23);
            memPutFloat(destAddr + 60, m.m33);
        }

        private final void put(Matrix3f m, long destAddr) {
            memPutFloat(destAddr,      m.m00);
            memPutFloat(destAddr + 4,  m.m01);
            memPutFloat(destAddr + 8,  m.m02);
            memPutFloat(destAddr + 12, m.m10);
            memPutFloat(destAddr + 16, m.m11);
            memPutFloat(destAddr + 20, m.m12);
            memPutFloat(destAddr + 24, m.m20);
            memPutFloat(destAddr + 28, m.m21);
            memPutFloat(destAddr + 32, m.m22);
        }

        private final void get(Matrix4f m, long srcAddr) {
            m.m00 = memGetFloat(srcAddr);
            m.m01 = memGetFloat(srcAddr+4);
            m.m02 = memGetFloat(srcAddr+8);
            m.m03 = memGetFloat(srcAddr+12);
            m.m10 = memGetFloat(srcAddr+16);
            m.m11 = memGetFloat(srcAddr+20);
            m.m12 = memGetFloat(srcAddr+24);
            m.m13 = memGetFloat(srcAddr+28);
            m.m20 = memGetFloat(srcAddr+32);
            m.m21 = memGetFloat(srcAddr+36);
            m.m22 = memGetFloat(srcAddr+40);
            m.m23 = memGetFloat(srcAddr+44);
            m.m30 = memGetFloat(srcAddr+48);
            m.m31 = memGetFloat(srcAddr+52);
            m.m32 = memGetFloat(srcAddr+56);
            m.m33 = memGetFloat(srcAddr+60);
        }

        private final void get(Matrix3f m, long srcAddr) {
            m.m00 = memGetFloat(srcAddr);
            m.m01 = memGetFloat(srcAddr+4);
            m.m02 = memGetFloat(srcAddr+8);
            m.m10 = memGetFloat(srcAddr+12);
            m.m11 = memGetFloat(srcAddr+16);
            m.m12 = memGetFloat(srcAddr+20);
            m.m20 = memGetFloat(srcAddr+24);
            m.m21 = memGetFloat(srcAddr+28);
            m.m22 = memGetFloat(srcAddr+32);
        }

        final void put(Matrix4f m, int offset, FloatBuffer dest) {
            put(m, addressOf(dest) + offset * 4);
        }

        final void put(Matrix4f m, int offset, ByteBuffer dest) {
            put(m, addressOf(dest) + offset);
        }

        final void putTransposed(Matrix4f m, int offset, FloatBuffer dest) {
            putTransposed(m, addressOf(dest) + offset * 4);
        }

        final void putTransposed(Matrix4f m, int offset, ByteBuffer dest) {
            putTransposed(m, addressOf(dest) + offset);
        }

        final void put(Matrix3f m, int offset, FloatBuffer dest) {
            put(m, addressOf(dest) + offset * 4);
        }

        final void put(Matrix3f m, int offset, ByteBuffer dest) {
            put(m, addressOf(dest) + offset);
        }

        final void get(Matrix4f m, int offset, FloatBuffer src) {
            get(m, addressOf(src) + offset * 4);
        }

        final void get(Matrix4f m, int offset, ByteBuffer src) {
            get(m, addressOf(src) + offset);
        }

        final void get(Matrix3f m, int offset, FloatBuffer src) {
            get(m, addressOf(src) + offset * 4);
        }

        final void get(Matrix3f m, int offset, ByteBuffer src) {
            get(m, addressOf(src) + offset);
        }
    }
}
