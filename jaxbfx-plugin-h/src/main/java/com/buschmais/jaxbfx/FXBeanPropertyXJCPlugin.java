/*
 * Copyright 2014 buschmais GbR
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package com.buschmais.jaxbfx;

import com.sun.codemodel.*;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.LocatorImpl;

import java.lang.reflect.Field;
import java.util.Map;

import static com.buschmais.jaxbfx.Types.*;

/**
 * JAXB2 XJC plugin to generate JavaFX properties.
 *
 * @author Tobias Israel
 * @see <a href="http://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm">Using JavaFX Properties and Binding</a>
 * @since 1.0.0
 */
public class FXBeanPropertyXJCPlugin extends Plugin {

    public static final String PROPERTY_POSTFIX = "Property";
    public static final String GENERATED_BY_DOC_COMMENT = "Generated by FXBeanPropertyXJCPlugin.";
    private static final String GETTER_PREFIX = "get";
    private static final String IS_GETTER_PREFIX = "is";

    @Override
    public String getOptionName() {
        return "Xgenerate-fx-properties";
    }

    @Override
    public String getUsage() {
        return "Xgenerate-fx-properties     generate JavaFX properties";
    }

    @Override
    public boolean run(Outline outline, Options options, ErrorHandler errorHandler) throws SAXException {

        try {

            for (ClassOutline classOutline : outline.getClasses()) {
                JDefinedClass implClass = classOutline.implClass;
                clearAllAnnotations(implClass);
                JCodeModel codeModel = classOutline.parent().getCodeModel();
                FieldOutline[] declaredFields = classOutline.getDeclaredFields();
                for (FieldOutline fieldOutline : declaredFields) {
                    CPropertyInfo propertyInfo = fieldOutline.getPropertyInfo();
                    JFieldVar field = implClass.fields().get(propertyInfo.getName(false));

                    if (field != null) {
                        String qualifiedFieldTypeName = field.type().fullName();
                        if (JAVA_LANG_STRING.equals(qualifiedFieldTypeName)) {
                            JType stringPropertyType = codeModel.parseType(JAVAFX_STRING_PROPERTY);
                            JType simpleStringPropertyType = codeModel.parseType(JAVAFX_SIMPLE_STRING_PROPERTY);
                            JType stringType = codeModel.parseType(JAVA_LANG_STRING);
                            createFXEnabledPropertyAccess(stringType, stringPropertyType, simpleStringPropertyType, propertyInfo, implClass, codeModel, GETTER_PREFIX, false);
                        } else if (JAVA_LANG_BOOLEAN.equals(qualifiedFieldTypeName)) {
                            JType booleanPropertyType = codeModel.parseType(JAVAFX_BOOLEAN_PROPERTY);
                            JType simpleBooleanPropertyType = codeModel.parseType(JAVAFX_SIMPLE_BOOLEAN_PROPERTY);
                            JType booleanType = codeModel.parseType(JAVA_LANG_BOOLEAN);
                            createFXEnabledPropertyAccess(booleanType, booleanPropertyType, simpleBooleanPropertyType, propertyInfo, implClass, codeModel, IS_GETTER_PREFIX, false);
                        } else if (JAVA_PRIMITIVE_BOOLEAN.equals(qualifiedFieldTypeName)) {
                            JType booleanPropertyType = codeModel.parseType(JAVAFX_BOOLEAN_PROPERTY);
                            JType simpleBooleanPropertyType = codeModel.parseType(JAVAFX_SIMPLE_BOOLEAN_PROPERTY);
                            JType booleanType = codeModel.parseType(JAVA_PRIMITIVE_BOOLEAN);
                            createFXEnabledPropertyAccess(booleanType, booleanPropertyType, simpleBooleanPropertyType, propertyInfo, implClass, codeModel, IS_GETTER_PREFIX, false);
                        } else if (JAVA_LANG_DOUBLE.equals(qualifiedFieldTypeName)) {
                            JType doublePropertyType = codeModel.parseType(JAVAFX_DOUBLE_PROPERTY);
                            JType simpleDoublePropertyType = codeModel.parseType(JAVAFX_SIMPLE_DOUBLE_PROPERTY);
                            JType doubleType = codeModel.parseType(JAVA_LANG_DOUBLE);
                            createFXEnabledPropertyAccess(doubleType, doublePropertyType, simpleDoublePropertyType, propertyInfo, implClass, codeModel, GETTER_PREFIX, false);
                        } else if (JAVA_PRIMITIVE_DOUBLE.equals(qualifiedFieldTypeName)) {
                            JType doublePropertyType = codeModel.parseType(JAVAFX_DOUBLE_PROPERTY);
                            JType simpleDoublePropertyType = codeModel.parseType(JAVAFX_SIMPLE_DOUBLE_PROPERTY);
                            JType doubleType = codeModel.parseType(JAVA_PRIMITIVE_DOUBLE);
                            createFXEnabledPropertyAccess(doubleType, doublePropertyType, simpleDoublePropertyType, propertyInfo, implClass, codeModel, GETTER_PREFIX, false);
                        } else if (JAVA_LANG_FLOAT.equals(qualifiedFieldTypeName)) {
                            JType floatPropertyType = codeModel.parseType(JAVAFX_FLOAT_PROPERTY);
                            JType simpleFloatPropertyType = codeModel.parseType(JAVAFX_SIMPLE_FLOAT_PROPERTY);
                            JType floatType = codeModel.parseType(JAVA_LANG_FLOAT);
                            createFXEnabledPropertyAccess(floatType, floatPropertyType, simpleFloatPropertyType, propertyInfo, implClass, codeModel, GETTER_PREFIX, false);
                        } else if (JAVA_PRIMITIVE_FLOAT.equals(qualifiedFieldTypeName)) {
                            JType floatPropertyType = codeModel.parseType(JAVAFX_FLOAT_PROPERTY);
                            JType simpleFloatPropertyType = codeModel.parseType(JAVAFX_SIMPLE_FLOAT_PROPERTY);
                            JType floatType = codeModel.parseType(JAVA_PRIMITIVE_FLOAT);
                            createFXEnabledPropertyAccess(floatType, floatPropertyType, simpleFloatPropertyType, propertyInfo, implClass, codeModel, GETTER_PREFIX, false);
                        } else if (JAVA_LANG_INTEGER.equals(qualifiedFieldTypeName)) {
                            JType integerPropertyType = codeModel.parseType(JAVAFX_INTEGER_PROPERTY);
                            JType simpleIntegerPropertyType = codeModel.parseType(JAVAFX_SIMPLE_INTEGER_PROPERTY);
                            JType integerType = codeModel.parseType(JAVA_LANG_INTEGER);
                            createFXEnabledPropertyAccess(integerType, integerPropertyType, simpleIntegerPropertyType, propertyInfo, implClass, codeModel, GETTER_PREFIX, false);
                        } else if (JAVA_PRIMITIVE_INTEGER.equals(qualifiedFieldTypeName)) {
                            JType integerPropertyType = codeModel.parseType(JAVAFX_INTEGER_PROPERTY);
                            JType simpleIntegerPropertyType = codeModel.parseType(JAVAFX_SIMPLE_INTEGER_PROPERTY);
                            JType integerType = codeModel.parseType(JAVA_PRIMITIVE_INTEGER);
                            createFXEnabledPropertyAccess(integerType, integerPropertyType, simpleIntegerPropertyType, propertyInfo, implClass, codeModel, GETTER_PREFIX, false);
                        } else if (JAVA_LANG_LONG.equals(qualifiedFieldTypeName)) {
                            JType longPropertyType = codeModel.parseType(JAVAFX_LONG_PROPERTY);
                            JType simpleLongPropertyType = codeModel.parseType(JAVAFX_SIMPLE_LONG_PROPERTY);
                            JType longType = codeModel.parseType(JAVA_LANG_LONG);
                            createFXEnabledPropertyAccess(longType, longPropertyType, simpleLongPropertyType, propertyInfo, implClass, codeModel, GETTER_PREFIX, false);
                        } else if (JAVA_PRIMITIVE_LONG.equals(qualifiedFieldTypeName)) {
                            JType longPropertyType = codeModel.parseType(JAVAFX_LONG_PROPERTY);
                            JType simpleLongPropertyType = codeModel.parseType(JAVAFX_SIMPLE_LONG_PROPERTY);
                            JType longType = codeModel.parseType(JAVA_PRIMITIVE_LONG);
                            createFXEnabledPropertyAccess(longType, longPropertyType, simpleLongPropertyType, propertyInfo, implClass, codeModel, GETTER_PREFIX, false);
                        } else if (qualifiedFieldTypeName.startsWith(JAVA_UTIL_LIST)) {
                            JType listPropertyType = codeModel.parseType(JAVAFX_LIST_PROPERTY);
                            JType simpleListPropertyType = codeModel.parseType(JAVAFX_SIMPLE_LIST_PROPERTY);
                            JType listType = codeModel.parseType(JAVA_UTIL_LIST);
                            createFXEnabledListPropertyAccess(listType, listPropertyType, simpleListPropertyType, propertyInfo, implClass, codeModel);
                        } else {
                            JType objectPropertyType = codeModel.parseType(JAVAFX_OBJECT_PROPERTY);
                            JType simpleObjectPropertyType = codeModel.parseType(JAVAFX_SIMPLE_OBJECT_PROPERTY);
                            JType objectType = field.type();
                            createFXEnabledPropertyAccess(objectType, objectPropertyType, simpleObjectPropertyType, propertyInfo, implClass, codeModel, GETTER_PREFIX, true);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            SAXParseException saxEx = new SAXParseException("Unable to resolve required class.", new LocatorImpl(), e);
            errorHandler.fatalError(saxEx);
            throw saxEx;
        }

        return true;
    }

    private void createFXEnabledPropertyAccess(JType plainType, JType propertyType, JType simplePropertyType, CPropertyInfo propertyInfo, JDefinedClass implClass, JCodeModel codeModel, String getterPrefix, boolean applyGenerics) {
        String oPublicFieldName = propertyInfo.getName(true);
        String oPrivateFieldName = propertyInfo.getName(false);

        //add new Field for property proxy
        String nPublicFieldName = oPrivateFieldName + "Proxy";
        JFieldVar nFieldVar;
        if (applyGenerics) {
            JClass narrowedPropertyType = codeModel.ref(propertyType.fullName()).narrow(plainType);
            JClass narrowedSimplePropertyType = codeModel.ref(simplePropertyType.fullName()).narrow(plainType);
            nFieldVar = implClass.field(JMod.PRIVATE | JMod.FINAL | JMod.TRANSIENT, narrowedPropertyType, nPublicFieldName, JExpr._new(narrowedSimplePropertyType));
        } else {
            nFieldVar = implClass.field(JMod.PRIVATE | JMod.FINAL | JMod.TRANSIENT, codeModel.ref(propertyType.fullName()), nPublicFieldName, JExpr._new(simplePropertyType));
        }

        //remove old getter and add a new one, interacting though the property proxy
        String getterName = getterPrefix + oPublicFieldName;
        JMethod oGetter = implClass.getMethod(getterName, new JType[0]);
        JDocComment javadoc = oGetter.javadoc();
        implClass.methods().remove(oGetter);
        JMethod nGetter = implClass.method(JMod.PUBLIC, plainType, getterName);

        /*
         * @author Heine
         *
         * Finally all of this is unnecessary:
         *
         * XJC doesn't invoke the setters when unmarshalling. Using the PROPERTY accessor type doesn't fix it either.
         * This is a dirty workaround where the getter invokes the setter first.
         */
        //JExpression defaultValueExpr = JExpr._null();
        //if(propertyType.name().toLowerCase().contains("object") || propertyType.name().toLowerCase().contains("string")){
        //    defaultValueExpr = JExpr._null();
        //}
        //if (propertyType.name().toLowerCase().contains("int") || propertyType.name().toLowerCase().contains("float")) {
        //    defaultValueExpr = JExpr.lit(0);
        //}
        //nGetter.body().add(JExpr.invoke(JExpr._this().ref(nFieldVar), "set").arg(JExpr.ref(oPrivateFieldName)));
        /*
         */

        nGetter.body()._return(JExpr._this().ref(nFieldVar).invoke("get"));
        nGetter.javadoc().append(javadoc);

        //modify the setter
        String setterName = "set" + oPublicFieldName;
        JMethod setter = implClass.getMethod(setterName, new JType[]{
                plainType
        });
        setter.body().directStatement("this." + nPublicFieldName + ".set(value);");

        //add the property getter
        String propertyGetterName = oPrivateFieldName + PROPERTY_POSTFIX;
        JMethod propertyGetter;
        if (applyGenerics) {
            JClass narrowedPropertyType = codeModel.ref(propertyType.fullName()).narrow(plainType);
            propertyGetter = implClass.method(JMod.PUBLIC, narrowedPropertyType, propertyGetterName);
        } else {
            propertyGetter = implClass.method(JMod.PUBLIC, propertyType, propertyGetterName);
        }
        propertyGetter.javadoc().append(GENERATED_BY_DOC_COMMENT);
        propertyGetter.body()._return(JExpr._this().ref(nFieldVar));
    }

    private void createFXEnabledListPropertyAccess(JType plainType, JType propertyType, JType simplePropertyType, CPropertyInfo propertyInfo, JDefinedClass implClass, JCodeModel codeModel) throws ClassNotFoundException {
        String oPublicFieldName = propertyInfo.getName(true);
        String oPrivateFieldName = propertyInfo.getName(false);

        JFieldVar collectionField = implClass.fields().get(oPrivateFieldName);
        JClass elementType = ((JClass) collectionField.type()).getTypeParameters().get(0);
        JClass narrowedPropertyType = codeModel.ref(propertyType.fullName()).narrow(elementType);

        //remove old getter and add a new one, interacting though the property proxy
        String getterName = GETTER_PREFIX + oPublicFieldName;
        JMethod oGetter = implClass.getMethod(getterName, new JType[0]);
        JDocComment javadoc = oGetter.javadoc();
        implClass.methods().remove(oGetter);
        JMethod nGetter = implClass.method(JMod.PUBLIC, codeModel.ref(plainType.fullName()).narrow(elementType), getterName);
        JClass narrowedWrapperType = ((JClass) simplePropertyType).narrow(elementType);


        //JBlock ifBlock = nGetter.body()._if(collectionField.eq(JExpr._null()))._elseif(JExpr._null())._then();
        JClass arrayListType = (JClass) codeModel.parseType(JAVA_UTIL_ARRAYLIST);
        JClass jxCollectionsType = (JClass) codeModel.parseType(JAVAFX_FXCOLLECTIONS);


        /*
         * @author Heine
         * The cast always throws exceptions. Little patch to stop them.
         */
        JConditional ifBlockCond = nGetter.body()._if(collectionField.eq(JExpr._null()));
        JVar backingList = ifBlockCond._then().decl(plainType, "backingList", JExpr._new(arrayListType.narrow(elementType)));
        ifBlockCond._then().assign(collectionField, JExpr._new(narrowedWrapperType).arg(jxCollectionsType.staticInvoke("observableArrayList").arg(backingList)));
        ifBlockCond._elseif(collectionField._instanceof(codeModel.parseType(JAVA_UTIL_ARRAYLIST)))._then()
                .assign(collectionField, JExpr._new(narrowedWrapperType).arg(jxCollectionsType.staticInvoke("observableArrayList").arg(collectionField)));
        /*
         *
         */

        JVar wrapperVar = nGetter.body().decl(narrowedWrapperType, oPrivateFieldName + "Wrapper", JExpr.cast(narrowedWrapperType, JExpr._this().ref(collectionField)));
        nGetter.body()._return(wrapperVar.invoke("get"));
        nGetter.javadoc().append(javadoc);

        //add the property getter
        String propertyGetterName = oPrivateFieldName + PROPERTY_POSTFIX;
        JMethod propertyGetter = implClass.method(JMod.PUBLIC, narrowedPropertyType, propertyGetterName);
        propertyGetter.javadoc().append(GENERATED_BY_DOC_COMMENT);
        propertyGetter.body()._return(JExpr.cast(narrowedPropertyType, JExpr._this().ref(collectionField)));

        /*
         * @author Heine
         * Yes, we DO need the setter for the marshalling and unmarshalling.
         */
        String setterName = "set" + oPublicFieldName;
        JMethod setter = implClass.method(JMod.PUBLIC, (JType)JType.parse(codeModel,"void"), setterName);
        setter.param(codeModel.ref(plainType.fullName()).narrow(elementType), oPrivateFieldName);
        setter.body().assign(JExpr._this().ref(collectionField), collectionField);

        /*
         *
         *
         */
    }

    private void clearAllAnnotations(JDefinedClass clazz) {
        try {
// clear class-level annotations
            Field f = JDefinedClass.class.getDeclaredField("annotations");
            f.setAccessible(true);
            f.set(clazz, null);

// clear field-level annotations
            Map<String, JFieldVar> fields = clazz.fields();
            for (JFieldVar fVar : fields.values()) {
                f = JVar.class.getDeclaredField("annotations");
                f.setAccessible(true);
                f.set(fVar, null);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
