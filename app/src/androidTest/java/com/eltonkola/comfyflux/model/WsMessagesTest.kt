package com.eltonkola.comfyflux.model

import com.eltonkola.comfyflux.data.model.WSMessage
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class WSMessageTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun testPromptMessageParsing() {
        val prompt_request = """
        {"prompt_id": "f79020a5-b9ca-465d-9fb0-59c9ffef379c", "number": 163, "node_errors": {}}
        """

        val message = WSMessage.fromJson(prompt_request) as WSMessage.PromptMessage
        assertEquals("f79020a5-b9ca-465d-9fb0-59c9ffef379c", message.promptId)
        assertEquals(163, message.number)
        assertEquals(emptyMap<String, String>(), message.nodeErrors)
    }

    @Test
    fun testStatusMessageParsing() {
        val status = """
        {"type": "status", "data": {"status": {"exec_info": {"queue_remaining": 1}}, "sid": "74fce948-fd4d-4104-93eb-388c54fd01e0"}}
        """

        val message = WSMessage.fromJson(status) as WSMessage.StatusMessage
        assertEquals(1, message.status.execInfo?.queueRemaining)
        assertEquals("74fce948-fd4d-4104-93eb-388c54fd01e0", message.sid)
    }

    @Test
    fun testExecutionCachedMessageParsing() {
        val execution_cached = """
        {"type": "execution_cached", "data": {"nodes": ["5", "16", "10", "11"], "prompt_id": "f79020a5-b9ca-465d-9fb0-59c9ffef379c", "timestamp": 1723167675096}}
        """
        val message = WSMessage.fromJson(execution_cached) as WSMessage.ExecutionCachedMessage
        assertEquals("f79020a5-b9ca-465d-9fb0-59c9ffef379c", message.promptId)
        assertEquals(listOf("5", "16", "10", "11"), message.nodes)
        assertEquals(1723167675096, message.timestamp)
    }

    @Test
    fun testExecutingMessageParsing() {
        val executing = """
        {"type": "executing", "data": {"node": "6", "prompt_id": "f79020a5-b9ca-465d-9fb0-59c9ffef379c"}}
        """

        val message = WSMessage.fromJson(executing) as WSMessage.ExecutingMessage
        assertEquals("f79020a5-b9ca-465d-9fb0-59c9ffef379c", message.promptId)
        assertEquals("6", message.node)
    }

    @Test
    fun testProgressMessageParsing() {
        val progress = """
        {"type": "progress", "data": {"value": 4, "max": 4, "prompt_id": "f79020a5-b9ca-465d-9fb0-59c9ffef379c", "node": "13"}}
        """

        val message = WSMessage.fromJson(progress) as WSMessage.ProgressMessage
        assertEquals("f79020a5-b9ca-465d-9fb0-59c9ffef379c", message.promptId)
        assertEquals(4, message.value)
        assertEquals(4, message.max)
        assertEquals("13", message.node)
    }

    @Test
    fun testExecutedMessageParsing() {
        val executed = """
        {"type": "executed", "data": {"node": "9", "output": {"images": [{"filename": "ComfyUI_00946_.png", "subfolder": "", "type": "output"}]}, "prompt_id": "f79020a5-b9ca-465d-9fb0-59c9ffef379c"}}
        """

        val message = WSMessage.fromJson(executed) as WSMessage.ExecutedMessage
        assertEquals("f79020a5-b9ca-465d-9fb0-59c9ffef379c", message.promptId)
        assertEquals("9", message.node)
        assertEquals(1, message.output.images.size)
        assertEquals("ComfyUI_00946_.png", message.output.images[0].filename)
        assertEquals("", message.output.images[0].subfolder)
        assertEquals("output", message.output.images[0].type)
    }

    @Test
    fun testExecutionSuccessMessageParsing() {
        val execution_success = """
        {"type": "execution_success", "data": {"prompt_id": "f79020a5-b9ca-465d-9fb0-59c9ffef379c", "timestamp": 1723167762147}}
        """
        val message = WSMessage.fromJson(execution_success) as WSMessage.ExecutionSuccessMessage
        assertEquals("f79020a5-b9ca-465d-9fb0-59c9ffef379c", message.promptId)
        assertEquals(1723167762147, message.timestamp)
    }

}
