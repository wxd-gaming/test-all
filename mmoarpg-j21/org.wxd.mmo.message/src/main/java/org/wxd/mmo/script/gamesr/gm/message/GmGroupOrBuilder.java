// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Gm.proto

package org.wxd.mmo.script.gamesr.gm.message;

public interface GmGroupOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Proto.Gm.GmGroup)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *分组
   * </pre>
   *
   * <code>string group = 1;</code>
   * @return The group.
   */
  java.lang.String getGroup();
  /**
   * <pre>
   *分组
   * </pre>
   *
   * <code>string group = 1;</code>
   * @return The bytes for group.
   */
  com.google.protobuf.ByteString
      getGroupBytes();

  /**
   * <pre>
   *分组下所以命令
   * </pre>
   *
   * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
   */
  java.util.List<org.wxd.mmo.script.gamesr.gm.message.GmBean> 
      getGmsList();
  /**
   * <pre>
   *分组下所以命令
   * </pre>
   *
   * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
   */
  org.wxd.mmo.script.gamesr.gm.message.GmBean getGms(int index);
  /**
   * <pre>
   *分组下所以命令
   * </pre>
   *
   * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
   */
  int getGmsCount();
  /**
   * <pre>
   *分组下所以命令
   * </pre>
   *
   * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
   */
  java.util.List<? extends org.wxd.mmo.script.gamesr.gm.message.GmBeanOrBuilder> 
      getGmsOrBuilderList();
  /**
   * <pre>
   *分组下所以命令
   * </pre>
   *
   * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
   */
  org.wxd.mmo.script.gamesr.gm.message.GmBeanOrBuilder getGmsOrBuilder(
      int index);
}
