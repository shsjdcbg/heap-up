package pers.dyx.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Description：
 * 辅助验证参数的断言实用程序类。
 * 对于在运行时及早清晰地识别程序员错误很有用。
 * 参考Spring 源码
 *
 * @author dyx
 * @date 2020/2/14 14:46
 */
public class Assert {

    /**
     * 声明一个布尔表达式，如果测试结果为{@code false}，则抛出{@code IllegalArgumentException}
     * <pre class="code">Assert.isTrue(flag, "The value must be greater than zero");</pre>
     *
     * @param expression 布尔表达式
     * @param message    消息，如果断言失败，则使用异常消息
     * @throws IllegalArgumentException 如果表达式为{@code false}，则抛出IllegalArgumentException
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 声明一个布尔表达式，如果测试结果为{@code false}，则抛出{@code IllegalArgumentException}。
     * <pre class="code">Assert.isTrue(flag);</pre>
     *
     * @param expression 布尔表达式
     * @throws IllegalArgumentException 如果表达式为{@code false}，则抛出IllegalArgumentException
     */
    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    /**
     * 断言一个对象是{@code null}
     * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
     *
     * @param object  要检查的对象
     * @param message 消息，如果断言失败，则使用异常消息
     * @throws IllegalArgumentException 如果对象不是{@code null}
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个对象是{@code null}
     * <pre class="code">Assert.isNull(value);</pre>
     *
     * @param object 要检查的对象
     * @throws IllegalArgumentException 如果对象不是{@code null}
     */
    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }

    /**
     * 断言一个对象不是{@code null}
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     *
     * @param object  要检查的对象
     * @param message 消息，如果断言失败，则使用异常消息
     * @throws IllegalArgumentException 如果对象是{@code null}
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个对象不是{@code null}
     * <pre class="code">Assert.notNull(clazz);</pre>
     *
     * @param object 要检查的对象
     * @throws IllegalArgumentException 如果对象不是{@code null}
     */
    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    /**
     * 断言给定的String不为空；也就是说，它不能为{@code null}，也不能为空字符串。
     * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
     *
     * @param text    要检查的对象
     * @param message 断言失败时使用的异常消息
     * @see StringUtils#hasLength
     */
    public static void hasLength(String text, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言给定的String不为空；也就是说，它不能为{@code null}，也不能为空字符串。
     * <pre class="code">Assert.hasLength(name);</pre>
     *
     * @param text 要检查的对象
     * @see StringUtils#hasLength
     */
    public static void hasLength(String text) {
        hasLength(text,
                "[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    /**
     * 断言给定的字符串具有有效的文本内容；也就是说，它不能为{@code null}，并且必须至少包含一个非空白字符。
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     *
     * @param text    要检查的对象
     * @param message 断言失败时使用的异常消息
     * @see StringUtils#hasText
     */
    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言给定的字符串具有有效的文本内容；也就是说，它不能为{@code null}，并且必须至少包含一个非空白字符。
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     *
     * @param text 要检查的对象
     * @see StringUtils#hasText
     */
    public static void hasText(String text) {
        hasText(text,
                "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    /**
     * 断言给定的文本不包含给定的子字符串。
     * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
     *
     * @param textToSearch 要检查的文字
     * @param substring    在文本中找到的子字符串
     * @param message      断言失败时使用的异常消息
     */
    public static void doesNotContain(String textToSearch, String substring, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言给定的文本不包含给定的子字符串。
     * <pre class="code">Assert.doesNotContain(name, "rod");</pre>
     *
     * @param textToSearch 要检查的文字
     * @param substring    在文本中找到的子字符串
     */
    public static void doesNotContain(String textToSearch, String substring) {
        doesNotContain(textToSearch, substring,
                "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
    }


    /**
     * 断言一个数组有元素；也就是说，它不能为{@code null}，并且必须至少包含一个元素
     * <pre class="code">Assert.notEmpty(array, "The array must have elements");</pre>
     *
     * @param array   要检查的数组
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果对象数组为{@code null}或没有元素
     */
    public static void notEmpty(Object[] array, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个数组有元素；也就是说，它不能为{@code null}，并且必须至少包含一个元素
     * <pre class="code">Assert.notEmpty(array, "The array must have elements");</pre>
     *
     * @param array 要检查的数组
     * @throws IllegalArgumentException 如果对象数组为{@code null}或没有元素
     */
    public static void notEmpty(Object[] array) {
        notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
    }

    /**
     * 断言数组没有空元素。
     * <pre class="code">Assert.noNullElements(array, "The array must have non-null elements");</pre>
     *
     * @param array   要检查的数组
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果对象数组包含{@code null}元素
     */
    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

    /**
     * 断言数组没有空元素。
     * <pre class="code">Assert.noNullElements(array);</pre>
     *
     * @param array 要检查的数组
     * @throws IllegalArgumentException 如果对象数组包含{@code null}元素
     */
    public static void noNullElements(Object[] array) {
        noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
    }

    /**
     * 断言集合具有元素；也就是说，它不能为{@code null}，并且必须至少包含一个元素。
     * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
     *
     * @param collection 要检查的集合
     * @param message    断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果集合为{@code null}或没有元素
     */
    public static void notEmpty(Collection collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言集合具有元素；也就是说，它不能为{@code null}，并且必须至少包含一个元素。
     * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
     *
     * @param collection 要检查的集合
     * @throws IllegalArgumentException 如果集合为{@code null}或没有元素
     */
    public static void notEmpty(Collection collection) {
        notEmpty(collection,
                "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    /**
     * 断言map中有条目；也就是说，它不能为{@code null}，并且必须至少有一个条目。
     * <pre class="code">Assert.notEmpty(map, "Map must have entries");</pre>
     *
     * @param map     要检查的map
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果地图是{@code null}或没有条目
     */
    public static void notEmpty(Map map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言map中有条目；也就是说，它不能为{@code null}，并且必须至少有一个条目。
     * <pre class="code">Assert.notEmpty(map);</pre>
     *
     * @param map 要检查的map
     * @throws IllegalArgumentException 如果地图是{@code null}或没有条目
     */
    public static void notEmpty(Map map) {
        notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }


    /**
     * 断言所提供的对象是所提供类的实例
     * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
     *
     * @param clazz class
     * @param obj   要检查的是对象
     * @throws IllegalArgumentException 如果对象不是clazz的实例
     * @see Class#isInstance
     */
    public static void isInstanceOf(Class<?> clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    /**
     * 断言所提供的对象是所提供类的实例
     * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
     *
     * @param type    要检查的类型
     * @param obj     要检查的是对象
     * @param message 一条消息，该消息将附加在函数本身生成的消息之前，并且可以用于提供上下文。
     * @throws IllegalArgumentException 如果对象不是clazz的实例
     * @see Class#isInstance
     */
    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(
                    (StringUtils.hasLength(message) ? message + " " : "") +
                            "Object of class [" + (obj != null ? obj.getClass().getName() : "null") +
                            "] must be an instance of " + type);
        }
    }

    /**
     * 断言{@code superType.isAssignableFrom（subType）}为{@code true}。
     * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
     *
     * @param superType 要检查的类型
     * @param subType   要检查的是对象
     * @throws IllegalArgumentException if the classes are not assignable
     */
    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }

    /**
     * 断言{@code superType.isAssignableFrom（subType）}为{@code true}。
     * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
     *
     * @param superType 要检查的类型
     * @param subType   要检查的是对象
     * @param message   一条消息，该消息将附加在函数本身生成的消息之前，并且可以用于提供上下文。
     * @throws IllegalArgumentException if the classes are not assignable
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
        }
    }


    /**
     * 声明一个布尔表达式，如果测试结果为{@code false}，则抛出{@code IllegalStateException}。
     * 如果希望在断言失败时引发IllegalArgumentException，请调用isTrue。
     * <pre class="code">Assert.state(id == null, "The id property must not already be initialized");</pre>
     *
     * @param expression 一个布尔表达式
     * @param message    断言失败时使用的异常消息
     * @throws IllegalStateException 如果表达式为{@code false}
     */
    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 声明一个布尔表达式，如果测试结果为{@code false}，则抛出{@link IllegalStateException}。
     * 如果您希望在断言失败时抛出{@link IllegalArgumentException}，请调用{@link #isTrue（boolean）}。
     * <pre class="code">Assert.state(id == null);</pre>
     *
     * @param expression 一个布尔表达式
     * @throws IllegalStateException 如果表达式为{@code false}
     */
    public static void state(boolean expression) {
        state(expression, "[Assertion failed] - this state invariant must be true");
    }

}
