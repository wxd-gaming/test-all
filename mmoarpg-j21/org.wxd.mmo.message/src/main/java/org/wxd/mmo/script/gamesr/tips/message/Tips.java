// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Tips.proto

package org.wxd.mmo.script.gamesr.tips.message;

public final class Tips {
  private Tips() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Proto_Tips_ResTips_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Proto_Tips_ResTips_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nTips.proto\022\nProto.Tips\"G\n\007ResTips\022\014\n\004t" +
      "ype\030\001 \001(\021\022\017\n\007lanCode\030\002 \001(\t\022\016\n\006params\030\003 \003" +
      "(\t\022\r\n\005resId\030\004 \001(\021*4\n\010TipsType\022\n\n\006normal\020" +
      "\000\022\007\n\003top\020\001\022\010\n\004chat\020\002\022\t\n\005error\020\003B*\n&org.w" +
      "xd.mmo.script.gamesr.tips.messageP\001b\006pro" +
      "to3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Proto_Tips_ResTips_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Proto_Tips_ResTips_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Proto_Tips_ResTips_descriptor,
        new java.lang.String[] { "Type", "LanCode", "Params", "ResId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
