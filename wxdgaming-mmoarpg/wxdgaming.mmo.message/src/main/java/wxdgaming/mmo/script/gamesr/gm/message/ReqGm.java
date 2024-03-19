// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Gm.proto

package wxdgaming.mmo.script.gamesr.gm.message;

/**
 * <pre>
 *请求执行gm命令--没有回复
 * </pre>
 *
 * Protobuf type {@code Proto.Gm.ReqGm}
 */
public final class ReqGm extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Proto.Gm.ReqGm)
    ReqGmOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ReqGm.newBuilder() to construct.
  private ReqGm(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ReqGm() {
    name_ = "";
    params_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ReqGm();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ReqGm(
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            params_ = s;
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
    return wxdgaming.mmo.script.gamesr.gm.message.Gm.internal_static_Proto_Gm_ReqGm_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return wxdgaming.mmo.script.gamesr.gm.message.Gm.internal_static_Proto_Gm_ReqGm_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            wxdgaming.mmo.script.gamesr.gm.message.ReqGm.class, wxdgaming.mmo.script.gamesr.gm.message.ReqGm.Builder.class);
  }

  public static final int NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object name_;
  /**
   * <pre>
   *gm名称
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The name.
   */
  @java.lang.Override
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *gm名称
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PARAMS_FIELD_NUMBER = 2;
  private volatile java.lang.Object params_;
  /**
   * <pre>
   *参数
   * </pre>
   *
   * <code>string params = 2;</code>
   * @return The params.
   */
  @java.lang.Override
  public java.lang.String getParams() {
    java.lang.Object ref = params_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      params_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *参数
   * </pre>
   *
   * <code>string params = 2;</code>
   * @return The bytes for params.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getParamsBytes() {
    java.lang.Object ref = params_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      params_ = b;
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(params_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, params_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(params_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, params_);
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
    if (!(obj instanceof wxdgaming.mmo.script.gamesr.gm.message.ReqGm)) {
      return super.equals(obj);
    }
    wxdgaming.mmo.script.gamesr.gm.message.ReqGm other = (wxdgaming.mmo.script.gamesr.gm.message.ReqGm) obj;

    if (!getName()
        .equals(other.getName())) return false;
    if (!getParams()
        .equals(other.getParams())) return false;
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
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + PARAMS_FIELD_NUMBER;
    hash = (53 * hash) + getParams().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm parseFrom(
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
  public static Builder newBuilder(wxdgaming.mmo.script.gamesr.gm.message.ReqGm prototype) {
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
   *请求执行gm命令--没有回复
   * </pre>
   *
   * Protobuf type {@code Proto.Gm.ReqGm}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Proto.Gm.ReqGm)
      wxdgaming.mmo.script.gamesr.gm.message.ReqGmOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return wxdgaming.mmo.script.gamesr.gm.message.Gm.internal_static_Proto_Gm_ReqGm_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return wxdgaming.mmo.script.gamesr.gm.message.Gm.internal_static_Proto_Gm_ReqGm_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              wxdgaming.mmo.script.gamesr.gm.message.ReqGm.class, wxdgaming.mmo.script.gamesr.gm.message.ReqGm.Builder.class);
    }

    // Construct using wxdgaming.mmo.script.gamesr.gm.message.ReqGm.newBuilder()
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
      name_ = "";

      params_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return wxdgaming.mmo.script.gamesr.gm.message.Gm.internal_static_Proto_Gm_ReqGm_descriptor;
    }

    @java.lang.Override
    public wxdgaming.mmo.script.gamesr.gm.message.ReqGm getDefaultInstanceForType() {
      return wxdgaming.mmo.script.gamesr.gm.message.ReqGm.getDefaultInstance();
    }

    @java.lang.Override
    public wxdgaming.mmo.script.gamesr.gm.message.ReqGm build() {
      wxdgaming.mmo.script.gamesr.gm.message.ReqGm result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public wxdgaming.mmo.script.gamesr.gm.message.ReqGm buildPartial() {
      wxdgaming.mmo.script.gamesr.gm.message.ReqGm result = new wxdgaming.mmo.script.gamesr.gm.message.ReqGm(this);
      result.name_ = name_;
      result.params_ = params_;
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
      if (other instanceof wxdgaming.mmo.script.gamesr.gm.message.ReqGm) {
        return mergeFrom((wxdgaming.mmo.script.gamesr.gm.message.ReqGm)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(wxdgaming.mmo.script.gamesr.gm.message.ReqGm other) {
      if (other == wxdgaming.mmo.script.gamesr.gm.message.ReqGm.getDefaultInstance()) return this;
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (!other.getParams().isEmpty()) {
        params_ = other.params_;
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
      wxdgaming.mmo.script.gamesr.gm.message.ReqGm parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (wxdgaming.mmo.script.gamesr.gm.message.ReqGm) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <pre>
     *gm名称
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *gm名称
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *gm名称
     * </pre>
     *
     * <code>string name = 1;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *gm名称
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *gm名称
     * </pre>
     *
     * <code>string name = 1;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object params_ = "";
    /**
     * <pre>
     *参数
     * </pre>
     *
     * <code>string params = 2;</code>
     * @return The params.
     */
    public java.lang.String getParams() {
      java.lang.Object ref = params_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        params_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *参数
     * </pre>
     *
     * <code>string params = 2;</code>
     * @return The bytes for params.
     */
    public com.google.protobuf.ByteString
        getParamsBytes() {
      java.lang.Object ref = params_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        params_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *参数
     * </pre>
     *
     * <code>string params = 2;</code>
     * @param value The params to set.
     * @return This builder for chaining.
     */
    public Builder setParams(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      params_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *参数
     * </pre>
     *
     * <code>string params = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearParams() {
      
      params_ = getDefaultInstance().getParams();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *参数
     * </pre>
     *
     * <code>string params = 2;</code>
     * @param value The bytes for params to set.
     * @return This builder for chaining.
     */
    public Builder setParamsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      params_ = value;
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


    // @@protoc_insertion_point(builder_scope:Proto.Gm.ReqGm)
  }

  // @@protoc_insertion_point(class_scope:Proto.Gm.ReqGm)
  private static final wxdgaming.mmo.script.gamesr.gm.message.ReqGm DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new wxdgaming.mmo.script.gamesr.gm.message.ReqGm();
  }

  public static wxdgaming.mmo.script.gamesr.gm.message.ReqGm getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ReqGm>
      PARSER = new com.google.protobuf.AbstractParser<ReqGm>() {
    @java.lang.Override
    public ReqGm parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ReqGm(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ReqGm> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ReqGm> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public wxdgaming.mmo.script.gamesr.gm.message.ReqGm getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
