package com.shortthirdman.core.common;

import junit.framework.TestCase;
import java.io.IOException;
import java.util.*;

public class TestObjectBuilder extends TestCase {

  public void test(String val, Object expected) throws IOException {
    val = val.replace('\'','"');
    JSONParser p = TestJSONParser.getParser(val);
    Object v = ObjectBuilder.getVal(p);

    String s1 = JSONUtil.toJSON(v);
    String s2 = JSONUtil.toJSON(expected);
    assertEquals(s1, s2);

    JSONParser p2 = TestJSONParser.getParser(s1);
    Object v2 = ObjectBuilder.getVal(p2);
    String s3 = JSONUtil.toJSON(v2);
    assertEquals(s1, s3);
  }

  public static List A(Object... lst) {
     return Arrays.asList(lst);
  }
  
  public static Map O(Object... lst) {
    LinkedHashMap map = new LinkedHashMap();
    for (int i = 0; i < lst.length; i += 2) {
      map.put(lst[i].toString(), lst[i+1]);
    }
    return map;
  }

  public void testVariations(String str, Object expected) throws IOException {
    test("[" + str + "]", A(expected));
    test("[" + str + "," + str + "]", A(expected,expected));
    test("{'foo':" + str + "}", O("foo",expected));
    test("{'foo':" + str + ",'bar':{'a':" + str + "},'baz':[" + str + "]}", O("foo",expected,"bar",O("a",expected),"baz",A(expected)));
  }

  public void testBuilder() throws IOException {
    testVariations("[]", A());
    testVariations("[]", A());
    testVariations("{}", O());
    testVariations("[[]]", A(A()));
    testVariations("{'foo':{}}", O("foo",O()));
    testVariations("[false,true,1,1.4,null,'hi']", A(false,true,1,1.4,null,"hi"));
  }
}