// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Chat.proto

package org.wxd.mmo.script.gamesr.chat.message;

/**
 * <pre>
 *聊天消息转发
 * </pre>
 *
 * Protobuf type {@code Proto.Chat.ResChat}
 */
public final class ResChat extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Proto.Chat.ResChat)
    ResChatOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ResChat.newBuilder() to construct.
  private ResChat(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ResChat() {
    type_ = 0;
    msg_ = "";
    sendName_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ResChat();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ResChat(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {
            int rawValue = input.readEnum();

            type_ = rawValue;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            msg_ = s;
            break;
          }
          case 24: {

            sendUid_ = input.readInt64();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            sendName_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (com.google.protobuf.UninitializedMessageException e) {
      throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.wxd.mmo.script.gamesr.chat.message.Chat.internal_static_Proto_Chat_ResChat_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.wxd.mmo.script.gamesr.chat.message.Chat.internal_static_Proto_Chat_ResChat_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.wxd.mmo.script.gamesr.chat.message.ResChat.class, org.wxd.mmo.script.gamesr.chat.message.ResChat.Builder.class);
  }

  public static final int TYPE_FIELD_NUMBER = 1;
  private int type_;
  /**
   * <pre>
   *聊天类型
   * </pre>
   *
   * <code>.Proto.Chat.ChatType type = 1;</code>
   * @return The enum numeric value on the wire for type.
   */
  @java.lang.Override public int getTypeValue() {
    return type_;
  }
  /**
   * <pre>
   *聊天类型
   * </pre>
   *
   * <code>.Proto.Chat.ChatType type = 1;</code>
   * @return The type.
   */
  @java.lang.Override public org.wxd.mmo.script.gamesr.chat.message.ChatType getType() {
    @SuppressWarnings("deprecation")
    org.wxd.mmo.script.gamesr.chat.message.ChatType result = org.wxd.mmo.script.gamesr.chat.message.ChatType.valueOf(type_);
    return result == null ? org.wxd.mmo.script.gamesr.chat.message.ChatType.UNRECOGNIZED : result;
  }

  public static final int MSG_FIELD_NUMBER = 2;
  private volatile java.lang.Object msg_;
  /**
   * <pre>
   *消息内容
   * </pre>
   *
   * <code>string msg = 2;</code>
   * @return The msg.
   */
  @java.lang.Override
  public java.lang.String getMsg() {
    java.lang.Object ref = msg_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      msg_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *消息内容
   * </pre>
   *
   * <code>string msg = 2;</code>
   * @return The bytes for msg.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getMsgBytes() {
    java.lang.Object ref = msg_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      msg_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SENDUID_FIELD_NUMBER = 3;
  private long sendUid_;
  /**
   * <pre>
   *发送者id；
   * </pre>
   *
   * <code>int64 sendUid = 3;</code>
   * @return The sendUid.
   */
  @java.lang.Override
  public long getSendUid() {
    return sendUid_;
  }

  public static final int SENDNAME_FIELD_NUMBER = 4;
  private volatile java.lang.Object sendName_;
  /**
   * <pre>
   *发送者名字
   * </pre>
   *
   * <code>string sendName = 4;</code>
   * @return The sendName.
   */
  @java.lang.Override
  public java.lang.String getSendName() {
    java.lang.Object ref = sendName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      sendName_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *发送者名字
   * </pre>
   *
   * <code>string sendName = 4;</code>
   * @return The bytes for sendName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getSendNameBytes() {
    java.lang.Object ref = sendName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      sendName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (type_ != org.wxd.mmo.script.gamesr.chat.message.ChatType.normal.getNumber()) {
      output.writeEnum(1, type_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(msg_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, msg_);
    }
    if (sendUid_ != 0L) {
      output.writeInt64(3, sendUid_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(sendName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, sendName_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (type_ != org.wxd.mmo.script.gamesr.chat.message.ChatType.normal.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, type_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(msg_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, msg_);
    }
    if (sendUid_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, sendUid_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(sendName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, sendName_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.wxd.mmo.script.gamesr.chat.message.ResChat)) {
      return super.equals(obj);
    }
    org.wxd.mmo.script.gamesr.chat.message.ResChat other = (org.wxd.mmo.script.gamesr.chat.message.ResChat) obj;

    if (type_ != other.type_) return false;
    if (!getMsg()
        .equals(other.getMsg())) return false;
    if (getSendUid()
        != other.getSendUid()) return false;
    if (!getSendName()
        .equals(other.getSendName())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + type_;
    hash = (37 * hash) + MSG_FIELD_NUMBER;
    hash = (53 * hash) + getMsg().hashCode();
    hash = (37 * hash) + SENDUID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getSendUid());
    hash = (37 * hash) + SENDNAME_FIELD_NUMBER;
    hash = (53 * hash) + getSendName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.wxd.mmo.script.gamesr.chat.message.ResChat parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.wxd.mmo.script.gamesr.chat.message.ResChat prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   *聊天消息转发
   * </pre>
   *
   * Protobuf type {@code Proto.Chat.ResChat}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Proto.Chat.ResChat)
      org.wxd.mmo.script.gamesr.chat.message.ResChatOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.wxd.mmo.script.gamesr.chat.message.Chat.internal_static_Proto_Chat_ResChat_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.wxd.mmo.script.gamesr.chat.message.Chat.internal_static_Proto_Chat_ResChat_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.wxd.mmo.script.gamesr.chat.message.ResChat.class, org.wxd.mmo.script.gamesr.chat.message.ResChat.Builder.class);
    }

    // Construct using org.wxd.mmo.script.gamesr.chat.message.ResChat.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      type_ = 0;

      msg_ = "";

      sendUid_ = 0L;

      sendName_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.wxd.mmo.script.gamesr.chat.message.Chat.internal_static_Proto_Chat_ResChat_descriptor;
    }

    @java.lang.Override
    public org.wxd.mmo.script.gamesr.chat.message.ResChat getDefaultInstanceForType() {
      return org.wxd.mmo.script.gamesr.chat.message.ResChat.getDefaultInstance();
    }

    @java.lang.Override
    public org.wxd.mmo.script.gamesr.chat.message.ResChat build() {
      org.wxd.mmo.script.gamesr.chat.message.ResChat result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.wxd.mmo.script.gamesr.chat.message.ResChat buildPartial() {
      org.wxd.mmo.script.gamesr.chat.message.ResChat result = new org.wxd.mmo.script.gamesr.chat.message.ResChat(this);
      result.type_ = type_;
      result.msg_ = msg_;
      result.sendUid_ = sendUid_;
      result.sendName_ = sendName_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.wxd.mmo.script.gamesr.chat.message.ResChat) {
        return mergeFrom((org.wxd.mmo.script.gamesr.chat.message.ResChat)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.wxd.mmo.script.gamesr.chat.message.ResChat other) {
      if (other == org.wxd.mmo.script.gamesr.chat.message.ResChat.getDefaultInstance()) return this;
      if (other.type_ != 0) {
        setTypeValue(other.getTypeValue());
      }
      if (!other.getMsg().isEmpty()) {
        msg_ = other.msg_;
        onChanged();
      }
      if (other.getSendUid() != 0L) {
        setSendUid(other.getSendUid());
      }
      if (!other.getSendName().isEmpty()) {
        sendName_ = other.sendName_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.wxd.mmo.script.gamesr.chat.message.ResChat parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.wxd.mmo.script.gamesr.chat.message.ResChat) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int type_ = 0;
    /**
     * <pre>
     *聊天类型
     * </pre>
     *
     * <code>.Proto.Chat.ChatType type = 1;</code>
     * @return The enum numeric value on the wire for type.
     */
    @java.lang.Override public int getTypeValue() {
      return type_;
    }
    /**
     * <pre>
     *聊天类型
     * </pre>
     *
     * <code>.Proto.Chat.ChatType type = 1;</code>
     * @param value The enum numeric value on the wire for type to set.
     * @return This builder for chaining.
     */
    public Builder setTypeValue(int value) {
      
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *聊天类型
     * </pre>
     *
     * <code>.Proto.Chat.ChatType type = 1;</code>
     * @return The type.
     */
    @java.lang.Override
    public org.wxd.mmo.script.gamesr.chat.message.ChatType getType() {
      @SuppressWarnings("deprecation")
      org.wxd.mmo.script.gamesr.chat.message.ChatType result = org.wxd.mmo.script.gamesr.chat.message.ChatType.valueOf(type_);
      return result == null ? org.wxd.mmo.script.gamesr.chat.message.ChatType.UNRECOGNIZED : result;
    }
    /**
     * <pre>
     *聊天类型
     * </pre>
     *
     * <code>.Proto.Chat.ChatType type = 1;</code>
     * @param value The type to set.
     * @return This builder for chaining.
     */
    public Builder setType(org.wxd.mmo.script.gamesr.chat.message.ChatType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      type_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *聊天类型
     * </pre>
     *
     * <code>.Proto.Chat.ChatType type = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearType() {
      
      type_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object msg_ = "";
    /**
     * <pre>
     *消息内容
     * </pre>
     *
     * <code>string msg = 2;</code>
     * @return The msg.
     */
    public java.lang.String getMsg() {
      java.lang.Object ref = msg_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        msg_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *消息内容
     * </pre>
     *
     * <code>string msg = 2;</code>
     * @return The bytes for msg.
     */
    public com.google.protobuf.ByteString
        getMsgBytes() {
      java.lang.Object ref = msg_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        msg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *消息内容
     * </pre>
     *
     * <code>string msg = 2;</code>
     * @param value The msg to set.
     * @return This builder for chaining.
     */
    public Builder setMsg(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      msg_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *消息内容
     * </pre>
     *
     * <code>string msg = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearMsg() {
      
      msg_ = getDefaultInstance().getMsg();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *消息内容
     * </pre>
     *
     * <code>string msg = 2;</code>
     * @param value The bytes for msg to set.
     * @return This builder for chaining.
     */
    public Builder setMsgBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      msg_ = value;
      onChanged();
      return this;
    }

    private long sendUid_ ;
    /**
     * <pre>
     *发送者id；
     * </pre>
     *
     * <code>int64 sendUid = 3;</code>
     * @return The sendUid.
     */
    @java.lang.Override
    public long getSendUid() {
      return sendUid_;
    }
    /**
     * <pre>
     *发送者id；
     * </pre>
     *
     * <code>int64 sendUid = 3;</code>
     * @param value The sendUid to set.
     * @return This builder for chaining.
     */
    public Builder setSendUid(long value) {
      
      sendUid_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送者id；
     * </pre>
     *
     * <code>int64 sendUid = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearSendUid() {
      
      sendUid_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object sendName_ = "";
    /**
     * <pre>
     *发送者名字
     * </pre>
     *
     * <code>string sendName = 4;</code>
     * @return The sendName.
     */
    public java.lang.String getSendName() {
      java.lang.Object ref = sendName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        sendName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *发送者名字
     * </pre>
     *
     * <code>string sendName = 4;</code>
     * @return The bytes for sendName.
     */
    public com.google.protobuf.ByteString
        getSendNameBytes() {
      java.lang.Object ref = sendName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        sendName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *发送者名字
     * </pre>
     *
     * <code>string sendName = 4;</code>
     * @param value The sendName to set.
     * @return This builder for chaining.
     */
    public Builder setSendName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      sendName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送者名字
     * </pre>
     *
     * <code>string sendName = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearSendName() {
      
      sendName_ = getDefaultInstance().getSendName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送者名字
     * </pre>
     *
     * <code>string sendName = 4;</code>
     * @param value The bytes for sendName to set.
     * @return This builder for chaining.
     */
    public Builder setSendNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      sendName_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:Proto.Chat.ResChat)
  }

  // @@protoc_insertion_point(class_scope:Proto.Chat.ResChat)
  private static final org.wxd.mmo.script.gamesr.chat.message.ResChat DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.wxd.mmo.script.gamesr.chat.message.ResChat();
  }

  public static org.wxd.mmo.script.gamesr.chat.message.ResChat getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ResChat>
      PARSER = new com.google.protobuf.AbstractParser<ResChat>() {
    @java.lang.Override
    public ResChat parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ResChat(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ResChat> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ResChat> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.wxd.mmo.script.gamesr.chat.message.ResChat getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

