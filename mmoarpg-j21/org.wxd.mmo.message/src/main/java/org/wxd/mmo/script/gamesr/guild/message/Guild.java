// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Guild.proto

package org.wxd.mmo.script.gamesr.guild.message;

public final class Guild {
  private Guild() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Proto_Login_ReqGuildList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Proto_Login_ReqGuildList_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Proto_Login_ResGuildList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Proto_Login_ResGuildList_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Proto_Login_ResGuildList_MoneysEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Proto_Login_ResGuildList_MoneysEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013Guild.proto\022\013Proto.Login\"\016\n\014ReqGuildLi" +
      "st\"\217\001\n\014ResGuildList\022\013\n\003uid\030\001 \001(\003\022\014\n\004name" +
      "\030\002 \001(\t\0225\n\006moneys\030\003 \003(\0132%.Proto.Login.Res" +
      "GuildList.MoneysEntry\032-\n\013MoneysEntry\022\013\n\003" +
      "key\030\001 \001(\005\022\r\n\005value\030\002 \001(\003:\0028\001B+\n\'org.wxd." +
      "mmo.script.gamesr.guild.messageP\001b\006proto" +
      "3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Proto_Login_ReqGuildList_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Proto_Login_ReqGuildList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Proto_Login_ReqGuildList_descriptor,
        new java.lang.String[] { });
    internal_static_Proto_Login_ResGuildList_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Proto_Login_ResGuildList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Proto_Login_ResGuildList_descriptor,
        new java.lang.String[] { "Uid", "Name", "Moneys", });
    internal_static_Proto_Login_ResGuildList_MoneysEntry_descriptor =
      internal_static_Proto_Login_ResGuildList_descriptor.getNestedTypes().get(0);
    internal_static_Proto_Login_ResGuildList_MoneysEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Proto_Login_ResGuildList_MoneysEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
