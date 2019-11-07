// Generated from Json.g4 by ANTLR 4.7.1

package com.example;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JsonParser}.
 */
public interface JsonListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JsonParser#json}.
	 * @param ctx the parse tree
	 */
	void enterJson(JsonParser.JsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonParser#json}.
	 * @param ctx the parse tree
	 */
	void exitJson(JsonParser.JsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonParser#jsonObject}.
	 * @param ctx the parse tree
	 */
	void enterJsonObject(JsonParser.JsonObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonParser#jsonObject}.
	 * @param ctx the parse tree
	 */
	void exitJsonObject(JsonParser.JsonObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonParser#keyValuePair}.
	 * @param ctx the parse tree
	 */
	void enterKeyValuePair(JsonParser.KeyValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonParser#keyValuePair}.
	 * @param ctx the parse tree
	 */
	void exitKeyValuePair(JsonParser.KeyValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonParser#primitive}.
	 * @param ctx the parse tree
	 */
	void enterPrimitive(JsonParser.PrimitiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonParser#primitive}.
	 * @param ctx the parse tree
	 */
	void exitPrimitive(JsonParser.PrimitiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(JsonParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(JsonParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(JsonParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(JsonParser.BoolContext ctx);
}