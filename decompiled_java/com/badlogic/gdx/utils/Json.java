// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map$Entry;

public class Json {
    class FieldMetadata {
        Class elementType;
        Field field;

        public FieldMetadata(Field field) {
            int v0;
            super();
            this.field = field;
            if((ClassReflection.isAssignableFrom(ObjectMap.class, field.getType())) || (ClassReflection.isAssignableFrom(Map.class, field.getType()))) {
                v0 = 1;
            }
            else {
                v0 = 0;
            }

            this.elementType = field.getElementType(v0);
        }
    }

    public abstract class ReadOnlySerializer implements Serializer {
        public ReadOnlySerializer() {
            super();
        }

        public abstract Object read(Json arg0, JsonValue arg1, Class arg2);

        public void write(Json json, Object arg2, Class knownType) {
        }
    }

    public abstract interface Serializable {
        public abstract void read(Json arg0, JsonValue arg1);

        public abstract void write(Json arg0);
    }

    public abstract interface Serializer {
        public abstract Object read(Json arg0, JsonValue arg1, Class arg2);

        public abstract void write(Json arg0, Object arg1, Class arg2);
    }

    private final ObjectMap classToDefaultValues;
    private final ObjectMap classToSerializer;
    private final ObjectMap classToTag;
    private static final boolean debug;
    private Serializer defaultSerializer;
    private boolean ignoreUnknownFields;
    private OutputType outputType;
    private final ObjectMap tagToClass;
    private String typeName;
    private final ObjectMap typeToFields;
    private boolean usePrototypes;
    private JsonWriter writer;

    public Json() {
        super();
        this.typeName = "class";
        this.usePrototypes = true;
        this.typeToFields = new ObjectMap();
        this.tagToClass = new ObjectMap();
        this.classToTag = new ObjectMap();
        this.classToSerializer = new ObjectMap();
        this.classToDefaultValues = new ObjectMap();
        this.outputType = OutputType.minimal;
    }

    public Json(OutputType outputType) {
        super();
        this.typeName = "class";
        this.usePrototypes = true;
        this.typeToFields = new ObjectMap();
        this.tagToClass = new ObjectMap();
        this.classToTag = new ObjectMap();
        this.classToSerializer = new ObjectMap();
        this.classToDefaultValues = new ObjectMap();
        this.outputType = outputType;
    }

    public void addClassTag(String tag, Class type) {
        this.tagToClass.put(tag, type);
        this.classToTag.put(type, tag);
    }

    private ObjectMap cacheFields(Class type) {  // has try-catch handlers
        ArrayList v0 = new ArrayList();
        Class v6;
        for(v6 = type; v6 != Object.class; v6 = v6.getSuperclass()) {
            Collections.addAll(((Collection)v0), ClassReflection.getDeclaredFields(v6));
        }

        ObjectMap v5 = new ObjectMap();
        int v3 = 0;
        int v4 = v0.size();
        while(v3 < v4) {
            Object v2 = v0.get(v3);
            if(!((Field)v2).isTransient()) {
                goto label_17;
            }

            goto label_15;
        label_17:
            if(((Field)v2).isStatic()) {
                goto label_15;
            }

            if(((Field)v2).isSynthetic()) {
                goto label_15;
            }

            if(!((Field)v2).isAccessible()) {
                try {
                    ((Field)v2).setAccessible(true);
                }
                catch(AccessControlException v1) {
                    goto label_15;
                }
            }

            v5.put(((Field)v2).getName(), new FieldMetadata(((Field)v2)));
        label_15:
            ++v3;
        }

        this.typeToFields.put(type, v5);
        return v5;
    }

    private String convertToString(Object object) {
        String v0;
        if((object instanceof Class)) {
            v0 = ((Class)object).getName();
        }
        else {
            v0 = String.valueOf(object);
        }

        return v0;
    }

    public Object fromJson(Class arg5, FileHandle file) {  // has try-catch handlers
        Class v1 = null;
        try {
            return this.readValue(arg5, v1, new JsonReader().parse(file));
        }
        catch(Exception v0) {
            throw new SerializationException("Error reading file: " + file, ((Throwable)v0));
        }
    }

    public Object fromJson(Class arg3, InputStream input) {
        return this.readValue(arg3, null, new JsonReader().parse(input));
    }

    public Object fromJson(Class arg3, Reader reader) {
        return this.readValue(arg3, null, new JsonReader().parse(reader));
    }

    public Object fromJson(Class arg5, Class elementType, FileHandle file) {  // has try-catch handlers
        try {
            return this.readValue(arg5, elementType, new JsonReader().parse(file));
        }
        catch(Exception v0) {
            throw new SerializationException("Error reading file: " + file, ((Throwable)v0));
        }
    }

    public Object fromJson(Class arg2, Class elementType, InputStream input) {
        return this.readValue(arg2, elementType, new JsonReader().parse(input));
    }

    public Object fromJson(Class arg2, Class elementType, Reader reader) {
        return this.readValue(arg2, elementType, new JsonReader().parse(reader));
    }

    public Object fromJson(Class arg2, Class elementType, String json) {
        return this.readValue(arg2, elementType, new JsonReader().parse(json));
    }

    public Object fromJson(Class arg2, Class elementType, char[] data, int offset, int length) {
        return this.readValue(arg2, elementType, new JsonReader().parse(data, offset, length));
    }

    public Object fromJson(Class arg3, String json) {
        return this.readValue(arg3, null, new JsonReader().parse(json));
    }

    public Object fromJson(Class arg3, char[] data, int offset, int length) {
        return this.readValue(arg3, null, new JsonReader().parse(data, offset, length));
    }

    public Class getClass(String tag) {  // has try-catch handlers
        Class v1_1;
        Object v1 = this.tagToClass.get(tag);
        if(v1 == null) {
            try {
                v1_1 = ClassReflection.forName(tag);
            }
            catch(ReflectionException v0) {
                throw new SerializationException(((Throwable)v0));
            }
        }

        return v1_1;
    }

    private Object[] getDefaultValues(Class type) {  // has try-catch handlers
        ObjectMap v2_1;
        Object v7;
        Object[] v10 = null;
        if(this.usePrototypes) {
            goto label_4;
        }

        goto label_3;
    label_4:
        if(!this.classToDefaultValues.containsKey(type)) {
            goto label_10;
        }

        Object v10_1 = this.classToDefaultValues.get(type);
        goto label_3;
        try {
        label_10:
            v7 = this.newInstance(type);
        }
        catch(Exception v0) {
            this.classToDefaultValues.put(type, v10);
            goto label_3;
        }

        Object v2 = this.typeToFields.get(type);
        if(v2 == null) {
            v2_1 = this.cacheFields(type);
        }

        Object[] v9 = new Object[v2_1.size];
        this.classToDefaultValues.put(type, v9);
        int v3 = 0;
        Iterator v5 = v2_1.values().iterator();
        while(true) {
            if(!v5.hasNext()) {
                break;
            }

            Field v1 = v5.next().field;
            int v4 = v3 + 1;
            try {
                v9[v3] = v1.get(v7);
                v3 = v4;
                continue;
            }
            catch(RuntimeException v8) {
                v0_1 = new SerializationException(((Throwable)v8));
                v0_1.addTrace(v1 + " (" + type.getName() + ")");
                throw v0_1;
            }
            catch(SerializationException v0_1) {
                v0_1.addTrace(v1 + " (" + type.getName() + ")");
                throw v0_1;
            }
            catch(ReflectionException v0_2) {
                throw new SerializationException("Error accessing field: " + v1.getName() + " (" + type.getName() + ")", ((Throwable)v0_2));
            }
        }

        v10 = v9;
    label_3:
        return ((Object[])v10_1);
    }

    public Serializer getSerializer(Class arg2) {
        return this.classToSerializer.get(arg2);
    }

    public String getTag(Class type) {
        Object v0 = this.classToTag.get(type);
        if(v0 == null) {
            String v0_1 = type.getName();
        }

        return ((String)v0);
    }

    public JsonWriter getWriter() {
        return this.writer;
    }

    protected Object newInstance(Class type) {  // has try-catch handlers
        Object v4;
        try {
            v4 = ClassReflection.newInstance(type);
        }
        catch(Exception v1) {
            try {
                Constructor v0 = ClassReflection.getDeclaredConstructor(type, new Class[0]);
                v0.setAccessible(true);
                v4 = v0.newInstance(new Object[0]);
                goto label_2;
            }
            catch(Exception v3) {
                v1 = v3;
            }
            catch(ReflectionException v2) {
                if(!type.isEnum()) {
                    goto label_19;
                }

                v4 = type.getEnumConstants()[0];
                goto label_2;
            label_19:
                if(type.isArray()) {
                    throw new SerializationException("Encountered JSON object when expected array of type: " + type.getName(), ((Throwable)v1));
                }

                if((ClassReflection.isMemberClass(type)) && !ClassReflection.isStaticClass(type)) {
                    throw new SerializationException("Class cannot be created (non-static member class): " + type.getName(), ((Throwable)v1));
                }

                throw new SerializationException("Class cannot be created (missing no-arg constructor): " + type.getName(), ((Throwable)v1));
            }
            catch(SecurityException v4_1) {
            }

            throw new SerializationException("Error constructing instance of class: " + type.getName(), ((Throwable)v1));
        }

    label_2:
        return v4;
    }

    public String prettyPrint(Object object) {
        return this.prettyPrint(object, 0);
    }

    public String prettyPrint(Object object, int singleLineColumns) {
        return this.prettyPrint(this.toJson(object), singleLineColumns);
    }

    public String prettyPrint(String json, int singleLineColumns) {
        return new JsonReader().parse(json).prettyPrint(this.outputType, singleLineColumns);
    }

    public String prettyPrint(Object object, PrettyPrintSettings settings) {
        return this.prettyPrint(this.toJson(object), settings);
    }

    public String prettyPrint(String json, PrettyPrintSettings settings) {
        return new JsonReader().parse(json).prettyPrint(settings);
    }

    public String prettyPrint(String json) {
        return this.prettyPrint(json, 0);
    }

    public void readField(Object object, String name, JsonValue jsonData) {
        this.readField(object, name, name, null, jsonData);
    }

    public void readField(Object object, String fieldName, String jsonName, Class elementType, JsonValue jsonMap) {  // has try-catch handlers
        Class v6 = object.getClass();
        Object v2 = this.typeToFields.get(v6);
        if(v2 == null) {
            ObjectMap v2_1 = this.cacheFields(v6);
        }

        Object v4 = ((ObjectMap)v2).get(fieldName);
        if(v4 == null) {
            throw new SerializationException("Field not found: " + fieldName + " (" + v6.getName() + ")");
        }

        Field v1 = ((FieldMetadata)v4).field;
        JsonValue v3 = jsonMap.get(jsonName);
        if(v3 != null) {
            if(elementType == null) {
                elementType = ((FieldMetadata)v4).elementType;
            }

            try {
                v1.set(object, this.readValue(v1.getType(), elementType, v3));
            }
            catch(RuntimeException v5) {
                v0 = new SerializationException(((Throwable)v5));
                v0.addTrace(v1.getName() + " (" + v6.getName() + ")");
                throw v0;
            }
            catch(SerializationException v0) {
                v0.addTrace(v1.getName() + " (" + v6.getName() + ")");
                throw v0;
            }
            catch(ReflectionException v0_1) {
                throw new SerializationException("Error accessing field: " + v1.getName() + " (" + v6.getName() + ")", ((Throwable)v0_1));
            }
        }
    }

    public void readField(Object object, String name, Class elementType, JsonValue jsonData) {
        this.readField(object, name, name, elementType, jsonData);
    }

    public void readField(Object object, String fieldName, String jsonName, JsonValue jsonData) {
        this.readField(object, fieldName, jsonName, null, jsonData);
    }

    public void readFields(Object object, JsonValue jsonMap) {  // has try-catch handlers
        Class v6 = object.getClass();
        Object v3 = this.typeToFields.get(v6);
        if(v3 == null) {
            ObjectMap v3_1 = this.cacheFields(v6);
        }

        JsonValue v0;
        for(v0 = jsonMap.child; v0 != null; v0 = v0.next) {
            Object v4 = ((ObjectMap)v3).get(v0.name());
            if(v4 != null) {
                Field v2 = ((FieldMetadata)v4).field;
                try {
                    v2.set(object, this.readValue(v2.getType(), ((FieldMetadata)v4).elementType, v0));
                }
                catch(RuntimeException v5) {
                    v1 = new SerializationException(((Throwable)v5));
                    v1.addTrace(v2.getName() + " (" + v6.getName() + ")");
                    throw v1;
                }
                catch(SerializationException v1) {
                    v1.addTrace(v2.getName() + " (" + v6.getName() + ")");
                    throw v1;
                }
                catch(ReflectionException v1_1) {
                    throw new SerializationException("Error accessing field: " + v2.getName() + " (" + v6.getName() + ")", ((Throwable)v1_1));
                }
            }
            else if(!this.ignoreUnknownFields) {
                throw new SerializationException("Field not found: " + v0.name() + " (" + v6.getName() + ")");
            }
        }
    }

    public Object readValue(Class arg22, Class elementType, JsonValue jsonData) {  // has try-catch handlers
        Boolean v13_11;
        Float v13_10;
        Integer v13_9;
        Long v13_8;
        Double v13_7;
        Short v13_6;
        Byte v13_5;
        String v13_4;
        Object v15;
        Object v16;
        String v5;
        if(jsonData != null) {
            goto label_3;
        }

        Object v13 = null;
        goto label_2;
    label_3:
        if(!jsonData.isObject()) {
            goto label_168;
        }

        if(this.typeName == null) {
            v5 = null;
        }
        else {
            v5 = jsonData.getString(this.typeName, null);
        }

        if(v5 == null) {
            goto label_18;
        }

        jsonData.remove(this.typeName);
        try {
            arg22 = ClassReflection.forName(v5);
        }
        catch(ReflectionException v8) {
            ObjectMap v18 = this.tagToClass;
            Object v22_1 = v18.get(v5);
            if(v22_1 != null) {
                goto label_18;
            }

            throw new SerializationException(((Throwable)v8));
        }

    label_18:
        if(arg22 == null) {
            goto label_128;
        }

        if(arg22 != String.class && arg22 != Integer.class && arg22 != Boolean.class && arg22 != Float.class && arg22 != Long.class && arg22 != Double.class && arg22 != Short.class && arg22 != Byte.class && arg22 != Character.class && !arg22.isEnum()) {
            v16 = this.classToSerializer.get(arg22);
            if(v16 != null) {
                v13 = v16.read(this, jsonData, arg22);
                goto label_2;
            }
            else {
                Object v14 = this.newInstance(arg22);
                if((v14 instanceof Serializable)) {
                    v14.read(this, jsonData);
                    v13 = v14;
                    goto label_2;
                }
                else if((v14 instanceof HashMap)) {
                    v15 = v14;
                    JsonValue v4;
                    for(v4 = jsonData.child; v4 != null; v4 = v4.next) {
                        ((HashMap)v15).put(v4.name(), this.readValue(elementType, null, v4));
                    }

                    v13 = v15;
                    goto label_2;
                }
                else if((v14 instanceof ObjectMap)) {
                    v15 = v14;
                    for(v4 = jsonData.child; v4 != null; v4 = v4.next) {
                        ((ObjectMap)v15).put(v4.name(), this.readValue(elementType, null, v4));
                    }

                    v13 = v15;
                    goto label_2;
                }
                else {
                    this.readFields(v14, jsonData);
                    v13 = v14;
                    goto label_2;
                }
            }
        }

        v13 = this.readValue("value", arg22, jsonData);
        goto label_2;
    label_128:
        if(this.defaultSerializer == null) {
            goto label_141;
        }

        v13 = this.defaultSerializer.read(this, jsonData, arg22);
        goto label_2;
    label_141:
        JsonValue v13_1 = jsonData;
        goto label_2;
    label_168:
        if(arg22 != null) {
            v16 = this.classToSerializer.get(arg22);
            if(v16 != null) {
                v13 = v16.read(this, jsonData, arg22);
                goto label_2;
            }
        }

        if(!jsonData.isArray()) {
            goto label_280;
        }

        if(arg22 != null && arg22 != Object.class && !ClassReflection.isAssignableFrom(Array.class, arg22)) {
            if(ClassReflection.isAssignableFrom(List.class, arg22)) {
                if(arg22 == null || (arg22.isInterface())) {
                    ArrayList v13_2 = new ArrayList();
                }
                else {
                    v13 = this.newInstance(arg22);
                }

                for(v4 = jsonData.child; true; v4 = v4.next) {
                    if(v4 == null) {
                        goto label_2;
                    }

                    ((List)v13).add(this.readValue(elementType, null, v4));
                }
            }
            else {
                if(!arg22.isArray()) {
                    goto label_265;
                }

                Class v6 = arg22.getComponentType();
                if(elementType == null) {
                    elementType = v6;
                }

                v13 = ArrayReflection.newInstance(v6, jsonData.size);
                v4 = jsonData.child;
                int v10;
                for(v10 = 0; true; ++v10) {
                    if(v4 == null) {
                        goto label_2;
                    }

                    ArrayReflection.set(v13, v10, this.readValue(elementType, null, v4));
                    v4 = v4.next;
                }

            label_265:
                throw new SerializationException("Unable to convert value to required type: " + jsonData + " (" + arg22.getName() + ")");
            }
        }

        if(arg22 == null || arg22 == Object.class) {
            Array v13_3 = new Array();
        }
        else {
            v13 = this.newInstance(arg22);
        }

        for(v4 = jsonData.child; true; v4 = v4.next) {
            if(v4 == null) {
                goto label_2;
            }

            ((Array)v13).add(this.readValue(elementType, null, v4));
        }

    label_280:
        if(!jsonData.isNumber()) {
            goto label_361;
        }

        if(arg22 != null) {
            try {
                if(arg22 != Float.TYPE && arg22 != Float.class) {
                    if(arg22 != Integer.TYPE && arg22 != Integer.class) {
                        if(arg22 != Long.TYPE && arg22 != Long.class) {
                            if(arg22 != Double.TYPE && arg22 != Double.class) {
                                if(arg22 == String.class) {
                                    v13_4 = Float.toString(jsonData.asFloat());
                                    goto label_2;
                                }
                                else {
                                    if(arg22 != Short.TYPE && arg22 != Short.class) {
                                        if(arg22 != Byte.TYPE && arg22 != Byte.class) {
                                            goto label_357;
                                        }

                                        v13_5 = Byte.valueOf(jsonData.asByte());
                                        goto label_2;
                                    }

                                    v13_6 = Short.valueOf(jsonData.asShort());
                                    goto label_2;
                                }
                            }

                            v13_7 = Double.valueOf(jsonData.asDouble());
                            goto label_2;
                        }

                        v13_8 = Long.valueOf(jsonData.asLong());
                        goto label_2;
                    }

                    v13_9 = Integer.valueOf(jsonData.asInt());
                    goto label_2;
                }

            label_291:
                v13_10 = Float.valueOf(jsonData.asFloat());
                goto label_2;
            }
            catch(NumberFormatException v18_1) {
                goto label_357;
            }
        }

        goto label_291;
    label_357:
        jsonData = new JsonValue(jsonData.asString());
    label_361:
        if(!jsonData.isBoolean()) {
            goto label_380;
        }

        if(arg22 != null) {
            try {
                if(arg22 != Boolean.TYPE && arg22 != Boolean.class) {
                    goto label_376;
                }

            label_372:
                v13_11 = Boolean.valueOf(jsonData.asBoolean());
                goto label_2;
            }
            catch(NumberFormatException v18_1) {
                goto label_376;
            }
        }

        goto label_372;
    label_376:
        jsonData = new JsonValue(jsonData.asString());
    label_380:
        if(!jsonData.isString()) {
            goto label_511;
        }

        String v17 = jsonData.asString();
        if(arg22 != null && arg22 != String.class) {
            try {
                if(arg22 != Integer.TYPE && arg22 != Integer.class) {
                    if(arg22 != Float.TYPE && arg22 != Float.class) {
                        if(arg22 != Long.TYPE && arg22 != Long.class) {
                            if(arg22 != Double.TYPE && arg22 != Double.class) {
                                if(arg22 != Short.TYPE && arg22 != Short.class) {
                                    if(arg22 != Byte.TYPE && arg22 != Byte.class) {
                                        goto label_451;
                                    }

                                    v13_5 = Byte.valueOf(v17);
                                    goto label_2;
                                }

                                v13_6 = Short.valueOf(v17);
                                goto label_2;
                            }

                            v13_7 = Double.valueOf(v17);
                            goto label_2;
                        }

                        v13_8 = Long.valueOf(v17);
                        goto label_2;
                    }

                    v13_10 = Float.valueOf(v17);
                    goto label_2;
                }

                v13_9 = Integer.valueOf(v17);
                goto label_2;
            }
            catch(NumberFormatException v18_1) {
            }

        label_451:
            if(arg22 != Boolean.TYPE && arg22 != Boolean.class) {
                if(arg22 != Character.TYPE && arg22 != Character.class) {
                    if(ClassReflection.isAssignableFrom(Enum.class, arg22)) {
                        Object[] v7 = arg22.getEnumConstants();
                        int v9 = 0;
                        int v12 = v7.length;
                        while(v9 < v12) {
                            if(v17.equals(v7[v9].toString())) {
                                v13 = v7[v9];
                                goto label_2;
                            }
                            else {
                                ++v9;
                                continue;
                            }
                        }
                    }

                    if(arg22 != CharSequence.class) {
                        goto label_496;
                    }

                    v13_4 = v17;
                    goto label_2;
                label_496:
                    throw new SerializationException("Unable to convert value to required type: " + jsonData + " (" + arg22.getName() + ")");
                }

                Character v13_12 = Character.valueOf(v17.charAt(0));
                goto label_2;
            }

            v13_11 = Boolean.valueOf(v17);
            goto label_2;
        }

        v13_4 = v17;
        goto label_2;
    label_511:
        v13 = null;
    label_2:
        return v13_8;
    }

    public Object readValue(Class arg2, JsonValue jsonData) {
        return this.readValue(arg2, null, jsonData);
    }

    public Object readValue(String name, Class arg4, JsonValue jsonMap) {
        return this.readValue(arg4, null, jsonMap.get(name));
    }

    public Object readValue(String name, Class arg4, Object arg5, JsonValue jsonMap) {
        JsonValue v0 = jsonMap.get(name);
        if(v0 != null) {
            arg5 = this.readValue(arg4, null, v0);
        }

        return arg5;
    }

    public Object readValue(Class arg2, Class elementType, Object arg4, JsonValue jsonData) {
        return this.readValue(arg2, elementType, jsonData);
    }

    public Object readValue(String name, Class arg3, Class elementType, JsonValue jsonMap) {
        return this.readValue(arg3, elementType, jsonMap.get(name));
    }

    public Object readValue(String name, Class arg3, Class elementType, Object arg5, JsonValue jsonMap) {
        JsonValue v0 = jsonMap.get(name);
        if(v0 != null) {
            arg5 = this.readValue(arg3, elementType, v0);
        }

        return arg5;
    }

    public void setDefaultSerializer(Serializer defaultSerializer) {
        this.defaultSerializer = defaultSerializer;
    }

    public void setElementType(Class type, String fieldName, Class elementType) {
        Object v0 = this.typeToFields.get(type);
        if(v0 == null) {
            ObjectMap v0_1 = this.cacheFields(type);
        }

        Object v1 = ((ObjectMap)v0).get(fieldName);
        if(v1 == null) {
            throw new SerializationException("Field not found: " + fieldName + " (" + type.getName() + ")");
        }

        ((FieldMetadata)v1).elementType = elementType;
    }

    public void setIgnoreUnknownFields(boolean ignoreUnknownFields) {
        this.ignoreUnknownFields = ignoreUnknownFields;
    }

    public void setOutputType(OutputType outputType) {
        this.outputType = outputType;
    }

    public void setSerializer(Class arg2, Serializer arg3) {
        this.classToSerializer.put(arg2, arg3);
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setUsePrototypes(boolean usePrototypes) {
        this.usePrototypes = usePrototypes;
    }

    public void setWriter(Writer writer) {
        JsonWriter v4_1;
        if(!(writer instanceof JsonWriter)) {
            v4_1 = new JsonWriter(writer);
        }

        this.writer = v4_1;
        this.writer.setOutputType(this.outputType);
    }

    public String toJson(Object object) {
        Class v1;
        Class v0 = null;
        if(object == null) {
            v1 = v0;
        }
        else {
            v1 = object.getClass();
        }

        return this.toJson(object, v1, v0);
    }

    public String toJson(Object object, Class knownType, Class elementType) {
        StringWriter v0 = new StringWriter();
        this.toJson(object, knownType, elementType, ((Writer)v0));
        return v0.toString();
    }

    public String toJson(Object object, Class knownType) {
        return this.toJson(object, knownType, null);
    }

    public void toJson(Object object, Class knownType, Class elementType, Writer writer) {  // has try-catch handlers
        JsonWriter v2 = null;
        this.setWriter(writer);
        try {
            this.writeValue(object, knownType, elementType);
        }
        catch(Throwable v0) {
            StreamUtils.closeQuietly(this.writer);
            this.writer = v2;
            throw v0;
        }

        StreamUtils.closeQuietly(this.writer);
        this.writer = v2;
    }

    public void toJson(Object object, FileHandle file) {
        Class v0;
        Class v1 = null;
        if(object == null) {
            v0 = v1;
        }
        else {
            v0 = object.getClass();
        }

        this.toJson(object, v0, v1, file);
    }

    public void toJson(Object object, Class knownType, Class elementType, FileHandle file) {  // has try-catch handlers
        Writer v1;
        try {
            v1 = file.writer(false, "UTF-8");
            this.toJson(object, knownType, elementType, v1);
        }
        catch(Throwable v2) {
        }
        catch(Exception v0) {
            try {
                throw new SerializationException("Error writing file: " + file, ((Throwable)v0));
            }
            catch(Throwable v2) {
                StreamUtils.closeQuietly(((Closeable)v1));
                throw v2;
            }
        }

        StreamUtils.closeQuietly(((Closeable)v1));
    }

    public void toJson(Object object, Writer writer) {
        Class v0;
        Class v1 = null;
        if(object == null) {
            v0 = v1;
        }
        else {
            v0 = object.getClass();
        }

        this.toJson(object, v0, v1, writer);
    }

    public void toJson(Object object, Class knownType, FileHandle file) {
        this.toJson(object, knownType, null, file);
    }

    public void toJson(Object object, Class knownType, Writer writer) {
        this.toJson(object, knownType, null, writer);
    }

    public void writeArrayEnd() {  // has try-catch handlers
        try {
            this.writer.pop();
            return;
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }
    }

    public void writeArrayStart() {  // has try-catch handlers
        try {
            this.writer.array();
            return;
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }
    }

    public void writeArrayStart(String name) {  // has try-catch handlers
        try {
            this.writer.name(name);
            this.writer.array();
            return;
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }
    }

    public void writeField(Object object, String name) {
        this.writeField(object, name, name, null);
    }

    public void writeField(Object object, String fieldName, String jsonName, Class elementType) {  // has try-catch handlers
        ObjectMap v2_1;
        Class v5 = object.getClass();
        Object v2 = this.typeToFields.get(v5);
        if(v2 == null) {
            v2_1 = this.cacheFields(v5);
        }

        Object v3 = v2_1.get(fieldName);
        if(v3 == null) {
            throw new SerializationException("Field not found: " + fieldName + " (" + v5.getName() + ")");
        }

        Field v1 = ((FieldMetadata)v3).field;
        if(elementType == null) {
            elementType = ((FieldMetadata)v3).elementType;
        }

        try {
            this.writer.name(jsonName);
            this.writeValue(v1.get(object), v1.getType(), elementType);
            return;
        }
        catch(Exception v4) {
            v0 = new SerializationException(((Throwable)v4));
            v0.addTrace(v1 + " (" + v5.getName() + ")");
            throw v0;
        }
        catch(SerializationException v0) {
            v0.addTrace(v1 + " (" + v5.getName() + ")");
            throw v0;
        }
        catch(ReflectionException v0_1) {
            throw new SerializationException("Error accessing field: " + v1.getName() + " (" + v5.getName() + ")", ((Throwable)v0_1));
        }
    }

    public void writeField(Object object, String name, Class elementType) {
        this.writeField(object, name, name, elementType);
    }

    public void writeField(Object object, String fieldName, String jsonName) {
        this.writeField(object, fieldName, jsonName, null);
    }

    public void writeFields(Object object) {  // has try-catch handlers
        Object v12;
        Field v4;
        Class v11 = object.getClass();
        Object[] v2 = this.getDefaultValues(v11);
        Object v5 = this.typeToFields.get(v11);
        if(v5 == null) {
            ObjectMap v5_1 = this.cacheFields(v11);
        }

        int v6 = 0;
        Iterator v8 = new Values(((ObjectMap)v5)).iterator();
        while(true) {
            if(!v8.hasNext()) {
                return;
            }

            Object v9 = v8.next();
            v4 = ((FieldMetadata)v9).field;
            try {
                v12 = v4.get(object);
                if(v2 != null) {
                    goto label_19;
                }

                goto label_32;
            }
            catch(Exception v10) {
                break;
            }
            catch(SerializationException v3) {
                goto label_57;
            }
            catch(ReflectionException v3_1) {
                goto label_42;
            }

        label_19:
            int v7 = v6 + 1;
            try {
                Object v1 = v2[v6];
                if(v12 == null && v1 == null) {
                    v6 = v7;
                    continue;
                }

                if(v12 != null && v1 != null) {
                    if(!v12.equals(v1)) {
                        goto label_31;
                    }

                    goto label_29;
                }

                goto label_31;
            }
            catch(Exception v10) {
                break;
            }
            catch(SerializationException v3) {
                goto label_57;
            }
            catch(ReflectionException v3_1) {
                goto label_42;
            }

        label_29:
            v6 = v7;
            continue;
        label_31:
            v6 = v7;
            try {
            label_32:
                this.writer.name(v4.getName());
                this.writeValue(v12, v4.getType(), ((FieldMetadata)v9).elementType);
                continue;
            }
            catch(Exception v10) {
                break;
            }
            catch(SerializationException v3) {
            label_57:
                v3.addTrace(v4 + " (" + v11.getName() + ")");
                throw v3;
            }
            catch(ReflectionException v3_1) {
            label_42:
                throw new SerializationException("Error accessing field: " + v4.getName() + " (" + v11.getName() + ")", ((Throwable)v3_1));
            }
        }

        v3 = new SerializationException(((Throwable)v10));
        v3.addTrace(v4 + " (" + v11.getName() + ")");
        throw v3;
    }

    public void writeObjectEnd() {  // has try-catch handlers
        try {
            this.writer.pop();
            return;
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }
    }

    public void writeObjectStart() {  // has try-catch handlers
        try {
            this.writer.object();
            return;
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }
    }

    public void writeObjectStart(Class actualType, Class knownType) {  // has try-catch handlers
        try {
            this.writer.object();
            if(knownType == null) {
                goto label_4;
            }
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }

        if(knownType != actualType) {
        label_4:
            this.writeType(actualType);
        }
    }

    public void writeObjectStart(String name) {  // has try-catch handlers
        try {
            this.writer.name(name);
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }

        this.writeObjectStart();
    }

    public void writeObjectStart(String name, Class actualType, Class knownType) {  // has try-catch handlers
        try {
            this.writer.name(name);
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }

        this.writeObjectStart(actualType, knownType);
    }

    public void writeType(Class type) {  // has try-catch handlers
        String v0_1;
        if(this.typeName != null) {
            Object v0 = this.classToTag.get(type);
            if(v0 == null) {
                v0_1 = type.getName();
            }

            try {
                this.writer.set(this.typeName, v0_1);
            }
            catch(IOException v1) {
                throw new SerializationException(((Throwable)v1));
            }
        }
    }

    public void writeValue(Object value, Class knownType, Class elementType) {  // has try-catch handlers
        Object v5;
        int v13;
        int v7;
        if(value == null) {
            try {
                this.writer.value(null);
                return;
            label_6:
                if((knownType == null || !knownType.isPrimitive()) && (knownType != String.class && knownType != Integer.class && knownType != Boolean.class && knownType != Float.class && knownType != Long.class && knownType != Double.class && knownType != Short.class && knownType != Byte.class && knownType != Character.class)) {
                    Class v3 = value.getClass();
                    if(!v3.isPrimitive() && v3 != String.class && v3 != Integer.class && v3 != Boolean.class && v3 != Float.class && v3 != Long.class && v3 != Double.class && v3 != Short.class && v3 != Byte.class && v3 != Character.class) {
                        if((value instanceof Serializable)) {
                            this.writeObjectStart(v3, knownType);
                            value.write(this);
                            this.writeObjectEnd();
                            return;
                        }
                        else {
                            Object v14 = this.classToSerializer.get(v3);
                            if(v14 != null) {
                                ((Serializer)v14).write(this, value, knownType);
                                return;
                            }
                            else if((value instanceof Array)) {
                                if(knownType != null && v3 != knownType && v3 != Array.class) {
                                    throw new SerializationException("Serialization of an Array other than the known type is not supported.\nKnown type: " + knownType + "\nActual type: " + v3);
                                }

                                this.writeArrayStart();
                                Object v4 = value;
                                v7 = 0;
                                v13 = ((Array)v4).size;
                                while(v7 < v13) {
                                    this.writeValue(((Array)v4).get(v7), elementType, null);
                                    ++v7;
                                }

                                this.writeArrayEnd();
                                return;
                            }
                            else {
                                if(!(value instanceof Collection)) {
                                    goto label_164;
                                }

                                if(knownType != null && v3 != knownType && v3 != ArrayList.class) {
                                    throw new SerializationException("Serialization of a Collection other than the known type is not supported.\nKnown type: " + knownType + "\nActual type: " + v3);
                                }

                                this.writeArrayStart();
                                Iterator v8 = ((Collection)value).iterator();
                                while(v8.hasNext()) {
                                    this.writeValue(v8.next(), elementType, null);
                                }

                                this.writeArrayEnd();
                                return;
                            label_164:
                                if(!v3.isArray()) {
                                    goto label_183;
                                }

                                if(elementType == null) {
                                    elementType = v3.getComponentType();
                                }

                                int v11 = ArrayReflection.getLength(value);
                                this.writeArrayStart();
                                for(v7 = 0; v7 < v11; ++v7) {
                                    this.writeValue(ArrayReflection.get(value, v7), elementType, null);
                                }

                                this.writeArrayEnd();
                                return;
                            label_183:
                                if(!(value instanceof OrderedMap)) {
                                    goto label_212;
                                }

                                if(knownType == null) {
                                    knownType = OrderedMap.class;
                                }

                                this.writeObjectStart(v3, knownType);
                                Object v12 = value;
                                v8 = ((OrderedMap)v12).orderedKeys().iterator();
                                while(v8.hasNext()) {
                                    Object v10 = v8.next();
                                    this.writer.name(this.convertToString(v10));
                                    this.writeValue(((OrderedMap)v12).get(v10), elementType, null);
                                }

                                this.writeObjectEnd();
                                return;
                            label_212:
                                if(!(value instanceof ArrayMap)) {
                                    goto label_245;
                                }

                                if(knownType == null) {
                                    knownType = ArrayMap.class;
                                }

                                this.writeObjectStart(v3, knownType);
                                v12 = value;
                                v7 = 0;
                                v13 = ((ArrayMap)v12).size;
                                while(v7 < v13) {
                                    this.writer.name(this.convertToString(((ArrayMap)v12).keys[v7]));
                                    this.writeValue(((ArrayMap)v12).values[v7], elementType, null);
                                    ++v7;
                                }

                                this.writeObjectEnd();
                                return;
                            label_245:
                                if(!(value instanceof ObjectMap)) {
                                    goto label_275;
                                }

                                if(knownType == null) {
                                    knownType = OrderedMap.class;
                                }

                                this.writeObjectStart(v3, knownType);
                                v8 = ((ObjectMap)value).entries().iterator();
                                while(v8.hasNext()) {
                                    v5 = v8.next();
                                    this.writer.name(this.convertToString(((Entry)v5).key));
                                    this.writeValue(((Entry)v5).value, elementType, null);
                                }

                                this.writeObjectEnd();
                                return;
                            label_275:
                                if(!(value instanceof Map)) {
                                    goto label_304;
                                }

                                if(knownType == null) {
                                    knownType = HashMap.class;
                                }

                                this.writeObjectStart(v3, knownType);
                                v8 = ((Map)value).entrySet().iterator();
                                while(v8.hasNext()) {
                                    v5 = v8.next();
                                    this.writer.name(this.convertToString(((Map$Entry)v5).getKey()));
                                    this.writeValue(((Map$Entry)v5).getValue(), elementType, null);
                                }

                                this.writeObjectEnd();
                                return;
                            label_304:
                                if(!ClassReflection.isAssignableFrom(Enum.class, v3)) {
                                    goto label_329;
                                }

                                if(knownType != null && (knownType.equals(v3))) {
                                    this.writer.value(value);
                                    return;
                                }

                                this.writeObjectStart(v3, null);
                                this.writer.name("value");
                                this.writer.value(value);
                                this.writeObjectEnd();
                                return;
                            label_329:
                                this.writeObjectStart(v3, knownType);
                                this.writeFields(value);
                                this.writeObjectEnd();
                                return;
                            }
                        }
                    }

                    this.writeObjectStart(v3, null);
                    this.writeValue("value", value);
                    this.writeObjectEnd();
                    return;
                }

                this.writer.value(value);
                return;
            }
            catch(IOException v6) {
                throw new SerializationException(((Throwable)v6));
            }
        }
        else {
            goto label_6;
        }
    }

    public void writeValue(Object value) {
        Class v1 = null;
        if(value == null) {
            this.writeValue(value, v1, v1);
        }
        else {
            this.writeValue(value, value.getClass(), v1);
        }
    }

    public void writeValue(Object value, Class knownType) {
        this.writeValue(value, knownType, null);
    }

    public void writeValue(String name, Object value) {  // has try-catch handlers
        Class v2 = null;
        try {
            this.writer.name(name);
            if(value != null) {
                goto label_9;
            }
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }

        this.writeValue(value, v2, v2);
        return;
    label_9:
        this.writeValue(value, value.getClass(), v2);
    }

    public void writeValue(String name, Object value, Class knownType) {  // has try-catch handlers
        try {
            this.writer.name(name);
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }

        this.writeValue(value, knownType, null);
    }

    public void writeValue(String name, Object value, Class knownType, Class elementType) {  // has try-catch handlers
        try {
            this.writer.name(name);
        }
        catch(IOException v0) {
            throw new SerializationException(((Throwable)v0));
        }

        this.writeValue(value, knownType, elementType);
    }
}

