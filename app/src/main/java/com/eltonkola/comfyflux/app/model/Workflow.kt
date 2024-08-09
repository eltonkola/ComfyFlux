package com.eltonkola.comfyflux.app.model

import com.eltonkola.comfyflux.app.cleanPrompt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

//@Serializer(forClass = Workflow::class)
object WorkflowSerializer : KSerializer<Workflow> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Workflow") {
            element<JsonObject>("content")
        }

    override fun serialize(encoder: Encoder, value: Workflow) {
        val jsonEncoder = encoder as? JsonEncoder ?: throw SerializationException("This serializer can be used only with Json format")
        val jsonObject = JsonObject(value.mapValues { (_, node) -> Json.encodeToJsonElement(node) })
        encoder.encodeJsonElement(jsonObject)
    }

    override fun deserialize(decoder: Decoder): Workflow {
        val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException("This serializer can be used only with Json format")
        val jsonObject = jsonDecoder.decodeJsonElement().jsonObject
        return Workflow().apply {
            putAll(jsonObject.mapValues { (_, value) ->
                Json.decodeFromJsonElement<Node<JsonElement>>(value)
            })
        }
    }
}

@Serializable(with = WorkflowSerializer::class)
class Workflow : MutableMap<String, Node<JsonElement>> by mutableMapOf() {

    companion object{
        const val SIZE = "5" // width, height, batch nr
        const val PROMPT = "6" //prompt
        const val KSAMPLER = "3" //it has seed
        const val RANDOMNOISE = "25" //it has seed for FLUX
    }

    inline fun <reified T> getTypedNode(id: String): Node<T>? {
        return this[id]?.let { node ->
            Node(
                inputs = Json.decodeFromJsonElement<T>(node.inputs),
                classType = node.classType,
                meta = node.meta
            )
        }
    }

    fun updateNode(id: String, updateFunction: (Node<JsonElement>) -> Node<JsonElement>) {
        this[id]?.let { node ->
            this[id] = updateFunction(node)
        }
    }

    inline fun <reified T> updateTypedNode(id: String, updateFunction: (Node<T>) -> Node<T>) {
        getTypedNode<T>(id)?.let { node ->
            val updatedNode = updateFunction(node)
            this[id] = Node(
                inputs = Json.encodeToJsonElement(updatedNode.inputs),
                classType = updatedNode.classType,
                meta = updatedNode.meta
            )
        }
    }

    fun toJson(): String {
        return Json.encodeToString(this)
    }
}

@Serializable
data class Node<T>(
    val inputs : T,
    @SerialName("class_type")
    val classType: String,
    @SerialName("_meta")
    val meta: Meta
)

@Serializable
data class Meta(
    val title: String
)

@Serializable
data class SizeInputs(
    val width: Int,
    val height: Int,
    @SerialName("batch_size")
    val batchSize: Int
)

@Serializable
data class PromptInputs(
    val text: String,
    val clip: JsonElement
)


fun Workflow.updateSizeAndBatch(width: Int, height: Int, batchSize: Int){
    this.updateTypedNode<SizeInputs>(Workflow.SIZE) { node ->
        node.copy(
            inputs = node.inputs.copy(
                width = width,
                height = height,
                batchSize = batchSize
            )
        )
    }
}

fun Workflow.updatePrompt(prompt: String ){
    this.updateTypedNode<PromptInputs>(Workflow.PROMPT) { node ->
        node.copy(
            inputs = node.inputs.copy(
                text = prompt
            )
        )
    }
}


fun updateSeed(node: Node<JsonElement>, newSeed: Long): Node<JsonElement> {
    val currentInputs = node.inputs.jsonObject
    val updatedInputs = buildJsonObject {
        currentInputs.forEach { (key, value) ->
            if (key == "seed") {
                put(key, JsonPrimitive(newSeed))
            } else {
                put(key, value)
            }
        }
    }
    return node.copy(inputs = updatedInputs)
}

fun Workflow.updateSeed(seed: Long ){
    this.updateNode(Workflow.KSAMPLER) { node ->
        updateSeed(node, seed)
    }
    this.updateNode(Workflow.RANDOMNOISE) { node ->
        updateSeed(node, seed)
    }
}
