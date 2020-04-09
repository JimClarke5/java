// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/transport_options.proto

package org.tensorflow.proto.distruntime;

public final class TransportOptions {
  private TransportOptions() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface RecvBufRespExtraOrBuilder extends
      // @@protoc_insertion_point(interface_extends:tensorflow.RecvBufRespExtra)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated bytes tensor_content = 1;</code>
     */
    java.util.List<com.google.protobuf.ByteString> getTensorContentList();
    /**
     * <code>repeated bytes tensor_content = 1;</code>
     */
    int getTensorContentCount();
    /**
     * <code>repeated bytes tensor_content = 1;</code>
     */
    com.google.protobuf.ByteString getTensorContent(int index);
  }
  /**
   * <pre>
   * Extra data needed on a non-RDMA RecvBufResponse.
   * </pre>
   *
   * Protobuf type {@code tensorflow.RecvBufRespExtra}
   */
  public  static final class RecvBufRespExtra extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:tensorflow.RecvBufRespExtra)
      RecvBufRespExtraOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use RecvBufRespExtra.newBuilder() to construct.
    private RecvBufRespExtra(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private RecvBufRespExtra() {
      tensorContent_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new RecvBufRespExtra();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private RecvBufRespExtra(
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
              if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                tensorContent_ = new java.util.ArrayList<com.google.protobuf.ByteString>();
                mutable_bitField0_ |= 0x00000001;
              }
              tensorContent_.add(input.readBytes());
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
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000001) != 0)) {
          tensorContent_ = java.util.Collections.unmodifiableList(tensorContent_); // C
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.proto.distruntime.TransportOptions.internal_static_tensorflow_RecvBufRespExtra_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.proto.distruntime.TransportOptions.internal_static_tensorflow_RecvBufRespExtra_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra.class, org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra.Builder.class);
    }

    public static final int TENSOR_CONTENT_FIELD_NUMBER = 1;
    private java.util.List<com.google.protobuf.ByteString> tensorContent_;
    /**
     * <code>repeated bytes tensor_content = 1;</code>
     */
    public java.util.List<com.google.protobuf.ByteString>
        getTensorContentList() {
      return tensorContent_;
    }
    /**
     * <code>repeated bytes tensor_content = 1;</code>
     */
    public int getTensorContentCount() {
      return tensorContent_.size();
    }
    /**
     * <code>repeated bytes tensor_content = 1;</code>
     */
    public com.google.protobuf.ByteString getTensorContent(int index) {
      return tensorContent_.get(index);
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
      for (int i = 0; i < tensorContent_.size(); i++) {
        output.writeBytes(1, tensorContent_.get(i));
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      {
        int dataSize = 0;
        for (int i = 0; i < tensorContent_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(tensorContent_.get(i));
        }
        size += dataSize;
        size += 1 * getTensorContentList().size();
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
      if (!(obj instanceof org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra)) {
        return super.equals(obj);
      }
      org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra other = (org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra) obj;

      if (!getTensorContentList()
          .equals(other.getTensorContentList())) return false;
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
      if (getTensorContentCount() > 0) {
        hash = (37 * hash) + TENSOR_CONTENT_FIELD_NUMBER;
        hash = (53 * hash) + getTensorContentList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parseFrom(
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
    public static Builder newBuilder(org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra prototype) {
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
     * Extra data needed on a non-RDMA RecvBufResponse.
     * </pre>
     *
     * Protobuf type {@code tensorflow.RecvBufRespExtra}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:tensorflow.RecvBufRespExtra)
        org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtraOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.tensorflow.proto.distruntime.TransportOptions.internal_static_tensorflow_RecvBufRespExtra_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.tensorflow.proto.distruntime.TransportOptions.internal_static_tensorflow_RecvBufRespExtra_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra.class, org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra.Builder.class);
      }

      // Construct using org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra.newBuilder()
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
        tensorContent_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.tensorflow.proto.distruntime.TransportOptions.internal_static_tensorflow_RecvBufRespExtra_descriptor;
      }

      @java.lang.Override
      public org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra getDefaultInstanceForType() {
        return org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra.getDefaultInstance();
      }

      @java.lang.Override
      public org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra build() {
        org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra buildPartial() {
        org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra result = new org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra(this);
        int from_bitField0_ = bitField0_;
        if (((bitField0_ & 0x00000001) != 0)) {
          tensorContent_ = java.util.Collections.unmodifiableList(tensorContent_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.tensorContent_ = tensorContent_;
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
        if (other instanceof org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra) {
          return mergeFrom((org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra other) {
        if (other == org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra.getDefaultInstance()) return this;
        if (!other.tensorContent_.isEmpty()) {
          if (tensorContent_.isEmpty()) {
            tensorContent_ = other.tensorContent_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureTensorContentIsMutable();
            tensorContent_.addAll(other.tensorContent_);
          }
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
        org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.util.List<com.google.protobuf.ByteString> tensorContent_ = java.util.Collections.emptyList();
      private void ensureTensorContentIsMutable() {
        if (!((bitField0_ & 0x00000001) != 0)) {
          tensorContent_ = new java.util.ArrayList<com.google.protobuf.ByteString>(tensorContent_);
          bitField0_ |= 0x00000001;
         }
      }
      /**
       * <code>repeated bytes tensor_content = 1;</code>
       */
      public java.util.List<com.google.protobuf.ByteString>
          getTensorContentList() {
        return ((bitField0_ & 0x00000001) != 0) ?
                 java.util.Collections.unmodifiableList(tensorContent_) : tensorContent_;
      }
      /**
       * <code>repeated bytes tensor_content = 1;</code>
       */
      public int getTensorContentCount() {
        return tensorContent_.size();
      }
      /**
       * <code>repeated bytes tensor_content = 1;</code>
       */
      public com.google.protobuf.ByteString getTensorContent(int index) {
        return tensorContent_.get(index);
      }
      /**
       * <code>repeated bytes tensor_content = 1;</code>
       */
      public Builder setTensorContent(
          int index, com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureTensorContentIsMutable();
        tensorContent_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated bytes tensor_content = 1;</code>
       */
      public Builder addTensorContent(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureTensorContentIsMutable();
        tensorContent_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated bytes tensor_content = 1;</code>
       */
      public Builder addAllTensorContent(
          java.lang.Iterable<? extends com.google.protobuf.ByteString> values) {
        ensureTensorContentIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, tensorContent_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated bytes tensor_content = 1;</code>
       */
      public Builder clearTensorContent() {
        tensorContent_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
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


      // @@protoc_insertion_point(builder_scope:tensorflow.RecvBufRespExtra)
    }

    // @@protoc_insertion_point(class_scope:tensorflow.RecvBufRespExtra)
    private static final org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra();
    }

    public static org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<RecvBufRespExtra>
        PARSER = new com.google.protobuf.AbstractParser<RecvBufRespExtra>() {
      @java.lang.Override
      public RecvBufRespExtra parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RecvBufRespExtra(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<RecvBufRespExtra> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<RecvBufRespExtra> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public org.tensorflow.proto.distruntime.TransportOptions.RecvBufRespExtra getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_RecvBufRespExtra_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_RecvBufRespExtra_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n0tensorflow/core/protobuf/transport_opt" +
      "ions.proto\022\ntensorflow\"*\n\020RecvBufRespExt" +
      "ra\022\026\n\016tensor_content\030\001 \003(\014Bl\n org.tensor" +
      "flow.proto.distruntimeZHgithub.com/tenso" +
      "rflow/tensorflow/tensorflow/go/core/core" +
      "_protos_go_protob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_tensorflow_RecvBufRespExtra_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tensorflow_RecvBufRespExtra_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_RecvBufRespExtra_descriptor,
        new java.lang.String[] { "TensorContent", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}