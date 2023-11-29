// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Gm.proto

package org.wxd.mmo.script.gamesr.gm.message;

/**
 * Protobuf type {@code Proto.Gm.GmGroup}
 */
public final class GmGroup extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Proto.Gm.GmGroup)
    GmGroupOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GmGroup.newBuilder() to construct.
  private GmGroup(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GmGroup() {
    group_ = "";
    gms_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GmGroup();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GmGroup(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            group_ = s;
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              gms_ = new java.util.ArrayList<org.wxd.mmo.script.gamesr.gm.message.GmBean>();
              mutable_bitField0_ |= 0x00000001;
            }
            gms_.add(
                input.readMessage(org.wxd.mmo.script.gamesr.gm.message.GmBean.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        gms_ = java.util.Collections.unmodifiableList(gms_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.wxd.mmo.script.gamesr.gm.message.Gm.internal_static_Proto_Gm_GmGroup_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.wxd.mmo.script.gamesr.gm.message.Gm.internal_static_Proto_Gm_GmGroup_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.wxd.mmo.script.gamesr.gm.message.GmGroup.class, org.wxd.mmo.script.gamesr.gm.message.GmGroup.Builder.class);
  }

  public static final int GROUP_FIELD_NUMBER = 1;
  private volatile java.lang.Object group_;
  /**
   * <pre>
   *分组
   * </pre>
   *
   * <code>string group = 1;</code>
   * @return The group.
   */
  @java.lang.Override
  public java.lang.String getGroup() {
    java.lang.Object ref = group_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      group_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *分组
   * </pre>
   *
   * <code>string group = 1;</code>
   * @return The bytes for group.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getGroupBytes() {
    java.lang.Object ref = group_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      group_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int GMS_FIELD_NUMBER = 2;
  private java.util.List<org.wxd.mmo.script.gamesr.gm.message.GmBean> gms_;
  /**
   * <pre>
   *分组下所以命令
   * </pre>
   *
   * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
   */
  @java.lang.Override
  public java.util.List<org.wxd.mmo.script.gamesr.gm.message.GmBean> getGmsList() {
    return gms_;
  }
  /**
   * <pre>
   *分组下所以命令
   * </pre>
   *
   * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends org.wxd.mmo.script.gamesr.gm.message.GmBeanOrBuilder> 
      getGmsOrBuilderList() {
    return gms_;
  }
  /**
   * <pre>
   *分组下所以命令
   * </pre>
   *
   * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
   */
  @java.lang.Override
  public int getGmsCount() {
    return gms_.size();
  }
  /**
   * <pre>
   *分组下所以命令
   * </pre>
   *
   * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
   */
  @java.lang.Override
  public org.wxd.mmo.script.gamesr.gm.message.GmBean getGms(int index) {
    return gms_.get(index);
  }
  /**
   * <pre>
   *分组下所以命令
   * </pre>
   *
   * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
   */
  @java.lang.Override
  public org.wxd.mmo.script.gamesr.gm.message.GmBeanOrBuilder getGmsOrBuilder(
      int index) {
    return gms_.get(index);
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(group_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, group_);
    }
    for (int i = 0; i < gms_.size(); i++) {
      output.writeMessage(2, gms_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(group_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, group_);
    }
    for (int i = 0; i < gms_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, gms_.get(i));
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
    if (!(obj instanceof org.wxd.mmo.script.gamesr.gm.message.GmGroup)) {
      return super.equals(obj);
    }
    org.wxd.mmo.script.gamesr.gm.message.GmGroup other = (org.wxd.mmo.script.gamesr.gm.message.GmGroup) obj;

    if (!getGroup()
        .equals(other.getGroup())) return false;
    if (!getGmsList()
        .equals(other.getGmsList())) return false;
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
    hash = (37 * hash) + GROUP_FIELD_NUMBER;
    hash = (53 * hash) + getGroup().hashCode();
    if (getGmsCount() > 0) {
      hash = (37 * hash) + GMS_FIELD_NUMBER;
      hash = (53 * hash) + getGmsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup parseFrom(
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
  public static Builder newBuilder(org.wxd.mmo.script.gamesr.gm.message.GmGroup prototype) {
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
   * Protobuf type {@code Proto.Gm.GmGroup}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Proto.Gm.GmGroup)
      org.wxd.mmo.script.gamesr.gm.message.GmGroupOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.wxd.mmo.script.gamesr.gm.message.Gm.internal_static_Proto_Gm_GmGroup_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.wxd.mmo.script.gamesr.gm.message.Gm.internal_static_Proto_Gm_GmGroup_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.wxd.mmo.script.gamesr.gm.message.GmGroup.class, org.wxd.mmo.script.gamesr.gm.message.GmGroup.Builder.class);
    }

    // Construct using org.wxd.mmo.script.gamesr.gm.message.GmGroup.newBuilder()
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
        getGmsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      group_ = "";

      if (gmsBuilder_ == null) {
        gms_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        gmsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.wxd.mmo.script.gamesr.gm.message.Gm.internal_static_Proto_Gm_GmGroup_descriptor;
    }

    @java.lang.Override
    public org.wxd.mmo.script.gamesr.gm.message.GmGroup getDefaultInstanceForType() {
      return org.wxd.mmo.script.gamesr.gm.message.GmGroup.getDefaultInstance();
    }

    @java.lang.Override
    public org.wxd.mmo.script.gamesr.gm.message.GmGroup build() {
      org.wxd.mmo.script.gamesr.gm.message.GmGroup result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.wxd.mmo.script.gamesr.gm.message.GmGroup buildPartial() {
      org.wxd.mmo.script.gamesr.gm.message.GmGroup result = new org.wxd.mmo.script.gamesr.gm.message.GmGroup(this);
      int from_bitField0_ = bitField0_;
      result.group_ = group_;
      if (gmsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          gms_ = java.util.Collections.unmodifiableList(gms_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.gms_ = gms_;
      } else {
        result.gms_ = gmsBuilder_.build();
      }
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
      if (other instanceof org.wxd.mmo.script.gamesr.gm.message.GmGroup) {
        return mergeFrom((org.wxd.mmo.script.gamesr.gm.message.GmGroup)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.wxd.mmo.script.gamesr.gm.message.GmGroup other) {
      if (other == org.wxd.mmo.script.gamesr.gm.message.GmGroup.getDefaultInstance()) return this;
      if (!other.getGroup().isEmpty()) {
        group_ = other.group_;
        onChanged();
      }
      if (gmsBuilder_ == null) {
        if (!other.gms_.isEmpty()) {
          if (gms_.isEmpty()) {
            gms_ = other.gms_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureGmsIsMutable();
            gms_.addAll(other.gms_);
          }
          onChanged();
        }
      } else {
        if (!other.gms_.isEmpty()) {
          if (gmsBuilder_.isEmpty()) {
            gmsBuilder_.dispose();
            gmsBuilder_ = null;
            gms_ = other.gms_;
            bitField0_ = (bitField0_ & ~0x00000001);
            gmsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getGmsFieldBuilder() : null;
          } else {
            gmsBuilder_.addAllMessages(other.gms_);
          }
        }
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
      org.wxd.mmo.script.gamesr.gm.message.GmGroup parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.wxd.mmo.script.gamesr.gm.message.GmGroup) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object group_ = "";
    /**
     * <pre>
     *分组
     * </pre>
     *
     * <code>string group = 1;</code>
     * @return The group.
     */
    public java.lang.String getGroup() {
      java.lang.Object ref = group_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        group_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *分组
     * </pre>
     *
     * <code>string group = 1;</code>
     * @return The bytes for group.
     */
    public com.google.protobuf.ByteString
        getGroupBytes() {
      java.lang.Object ref = group_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        group_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *分组
     * </pre>
     *
     * <code>string group = 1;</code>
     * @param value The group to set.
     * @return This builder for chaining.
     */
    public Builder setGroup(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      group_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *分组
     * </pre>
     *
     * <code>string group = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearGroup() {
      
      group_ = getDefaultInstance().getGroup();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *分组
     * </pre>
     *
     * <code>string group = 1;</code>
     * @param value The bytes for group to set.
     * @return This builder for chaining.
     */
    public Builder setGroupBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      group_ = value;
      onChanged();
      return this;
    }

    private java.util.List<org.wxd.mmo.script.gamesr.gm.message.GmBean> gms_ =
      java.util.Collections.emptyList();
    private void ensureGmsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        gms_ = new java.util.ArrayList<org.wxd.mmo.script.gamesr.gm.message.GmBean>(gms_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        org.wxd.mmo.script.gamesr.gm.message.GmBean, org.wxd.mmo.script.gamesr.gm.message.GmBean.Builder, org.wxd.mmo.script.gamesr.gm.message.GmBeanOrBuilder> gmsBuilder_;

    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public java.util.List<org.wxd.mmo.script.gamesr.gm.message.GmBean> getGmsList() {
      if (gmsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(gms_);
      } else {
        return gmsBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public int getGmsCount() {
      if (gmsBuilder_ == null) {
        return gms_.size();
      } else {
        return gmsBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public org.wxd.mmo.script.gamesr.gm.message.GmBean getGms(int index) {
      if (gmsBuilder_ == null) {
        return gms_.get(index);
      } else {
        return gmsBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public Builder setGms(
        int index, org.wxd.mmo.script.gamesr.gm.message.GmBean value) {
      if (gmsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGmsIsMutable();
        gms_.set(index, value);
        onChanged();
      } else {
        gmsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public Builder setGms(
        int index, org.wxd.mmo.script.gamesr.gm.message.GmBean.Builder builderForValue) {
      if (gmsBuilder_ == null) {
        ensureGmsIsMutable();
        gms_.set(index, builderForValue.build());
        onChanged();
      } else {
        gmsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public Builder addGms(org.wxd.mmo.script.gamesr.gm.message.GmBean value) {
      if (gmsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGmsIsMutable();
        gms_.add(value);
        onChanged();
      } else {
        gmsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public Builder addGms(
        int index, org.wxd.mmo.script.gamesr.gm.message.GmBean value) {
      if (gmsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGmsIsMutable();
        gms_.add(index, value);
        onChanged();
      } else {
        gmsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public Builder addGms(
        org.wxd.mmo.script.gamesr.gm.message.GmBean.Builder builderForValue) {
      if (gmsBuilder_ == null) {
        ensureGmsIsMutable();
        gms_.add(builderForValue.build());
        onChanged();
      } else {
        gmsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public Builder addGms(
        int index, org.wxd.mmo.script.gamesr.gm.message.GmBean.Builder builderForValue) {
      if (gmsBuilder_ == null) {
        ensureGmsIsMutable();
        gms_.add(index, builderForValue.build());
        onChanged();
      } else {
        gmsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public Builder addAllGms(
        java.lang.Iterable<? extends org.wxd.mmo.script.gamesr.gm.message.GmBean> values) {
      if (gmsBuilder_ == null) {
        ensureGmsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, gms_);
        onChanged();
      } else {
        gmsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public Builder clearGms() {
      if (gmsBuilder_ == null) {
        gms_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        gmsBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public Builder removeGms(int index) {
      if (gmsBuilder_ == null) {
        ensureGmsIsMutable();
        gms_.remove(index);
        onChanged();
      } else {
        gmsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public org.wxd.mmo.script.gamesr.gm.message.GmBean.Builder getGmsBuilder(
        int index) {
      return getGmsFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public org.wxd.mmo.script.gamesr.gm.message.GmBeanOrBuilder getGmsOrBuilder(
        int index) {
      if (gmsBuilder_ == null) {
        return gms_.get(index);  } else {
        return gmsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public java.util.List<? extends org.wxd.mmo.script.gamesr.gm.message.GmBeanOrBuilder> 
         getGmsOrBuilderList() {
      if (gmsBuilder_ != null) {
        return gmsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(gms_);
      }
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public org.wxd.mmo.script.gamesr.gm.message.GmBean.Builder addGmsBuilder() {
      return getGmsFieldBuilder().addBuilder(
          org.wxd.mmo.script.gamesr.gm.message.GmBean.getDefaultInstance());
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public org.wxd.mmo.script.gamesr.gm.message.GmBean.Builder addGmsBuilder(
        int index) {
      return getGmsFieldBuilder().addBuilder(
          index, org.wxd.mmo.script.gamesr.gm.message.GmBean.getDefaultInstance());
    }
    /**
     * <pre>
     *分组下所以命令
     * </pre>
     *
     * <code>repeated .Proto.Gm.GmBean gms = 2;</code>
     */
    public java.util.List<org.wxd.mmo.script.gamesr.gm.message.GmBean.Builder> 
         getGmsBuilderList() {
      return getGmsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        org.wxd.mmo.script.gamesr.gm.message.GmBean, org.wxd.mmo.script.gamesr.gm.message.GmBean.Builder, org.wxd.mmo.script.gamesr.gm.message.GmBeanOrBuilder> 
        getGmsFieldBuilder() {
      if (gmsBuilder_ == null) {
        gmsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            org.wxd.mmo.script.gamesr.gm.message.GmBean, org.wxd.mmo.script.gamesr.gm.message.GmBean.Builder, org.wxd.mmo.script.gamesr.gm.message.GmBeanOrBuilder>(
                gms_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        gms_ = null;
      }
      return gmsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:Proto.Gm.GmGroup)
  }

  // @@protoc_insertion_point(class_scope:Proto.Gm.GmGroup)
  private static final org.wxd.mmo.script.gamesr.gm.message.GmGroup DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.wxd.mmo.script.gamesr.gm.message.GmGroup();
  }

  public static org.wxd.mmo.script.gamesr.gm.message.GmGroup getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GmGroup>
      PARSER = new com.google.protobuf.AbstractParser<GmGroup>() {
    @java.lang.Override
    public GmGroup parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GmGroup(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GmGroup> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GmGroup> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.wxd.mmo.script.gamesr.gm.message.GmGroup getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

