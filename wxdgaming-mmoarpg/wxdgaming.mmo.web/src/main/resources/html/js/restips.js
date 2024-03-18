// source: Tips.proto
/**
 * @fileoverview
 * @enhanceable
 * @suppress {missingRequire} reports error on implicit type usages.
 * @suppress {messageConventions} JS Compiler reports an error if a variable or
 *     field starts with 'MSG_' and isn't a translatable message.
 * @public
 */
// GENERATED CODE -- DO NOT EDIT!
/* eslint-disable */
// @ts-nocheck

goog.provide('proto.Proto.Tips.ResTips');

goog.require('jspb.BinaryReader');
goog.require('jspb.BinaryWriter');
goog.require('jspb.Message');

goog.forwardDeclare('proto.Proto.Tips.TipsType');
/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.Proto.Tips.ResTips = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, proto.Proto.Tips.ResTips.repeatedFields_, null);
};
goog.inherits(proto.Proto.Tips.ResTips, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  /**
   * @public
   * @override
   */
  proto.Proto.Tips.ResTips.displayName = 'proto.Proto.Tips.ResTips';
}

/**
 * List of repeated fields within this message type.
 * @private {!Array<number>}
 * @const
 */
proto.Proto.Tips.ResTips.repeatedFields_ = [3];



if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * Optional fields that are not set will be set to undefined.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     net/proto2/compiler/js/internal/generator.cc#kKeyword.
 * @param {boolean=} opt_includeInstance Deprecated. whether to include the
 *     JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @return {!Object}
 */
proto.Proto.Tips.ResTips.prototype.toObject = function(opt_includeInstance) {
  return proto.Proto.Tips.ResTips.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Deprecated. Whether to include
 *     the JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.Proto.Tips.ResTips} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.Proto.Tips.ResTips.toObject = function(includeInstance, msg) {
  var f, obj = {
    type: jspb.Message.getFieldWithDefault(msg, 1, 0),
    lancode: jspb.Message.getFieldWithDefault(msg, 2, ""),
    paramsList: (f = jspb.Message.getRepeatedField(msg, 3)) == null ? undefined : f,
    resid: jspb.Message.getFieldWithDefault(msg, 4, 0)
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.Proto.Tips.ResTips}
 */
proto.Proto.Tips.ResTips.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.Proto.Tips.ResTips;
  return proto.Proto.Tips.ResTips.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.Proto.Tips.ResTips} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.Proto.Tips.ResTips}
 */
proto.Proto.Tips.ResTips.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {!proto.Proto.Tips.TipsType} */ (reader.readEnum());
      msg.setType(value);
      break;
    case 2:
      var value = /** @type {string} */ (reader.readString());
      msg.setLancode(value);
      break;
    case 3:
      var value = /** @type {string} */ (reader.readString());
      msg.addParams(value);
      break;
    case 4:
      var value = /** @type {number} */ (reader.readSint32());
      msg.setResid(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.Proto.Tips.ResTips.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.Proto.Tips.ResTips.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.Proto.Tips.ResTips} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.Proto.Tips.ResTips.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = message.getType();
  if (f !== 0.0) {
    writer.writeEnum(
      1,
      f
    );
  }
  f = message.getLancode();
  if (f.length > 0) {
    writer.writeString(
      2,
      f
    );
  }
  f = message.getParamsList();
  if (f.length > 0) {
    writer.writeRepeatedString(
      3,
      f
    );
  }
  f = message.getResid();
  if (f !== 0) {
    writer.writeSint32(
      4,
      f
    );
  }
};


/**
 * optional TipsType type = 1;
 * @return {!proto.Proto.Tips.TipsType}
 */
proto.Proto.Tips.ResTips.prototype.getType = function() {
  return /** @type {!proto.Proto.Tips.TipsType} */ (jspb.Message.getFieldWithDefault(this, 1, 0));
};


/**
 * @param {!proto.Proto.Tips.TipsType} value
 * @return {!proto.Proto.Tips.ResTips} returns this
 */
proto.Proto.Tips.ResTips.prototype.setType = function(value) {
  return jspb.Message.setProto3EnumField(this, 1, value);
};


/**
 * optional string lanCode = 2;
 * @return {string}
 */
proto.Proto.Tips.ResTips.prototype.getLancode = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 2, ""));
};


/**
 * @param {string} value
 * @return {!proto.Proto.Tips.ResTips} returns this
 */
proto.Proto.Tips.ResTips.prototype.setLancode = function(value) {
  return jspb.Message.setProto3StringField(this, 2, value);
};


/**
 * repeated string params = 3;
 * @return {!Array<string>}
 */
proto.Proto.Tips.ResTips.prototype.getParamsList = function() {
  return /** @type {!Array<string>} */ (jspb.Message.getRepeatedField(this, 3));
};


/**
 * @param {!Array<string>} value
 * @return {!proto.Proto.Tips.ResTips} returns this
 */
proto.Proto.Tips.ResTips.prototype.setParamsList = function(value) {
  return jspb.Message.setField(this, 3, value || []);
};


/**
 * @param {string} value
 * @param {number=} opt_index
 * @return {!proto.Proto.Tips.ResTips} returns this
 */
proto.Proto.Tips.ResTips.prototype.addParams = function(value, opt_index) {
  return jspb.Message.addToRepeatedField(this, 3, value, opt_index);
};


/**
 * Clears the list making it empty but non-null.
 * @return {!proto.Proto.Tips.ResTips} returns this
 */
proto.Proto.Tips.ResTips.prototype.clearParamsList = function() {
  return this.setParamsList([]);
};


/**
 * optional sint32 resId = 4;
 * @return {number}
 */
proto.Proto.Tips.ResTips.prototype.getResid = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 4, 0));
};


/**
 * @param {number} value
 * @return {!proto.Proto.Tips.ResTips} returns this
 */
proto.Proto.Tips.ResTips.prototype.setResid = function(value) {
  return jspb.Message.setProto3IntField(this, 4, value);
};


