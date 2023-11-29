// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Login.proto

package org.wxd.mmo.script.gamesr.login.message;

public interface ReqLoginOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Proto.Login.ReqLogin)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *账号
   * </pre>
   *
   * <code>string user = 1;</code>
   * @return The user.
   */
  java.lang.String getUser();
  /**
   * <pre>
   *账号
   * </pre>
   *
   * <code>string user = 1;</code>
   * @return The bytes for user.
   */
  com.google.protobuf.ByteString
      getUserBytes();

  /**
   * <pre>
   *秘钥
   * </pre>
   *
   * <code>string token = 2;</code>
   * @return The token.
   */
  java.lang.String getToken();
  /**
   * <pre>
   *秘钥
   * </pre>
   *
   * <code>string token = 2;</code>
   * @return The bytes for token.
   */
  com.google.protobuf.ByteString
      getTokenBytes();

  /**
   * <pre>
   *平台
   * </pre>
   *
   * <code>string sdkType = 3;</code>
   * @return The sdkType.
   */
  java.lang.String getSdkType();
  /**
   * <pre>
   *平台
   * </pre>
   *
   * <code>string sdkType = 3;</code>
   * @return The bytes for sdkType.
   */
  com.google.protobuf.ByteString
      getSdkTypeBytes();

  /**
   * <pre>
   *参数
   * </pre>
   *
   * <code>map&lt;string, string&gt; params = 4;</code>
   */
  int getParamsCount();
  /**
   * <pre>
   *参数
   * </pre>
   *
   * <code>map&lt;string, string&gt; params = 4;</code>
   */
  boolean containsParams(
      java.lang.String key);
  /**
   * Use {@link #getParamsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getParams();
  /**
   * <pre>
   *参数
   * </pre>
   *
   * <code>map&lt;string, string&gt; params = 4;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getParamsMap();
  /**
   * <pre>
   *参数
   * </pre>
   *
   * <code>map&lt;string, string&gt; params = 4;</code>
   */

  /* nullable */
java.lang.String getParamsOrDefault(
      java.lang.String key,
      /* nullable */
java.lang.String defaultValue);
  /**
   * <pre>
   *参数
   * </pre>
   *
   * <code>map&lt;string, string&gt; params = 4;</code>
   */

  java.lang.String getParamsOrThrow(
      java.lang.String key);
}