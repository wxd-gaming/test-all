// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Chat.proto

package wxdgaming.mmo.script.gamesr.chat.message;

public final class Chat {
  private Chat() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Proto_Chat_ReqChat_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Proto_Chat_ReqChat_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Proto_Chat_ResChat_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Proto_Chat_ResChat_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nChat.proto\022\nProto.Chat\":\n\007ReqChat\022\"\n\004t" +
      "ype\030\001 \001(\0162\024.Proto.Chat.ChatType\022\013\n\003msg\030\002" +
      " \001(\t\"]\n\007ResChat\022\"\n\004type\030\001 \001(\0162\024.Proto.Ch" +
      "at.ChatType\022\013\n\003msg\030\002 \001(\t\022\017\n\007sendUid\030\003 \001(" +
      "\003\022\020\n\010sendName\030\004 \001(\t*\026\n\010ChatType\022\n\n\006norma" +
      "l\020\000B,\n(wxdgaming.mmo.script.gamesr.chat." +
      "messageP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Proto_Chat_ReqChat_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Proto_Chat_ReqChat_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Proto_Chat_ReqChat_descriptor,
        new java.lang.String[] { "Type", "Msg", });
    internal_static_Proto_Chat_ResChat_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Proto_Chat_ResChat_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Proto_Chat_ResChat_descriptor,
        new java.lang.String[] { "Type", "Msg", "SendUid", "SendName", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}