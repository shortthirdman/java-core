package com.shortthirdman.core.common;

import com.shortthirdman.common.JSONTokenizer.TokenType;

import junit.framework.TestCase;

public class JSONTokenizerTest extends TestCase {

  public void test1_blank() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("");
    assertEquals(TokenType.EOF, tokenizer.nextToken());
    assertEquals("", tokenizer.getToken());
  }

  public void test2_null() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer(null);
    assertEquals(TokenType.EOF, tokenizer.nextToken());
    assertEquals("null", tokenizer.getToken());
  }

  public void test3_keyvalue() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\"aaa\":123}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals("{\"aaa\":123}", tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("aaa", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("123", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test4_array() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("[\"aaa\",123,\"b\"]");
    assertEquals(TokenType.START_BRACKET, tokenizer.currentTokenType());
    assertEquals("[\"aaa\",123,\"b\"]", tokenizer.getToken());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("\"aaa\"", tokenizer.getToken());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("123", tokenizer.getToken());
    assertEquals(TokenType.END_BRACKET, tokenizer.nextToken());
    assertEquals("\"b\"", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test5_emptyobject() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals("{}", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test6_emptyarray() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("[]");
    assertEquals(TokenType.START_BRACKET, tokenizer.currentTokenType());
    assertEquals("[]", tokenizer.getToken());
    assertEquals(TokenType.END_BRACKET, tokenizer.nextToken());
    assertEquals("", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test7_multiplekeyvalue() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer(
        "{\"aaa\":123,\"bbb\":true}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals("{\"aaa\":123,\"bbb\":true}", tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("aaa", tokenizer.getToken());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("123", tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("bbb", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("true", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test8_keyvaluewithsinglequote() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{'aaa':'123'}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals("{'aaa':'123'}", tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("aaa", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("'123'", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test9_blankwithtab() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\t}");
    assertEquals("{\t}", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("", tokenizer.getToken());
  }

  public void test10_blankwithbackspace() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\b}");
    assertEquals("{\b}", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("", tokenizer.getToken());
  }

  public void test11_blankwithformfeed() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\f}");
    assertEquals("{\f}", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("", tokenizer.getToken());
  }

  public void test12_blankwithnewline() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\n}");
    assertEquals("{\n}", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("", tokenizer.getToken());
  }

  public void test13_blankwithcarriagereturn() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\r}");
    assertEquals("{\r}", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("", tokenizer.getToken());
  }

  public void test14_keyvalue_nest1() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer(
        "{\"aaa\":123, \"bbb\":{\"b1\":true}}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals("{\"aaa\":123, \"bbb\":{\"b1\":true}}", tokenizer
        .getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("aaa", tokenizer.getToken());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("123", tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("bbb", tokenizer.getToken());
    {
      assertEquals(TokenType.START_BRACE, tokenizer.nextToken());
      assertEquals("{\"b1\":true}}", tokenizer.getToken());
      assertEquals(TokenType.COLON, tokenizer.nextToken());
      assertEquals("b1", tokenizer.getToken());
      assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
      assertEquals("true", tokenizer.getToken());
    }
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
  }

  public void test15_ignorableSpaceShouldIgnoreAtObject() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\"aaa\"\r\t:\n123   }");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("aaa", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("123", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test16_ignorableSpaceShouldIgnoreAtArray() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer(
        "[ \"aaa\"\t,123\b,\f\'b\'     ]");
    assertEquals(TokenType.START_BRACKET, tokenizer.currentTokenType());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("\"aaa\"", tokenizer.getToken());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("123", tokenizer.getToken());
    assertEquals(TokenType.END_BRACKET, tokenizer.nextToken());
    assertEquals("\'b\'", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test17_blankwithlotsofignorables() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\t\r\n    \t}");
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("", tokenizer.getToken());
  }

  public void test18_keyvalue_nest3_array() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer(
        "{\"aaa\":123, \"bbb\":{\"b1\":true},\t\"ccc\":\"fuga\", \"array1\":[\"1.1233333333000000000000001\"\r,\b1.1233333333000000000000001, \"3.0\"]}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("aaa", tokenizer.getToken());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("123", tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("bbb", tokenizer.getToken());
    {
      assertEquals(TokenType.START_BRACE, tokenizer.nextToken());
      assertEquals(TokenType.COLON, tokenizer.nextToken());
      assertEquals("b1", tokenizer.getToken());
      assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
      assertEquals("true", tokenizer.getToken());
    }
    assertEquals(TokenType.END_COMMA, tokenizer.nextToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("ccc", tokenizer.getToken());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("\"fuga\"", tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("array1", tokenizer.getToken());
    {
      assertEquals(TokenType.START_BRACKET, tokenizer.nextToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("\"1.1233333333000000000000001\"", tokenizer
          .getToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("1.1233333333000000000000001", tokenizer.getToken());
      assertEquals(TokenType.END_BRACKET, tokenizer.nextToken());
      assertEquals("\"3.0\"", tokenizer.getToken());
    }
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test18_stringEnquote() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\"a'aa\":\"?????\"}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("a'aa", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("\"?????\"", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test19_booleanarray() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("[true, false,true]");
    assertEquals(TokenType.START_BRACKET, tokenizer.currentTokenType());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("true", tokenizer.getToken());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("false", tokenizer.getToken());
    assertEquals(TokenType.END_BRACKET, tokenizer.nextToken());
    assertEquals("true", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test20_nestarray() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("[1, [2, 3, 4, 5], 3]");
    assertEquals(TokenType.START_BRACKET, tokenizer.currentTokenType());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("1", tokenizer.getToken());
    {
      assertEquals(TokenType.START_BRACKET, tokenizer.nextToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("2", tokenizer.getToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("3", tokenizer.getToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("4", tokenizer.getToken());
      assertEquals(TokenType.END_BRACKET, tokenizer.nextToken());
      assertEquals("5", tokenizer.getToken());
    }
    assertEquals(TokenType.END_COMMA, tokenizer.nextToken());
    assertEquals(TokenType.END_BRACKET, tokenizer.nextToken());
    assertEquals("3", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test21_nestarrayandobjects() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer(
        "[1, [2, 3, 4, 5], \"key\":{true, false, \"hoge\", \"array\":[0.001, 0.00001, 1.2E-7]   }, 3]");
    assertEquals(TokenType.START_BRACKET, tokenizer.currentTokenType());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("1", tokenizer.getToken());
    {
      assertEquals(TokenType.START_BRACKET, tokenizer.nextToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("2", tokenizer.getToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("3", tokenizer.getToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("4", tokenizer.getToken());
      assertEquals(TokenType.END_BRACKET, tokenizer.nextToken());
      assertEquals("5", tokenizer.getToken());
    }
    assertEquals(TokenType.END_COMMA, tokenizer.nextToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("key", tokenizer.getToken());
    {
      assertEquals(TokenType.START_BRACE, tokenizer.nextToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("true", tokenizer.getToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("false", tokenizer.getToken());
      assertEquals(TokenType.COMMA, tokenizer.nextToken());
      assertEquals("\"hoge\"", tokenizer.getToken());
      assertEquals(TokenType.COLON, tokenizer.nextToken());
      assertEquals("array", tokenizer.getToken());
      {
        assertEquals(TokenType.START_BRACKET, tokenizer.nextToken());
        assertEquals(TokenType.COMMA, tokenizer.nextToken());
        assertEquals("0.001", tokenizer.getToken());
        assertEquals(TokenType.COMMA, tokenizer.nextToken());
        assertEquals("0.00001", tokenizer.getToken());
        assertEquals(TokenType.END_BRACKET, tokenizer.nextToken());
        assertEquals("1.2E-7", tokenizer.getToken());
      }
      assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    }
    assertEquals(TokenType.END_COMMA, tokenizer.nextToken());
    assertEquals(TokenType.END_BRACKET, tokenizer.nextToken());
    assertEquals("3", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test22_stringSingleEnquote() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\'a'aa\':\"?????\"}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("a'aa", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("\"?????\"", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test23_keyMustBeString() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{aaa:123}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("aaa", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("123", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test24_keyvalue_nestOnlyNestObject() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer("{\"bbb\":{\"b1\":true}}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals("{\"bbb\":{\"b1\":true}}", tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("bbb", tokenizer.getToken());
    assertEquals(TokenType.START_BRACE, tokenizer.nextToken());
    assertEquals("{\"b1\":true}}", tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("b1", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("true", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
  }

  public void test25_keyvalue_nestOnlyNestObject2() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer(
        "{\"bbb\":{\"b1\":true}, \"vvv\":null}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals("{\"bbb\":{\"b1\":true}, \"vvv\":null}", tokenizer
        .getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("bbb", tokenizer.getToken());
    {
      assertEquals(TokenType.START_BRACE, tokenizer.nextToken());
      assertEquals("{\"b1\":true}, \"vvv\":null}", tokenizer.getToken());
      assertEquals(TokenType.COLON, tokenizer.nextToken());
      assertEquals("b1", tokenizer.getToken());
      assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
      assertEquals("true", tokenizer.getToken());
    }
    assertEquals(TokenType.END_COMMA, tokenizer.nextToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("vvv", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("null", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test26_keyvalue_deepNest1() throws Exception {
    final String json = "{\"bbb\":{\"dates\":{\"from\":20090101,\n \"to\":20091231},\t\"b1\":true}, \"vvv\":null}";
    JSONTokenizer tokenizer = new JSONTokenizer(json);
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals(json, tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("bbb", tokenizer.getToken());
    {
      assertEquals(TokenType.START_BRACE, tokenizer.nextToken());
      assertEquals(
          "{\"dates\":{\"from\":20090101,\n \"to\":20091231},\t\"b1\":true}, \"vvv\":null}",
          tokenizer.getToken());
      assertEquals(TokenType.COLON, tokenizer.nextToken());
      assertEquals("dates", tokenizer.getToken());
      {
        assertEquals(TokenType.START_BRACE, tokenizer.nextToken());
        assertEquals(
            "{\"from\":20090101,\n \"to\":20091231},\t\"b1\":true}, \"vvv\":null}",
            tokenizer.getToken());
        assertEquals(TokenType.COLON, tokenizer.nextToken());
        assertEquals("from", tokenizer.getToken());
        assertEquals(TokenType.COMMA, tokenizer.nextToken());
        assertEquals("20090101", tokenizer.getToken());
        assertEquals(TokenType.COLON, tokenizer.nextToken());
        assertEquals("to", tokenizer.getToken());
        assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
        assertEquals("20091231", tokenizer.getToken());
      }
      assertEquals(TokenType.END_COMMA, tokenizer.nextToken());
      assertEquals(TokenType.COLON, tokenizer.nextToken());
      assertEquals("b1", tokenizer.getToken());
      assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
      assertEquals("true", tokenizer.getToken());
    }
    assertEquals(TokenType.END_COMMA, tokenizer.nextToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("vvv", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("null", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }

  public void test27_keyvalue_nest2() throws Exception {
    JSONTokenizer tokenizer = new JSONTokenizer(
        "{\"aaa\":123, \"bbb\":{\"b1\":true},\t\"ccc\":\"fuga\"}");
    assertEquals(TokenType.START_BRACE, tokenizer.currentTokenType());
    assertEquals("{\"aaa\":123, \"bbb\":{\"b1\":true},\t\"ccc\":\"fuga\"}",
        tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("aaa", tokenizer.getToken());
    assertEquals(TokenType.COMMA, tokenizer.nextToken());
    assertEquals("123", tokenizer.getToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("bbb", tokenizer.getToken());
    {
      assertEquals(TokenType.START_BRACE, tokenizer.nextToken());
      assertEquals("{\"b1\":true},\t\"ccc\":\"fuga\"}", tokenizer
          .getToken());
      assertEquals(TokenType.COLON, tokenizer.nextToken());
      assertEquals("b1", tokenizer.getToken());
      assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
      assertEquals("true", tokenizer.getToken());
    }
    assertEquals(TokenType.END_COMMA, tokenizer.nextToken());
    assertEquals(TokenType.COLON, tokenizer.nextToken());
    assertEquals("ccc", tokenizer.getToken());
    assertEquals(TokenType.END_BRACE, tokenizer.nextToken());
    assertEquals("\"fuga\"", tokenizer.getToken());
    assertEquals(TokenType.EOF, tokenizer.nextToken());
  }
}