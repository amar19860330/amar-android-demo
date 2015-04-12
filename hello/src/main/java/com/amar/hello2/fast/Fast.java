package com.amar.hello2.fast;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAM on 2015/4/10.
 */
public class Fast<T> {

    Activity activity;
    T invoker;
    View rootView;
    /**
     * 要匹配的id直接在activity中 0 <br/>
     * 还是要匹配的id间接存放在activity的布局文件中，如多次include同一个布局文件 1
     */
    int findType = 0;

    public Fast() {
    }

    /**
     * 要匹配的id直接在activity中
     *
     * @param invoker
     */
    public void scanInActivity(T invoker) {
        this.invoker = invoker;
        this.activity = (Activity) invoker;
        findType = 0;
        Class _class = invoker.getClass();
        dealViewById(getAllField(_class));

        dealClickById(getAllMethod(_class));
    }

    /**
     * 要匹配的id间接存放在activity的布局文件中，如多次include同一个布局文件
     *
     * @param invoker
     * @param rootView
     * @param activity 仅用于 getResources()
     */
    public void scanInView(T invoker, View rootView, Activity activity) {
        this.invoker = invoker;
        this.rootView = rootView;
        this.activity = activity;
        findType = 1;
    }

    private void dealClickById(List<Method> methodList) {
        if (methodList == null)
            return;
        for (final Method method : methodList) {
            if (method.isAnnotationPresent(FClickById.class)) {
                FClickById fClickById = method.getAnnotation(FClickById.class);
                int[] ids = fClickById.value();
                if (ids == null)
                    return;
                if (!method.isAccessible())
                    method.setAccessible(true);

                if (findType == 0)//直接在activity中查找
                {
                    for (int id : ids) {
                        View view = activity.findViewById(id);
                        addClickToView(method, view);
                    }
                } else if (findType == 1) {
                    for (int id : ids) {
                        View view = rootView.findViewById(id);
                        addClickToView(method, view);
                    }
                }
            }
        }
    }

    private void addClickToView(final Method method, final View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Class[] typeClass = method.getParameterTypes();
                    if (typeClass == null || typeClass.length == 0) {
                        method.invoke(invoker);
                    } else if (typeClass.length > 1) {
                        throw new IllegalArgumentException("参数数量不对");
                    } else if (typeClass.length == 1 && typeClass.equals(View.class.getClass())) {
                        method.invoke(invoker, view);
                    } else if (typeClass.length == 1 && typeClass.equals(View.class.getClass())) {
                        throw new IllegalArgumentException("参数类型不对");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dealViewById(List<Field> fieldList) {
        if (fieldList == null)
            return;
        for (Field field : fieldList) {
            try {
                if (field.isAnnotationPresent(FViewById.class)) {
                    FViewById fViewById = field.getAnnotation(FViewById.class);
                    int id = fViewById.value();
                    if (!field.isAccessible())
                        field.setAccessible(true);
                    if (findType == 0)//直接在activity中查找
                    {
                        View view = activity.findViewById(id);
                        field.set(invoker, view);
                    } else if (findType == 1) {
                        View view = rootView.findViewById(id);
                        field.set(invoker, view);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Field> fieldList = new ArrayList<>();
    private List<Method> methodList = new ArrayList<>();

    private List<Field> getAllField(Class _class) {
        getAllFieldByRecursive(_class);

        return fieldList;
    }

    private void getAllFieldByRecursive(Class _class) {
        Field[] declaredFields = _class.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(FViewById.class)) {
                fieldList.add(field);
            }
        }
        if (_class.getSuperclass() != Object.class) {
            getAllFieldByRecursive(_class.getSuperclass());
        }
    }

    private List<Method> getAllMethod(Class _class) {
        getAllMethodByRecursive(_class);
        return methodList;
    }

    private void getAllMethodByRecursive(Class _class) {
        Method[] declaredMethods = _class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(FClickById.class)) {
                methodList.add(method);
            }
        }
        if (_class.getSuperclass() != Object.class) {
            getAllMethodByRecursive(_class.getSuperclass());
        }
    }
}
